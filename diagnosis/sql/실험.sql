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
