-- 실험1 : 진단학습지 다운로드 시, answer테이블에 서브쿼리로 insert 후 서브쿼리로 update
DROP TABLE IF EXISTS exprm2;
DROP TABLE IF EXISTS exprm1;
CREATE TABLE exprm2 (
	test_item_id BIGINT auto_increment,
	test_id	BIGINT,
	item_id	BIGINT,
	test_item_number INT,
	PRIMARY KEY (test_item_id)
);
CREATE TABLE exprm1 (
	answer_id BIGINT auto_increment,
	student_id BIGINT,
	test_item_id BIGINT,
	answer_code	INT,
	answer_probability DECIMAL(5,2),
	answer_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (answer_id),
	FOREIGN KEY (test_item_id) REFERENCES exprm2 (test_item_id)
);
insert into exprm2 (test_id, item_id, test_item_number) values (1, 10, 1);
insert into exprm2 (test_id, item_id, test_item_number) values (1, 20, 2);
insert into exprm2 (test_id, item_id, test_item_number) values (1, 30, 3);
insert into exprm2 (test_id, item_id, test_item_number) values (1, 40, 4);
insert into exprm2 (test_id, item_id, test_item_number) values (1, 50, 5);
insert into exprm2 (test_id, item_id, test_item_number) values (2, 60, 1);
insert into exprm2 (test_id, item_id, test_item_number) values (2, 70, 2);
insert into exprm2 (test_id, item_id, test_item_number) values (2, 80, 3);
insert into exprm2 (test_id, item_id, test_item_number) values (2, 90, 4);
insert into exprm2 (test_id, item_id, test_item_number) values (2, 100, 5);

insert into exprm1 (test_item_id) select test_item_id from exprm2 where test_id=2;
select * from exprm1;

update exprm1 set student_id=1000 where test_item_id in (select test_item_id from exprm2 where test_id=2);
select * from exprm1;

-- 실험2 : 학생에 따른 학습지와 정오답 기록 여부 함께 보여주고 싶어
	-- from에 테이블이 없더라도 서브쿼리를 사용하면 select 구문에서 사용할 수 있는지 확인
DROP TABLE IF EXISTS ctable;
DROP TABLE IF EXISTS btable;
DROP TABLE IF EXISTS atable;
CREATE TABLE atable (
	a_id VARCHAR(20),
	sel_1 BIGINT,
	PRIMARY KEY (a_id)
);
CREATE TABLE btable (
	b_id VARCHAR(20),
	a_id VARCHAR(20),
	PRIMARY KEY (b_id),
	FOREIGN KEY (a_id) REFERENCES atable (a_id)
);
CREATE TABLE ctable (
	c_id VARCHAR(20),
	b_id VARCHAR(20),
    sel_2 BIGINT,
    whe BIGINT,
    PRIMARY KEY (c_id),
	FOREIGN KEY (b_id) REFERENCES btable (b_id)
);
insert into atable values ("a아이디1", 10);
insert into atable values ("a아이디2", 20);
insert into atable values ("a아이디3", 30);
insert into btable values ("b아이디1", "a아이디1");
insert into btable values ("b아이디2", "a아이디1");
insert into btable values ("b아이디3", "a아이디2");
insert into btable values ("b아이디4", "a아이디2");
insert into btable values ("b아이디5", "a아이디3");
insert into btable values ("b아이디6", "a아이디3");
insert into ctable values ("c아이디1", "b아이디1", 100, 1);
insert into ctable values ("c아이디2", "b아이디1", 200, 1);
insert into ctable values ("c아이디3", "b아이디2", 300, 1);
insert into ctable values ("c아이디4", "b아이디2", 400, 2);
insert into ctable values ("c아이디5", "b아이디3", 500, 2);
insert into ctable values ("c아이디6", "b아이디3", 600, 2);
insert into ctable values ("c아이디7", "b아이디4", 700, 3);
insert into ctable values ("c아이디8", "b아이디4", 800, 3);
insert into ctable values ("c아이디9", "b아이디5", 900, 3);
insert into ctable values ("c아이디10", "b아이디5", 1000, 4);
insert into ctable values ("c아이디11", "b아이디6", 1100, 4);
insert into ctable values ("c아이디12", "b아이디6", 1200, 4);
	-- 예상대로 from 절에 ctable 없으니 에러 Error Code: 1054. Unknown column 'ctable.sel_2' in 'field list'
SELECT atable.sel_1, ctable.sel_2 FROM atable JOIN btable ON atable.a_id = btable.b_id WHERE btable.b_id IN (SELECT b_id FROM ctable WHERE whe = 1);
	-- 테이블 3개 조인
SELECT *
FROM atable a
INNER JOIN btable b ON a.a_id = b.a_id
INNER JOIN ctable c ON b.b_id = c.b_id;
		-- 원하는 조건 추가 (이게 최종!!!)
SELECT a.a_id, a.sel_1, c.sel_2
FROM atable a
INNER JOIN btable b ON a.a_id = b.a_id
INNER JOIN ctable c ON b.b_id = c.b_id
WHERE c.whe = 1;
SELECT a.sel_1, c.sel_2 FROM atable a INNER JOIN btable b ON a.a_id = b.a_id INNER JOIN ctable c ON b.b_id = c.b_id WHERE c.whe = 1;
	-- 적용
SELECT t.test_name, a.answer_code FROM tests t INNER JOIN tests_items ti ON t.test_id = ti.test_id INNER JOIN answers a ON ti.test_item_id = a.test_item_id WHERE a.student_id = 2;
	-- 이러면 test_id 하나에 따른 여러 결과가 나와버림... 흠...

-- 실험 2' : 관계 테이블의 id 말고 그 전 단계 테이블의 id를 fk로 사용하면 조인 2번 안 써도 되는지 테스트
DROP TABLE IF EXISTS ctable;
DROP TABLE IF EXISTS btable;
DROP TABLE IF EXISTS atable;
DROP TABLE IF EXISTS a2table;

CREATE TABLE atable (
	a_id VARCHAR(20),
	sel_1 BIGINT,
	PRIMARY KEY (a_id)
);
CREATE TABLE a2table (
	a2_id VARCHAR(20),
	PRIMARY KEY (a2_id)
);
CREATE TABLE btable (
	b_id BIGINT auto_increment,
	a_id VARCHAR(20),
    a2_id VARCHAR(20),
	PRIMARY KEY (b_id),
	FOREIGN KEY (a_id) REFERENCES atable (a_id),
	FOREIGN KEY (a2_id) REFERENCES a2table (a2_id)
);
CREATE TABLE ctable (
	c_id BIGINT auto_increment,
	a_id VARCHAR(20),
    a2_id VARCHAR(20),
    sel_2 BIGINT,
    whe VARCHAR(20),
    PRIMARY KEY (c_id),
	FOREIGN KEY (a_id) REFERENCES btable (a_id),
	FOREIGN KEY (a2_id) REFERENCES btable (a2_id)
);
insert into atable values ("시험지1", 10);
insert into atable values ("시험지2", 20);
insert into a2table values ("문항1");
insert into a2table values ("문항2");
insert into a2table values ("문항3");
insert into btable values (1, "시험지1", "문항1");
insert into btable values (2, "시험지1", "문항2");
insert into btable values (3, "시험지1", "문항3");
insert into btable values (4, "시험지2", "문항1");
insert into btable values (5, "시험지2", "문항2");
insert into btable values (6, "시험지2", "문항3");
insert into ctable values (1, "시험지1", "문항1", 100, "학생1");
insert into ctable values (2, "시험지1", "문항2", 200, "학생1");
insert into ctable values (3, "시험지1", "문항3", 300, "학생1");
insert into ctable values (4, "시험지2", "문항1", 400, "학생1");
insert into ctable values (5, "시험지2", "문항2", 500, "학생1");
insert into ctable values (6, "시험지2", "문항3", 600, "학생1");
insert into ctable values (7, "시험지1", "문항1", 700, "학생2");
insert into ctable values (8, "시험지1", "문항2", 800, "학생2");
insert into ctable values (9, "시험지1", "문항3", 900, "학생2");
insert into ctable values (10, "시험지2", "문항1", 1000, "학생2");
insert into ctable values (11, "시험지2", "문항2", 1100, "학생2");
insert into ctable values (12, "시험지2", "문항3", 1200, "학생2");
	-- 오!! 가능
SELECT a.a_id, a.sel_1, c.sel_2
FROM ctable c
INNER JOIN atable a ON a.a_id = c.a_id
-- 조인조건 둘 중 하나만 써도 가능, 물론 둘 다 써도 가능하고 INNER JOIN a2table a2 ON a2.a2_id = c.a2_id
WHERE c.whe = "학생2";

-- 테이블이 바뀌었으니 실험1의 insert, update 구문도 수정되어야 함. 실험은 생략하고 바로 프로젝트에서 수정하자.
-- 오개념 찾음 : 기존 논리로는 한 시험지를 재사용할 수 없음...시험지에 맞춰서 학생을 업데이트 하니까 (수정 전에도 오류였음)
	-- 실험1의 테이블 그대로 사용
insert into exprm1 (student_id, test_item_id) select 1000 AS student_id, test_item_id from exprm2 where test_id=2;
select * from exprm1;

-- 실험3 : isRecord DB단에서 처리하기
SELECT
    st.student_test_id,
    t.test_name,
    t.test_comments,
    CASE
        WHEN EXISTS (SELECT 1 FROM answers a WHERE a.student_test_id = st.student_test_id) THEN 'true'
        ELSE 'false'
    END AS is_record
FROM students_tests st
JOIN tests t
ON st.test_id = t.test_id
WHERE st.student_id = 3;

SELECT st.student_test_id, t.test_name, t.test_comments, 
CASE WHEN EXISTS (SELECT 1 FROM answers a WHERE a.student_test_id = st.student_test_id) 
THEN TRUE ELSE FALSE END AS is_record 
FROM students_tests st JOIN tests t ON st.test_id = t.test_id
WHERE st.student_id = 1;

-- 실험4 : 다중 value 문법 확인
use mmt;
CREATE TABLE atable (
	a_id BIGINT auto_increment,
	in_1 BIGINT,
    in_2 BIGINT,
    in_3 INT,
	PRIMARY KEY (a_id)
);
INSERT INTO atable(in_1, in_2, in_3) VALUES (1, 1, 0), (1, 2, 1), (1, 3, 1), (1, 4, 1), (1, 5, 1);
select * from atable;
-- 근데 jdbcTemplate이므로 배치사이즈업 사용!

-- 실험 5 : 
select * from concepts where concept_grade_level = "중3";
-- 중등 진단학습지
select * from concepts where concept_id in (3821, 9171, 6801, 8894, 8903, 9124, 4661, 2852, 2868, 4979, 1117, 8404, 8426, 4210, 4212, 10182, 10180, 1251, 2496, 9924) order by concept_grade_level;
-- 선수지식들
select * from concepts where concept_id in (3806, 6784, 1263, 6795, 6800, 7665, 5653, 8893, 11214, 9796, 116, 8790, 4803, 9168, 4972, 4786, 4975, 1110, 342, 2120, 8836,
 8420, 5308, 5261, 9728, 116, 971, 6784, 1011, 8882, 2643, 3691, 3692, 3793, 6795, 6784, 5810, 9166, 5809, 5620, 1266, 2116, 6648, 11239, 2117, 7944, 1263, 4785, 6795,
 1263, 2666, 4961, 2120, 6804, 332, 342, 346, 2119, 2120, 342, 4709, 9728, 78, 78, 5261, 4699, 7944, 4668, 4672, 1259, 4662, 9161, 9162, 1009, 1010, 2053, 11214, 2055) order by concept_grade_level, concept_semester;
-- 2차 선수지식들
select * from concepts where concept_id in (3691, 3692, 3793, 6795, 6784, 5810, 9166, 5809, 5620, 1266, 2116, 6648, 11239, 2117, 7944, 1263, 4785, 6795, 1263, 2666, 
4961, 2120, 6804, 332, 342, 346, 2119, 2120, 342, 4709, 9728, 78, 78, 5261, 4699, 7944, 4668, 4672, 1259, 4662, 9161, 9162, 1009, 1010, 2053, 11214, 2055) 
order by concept_grade_level, concept_semester;