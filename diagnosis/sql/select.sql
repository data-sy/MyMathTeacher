select * from users;

select * from students;

select * from tests;

show tables;

select * from concepts;

select count(*) from concepts;

select count(*) from knowledge_tags;

select * from items;
-- 진단평가 문항들 보기
select * from items where item_id>4890;

select count(*) from items;

select * from tests_items;

SELECT i.item_id, i.item_image_path, ti.test_item_number FROM tests_items ti JOIN items i ON ti.item_id = i.item_id WHERE ti.test_id = 1;

-- 가영이(1)는 1번, 나영이(2)는 5번, 다영이(3)는 9, 11번 진단학습지 insert
select * from answers;

-- 학습지 당 정오답 여부 함께 select
SELECT a.answer_id, a.test_id, t.test_name, a.answer_code FROM answers a JOIN tests t ON t.test_id = a.test_id WHERE a.student_id=3 AND (a.test_id, a.item_id) IN (SELECT ti.test_id, ti.item_id FROM tests_items ti WHERE ti.test_item_number=1);
-- group by 는 재시험을 분류 못 함
select test_id, min(item_id) from answers group by test_id having min(item_id);

select * from students_tests;

select * from answers;

-- skill_id당 정오답 결과
SELECT c.skill_id, a.answer_code FROM answers a JOIN items i ON a.item_id = i.item_id JOIN concepts c ON c.concept_id = i.concept_id WHERE a.student_test_id=3;
SELECT c.skill_id, a.answer_code FROM answers a JOIN items i ON a.item_id = i.item_id JOIN concepts c ON c.concept_id = i.concept_id WHERE a.student_test_id=4;

-- AIInput을 위해 해당 학생의 (답안을 기록한) 학생_학습지 아이디를 시간순으로 추출
SELECT student_id FROM students_tests WHERE student_test_id=7;
SELECT student_test_timestamp FROM students_tests WHERE student_test_id=7;
SELECT student_test_id FROM answers;
	-- 2개 모두 가능 (나중에 성능 테스트 해보기. 우선 두 번째 선택)
SELECT student_test_id FROM students_tests WHERE student_id = (SELECT student_id FROM students_tests WHERE student_test_id=7) AND student_test_id IN (SELECT student_test_id FROM answers);
SELECT student_test_id FROM students_tests st WHERE student_id = (SELECT student_id FROM students_tests WHERE student_test_id=7) AND EXISTS (SELECT 1 FROM answers a WHERE a.student_test_id = st.student_test_id);
	-- 여기에 타임스탬프 추가
SELECT student_test_id FROM students_tests st WHERE student_id = (SELECT student_id FROM students_tests WHERE student_test_id=5) AND student_test_timestamp <= (SELECT student_test_timestamp FROM students_tests WHERE student_test_id=5) AND EXISTS (SELECT 1 FROM answers a WHERE a.student_test_id = st.student_test_id);

SELECT student_test_id FROM students_tests st 
WHERE student_id = (SELECT student_id FROM students_tests WHERE student_test_id=5) 
AND student_test_timestamp <= (SELECT student_test_timestamp FROM students_tests WHERE student_test_id=5) 
AND EXISTS (SELECT 1 FROM answers a WHERE a.student_test_id = st.student_test_id);

-- st_id에 따른 문항들의 skill_id 찾기
SELECT a.answer_id, i.concept_id, c.skill_id FROM items i JOIN answers a ON a.item_id=i.item_id JOIN concepts c ON c.concept_id=i.concept_id WHERE a.student_test_id = 7;

select * from probabilities;

SELECT k.to_concept_id, c.skill_id FROM concepts c JOIN knowledge_tags k ON c.concept_id=k.to_concept_id WHERE k.from_concept_id = 6104;

select * from probabilities p join answers a on a.answer_id=p.answer_id where a.student_test_id=7;

SELECT c.concept_name, p.to_concept_depth FROM concepts c JOIN probabilities p ON c.concept_id=p.concept_id JOIN answers a ON a.answer_id=p.answer_id WHERE a.student_test_id = 7 AND p.probability_percent<= 0.5;

-- sql 안에서의 조건문인데 레파지토리 메서드 안에서 if문 처리하는 걸로 수정해서 사용하진 않음
SELECT c.concept_name, p.to_concept_depth FROM concepts c 
JOIN probabilities p ON c.concept_id=p.concept_id 
JOIN answers a ON a.answer_id=p.answer_id 
WHERE a.student_test_id = 7
AND p.to_concept_depth=2
AND CASE
		WHEN p.to_concept_depth=0 THEN p.probability_percent<= 0.5
	ELSE TRUE
	END;
SELECT c.concept_name, p.to_concept_depth FROM concepts c JOIN probabilities p ON c.concept_id=p.concept_id JOIN answers a ON a.answer_id=p.answer_id WHERE a.student_test_id = 7 AND p.to_concept_depth=2 AND CASE WHEN p.to_concept_depth=0 THEN p.probability_percent<= 0.5 ELSE TRUE END;

-- 뎁스 1, 2는 모두 셀렉트
SELECT c.concept_name, p.to_concept_depth, p.probability_percent FROM concepts c JOIN probabilities p ON c.concept_id=p.concept_id JOIN answers a ON a.answer_id=p.answer_id WHERE a.student_test_id = 7 AND p.to_concept_depth>0 ORDER BY p.probability_percent;
-- 뎁스 0은 50% 이하만 셀렉트

-- 후수단위개념
SELECT c.concept_id FROM answers a JOIN items i ON i.item_id=a.item_id JOIN concepts c ON c.concept_id=i.concept_id JOIN probabilities p ON p.answer_id=a.answer_id WHERE p.probability_percent>0.6 AND a.student_test_id = 7;
SELECT c.concept_name FROM concepts c JOIN knowledge_tags k ON c.concept_id = k.from_concept_id WHERE k.to_concept_id IN (SELECT c.concept_id FROM answers a JOIN items i ON i.item_id=a.item_id JOIN concepts c ON c.concept_id=i.concept_id JOIN probabilities p ON p.answer_id=a.answer_id WHERE p.probability_percent>0.6 AND a.student_test_id = 7);

-- item별 확률 등의 정보
SELECT ti.test_item_number, i.item_image_path, c.concept_id, c.concept_name, p.probability_percent 
FROM answers a 
JOIN items i ON i.item_id = a.item_id 
JOIN tests_items ti ON ti.item_id = a.item_id 
JOIN probabilities p ON p.answer_id = a.answer_id 
JOIN concepts c ON c.concept_id = i.concept_id
WHERE a.student_test_id = 7
AND p.to_concept_depth = 0 ;

SELECT ti.test_item_number, i.item_image_path, c.concept_id, c.concept_name, p.probability_percent FROM answers a 
JOIN items i ON i.item_id = a.item_id JOIN tests_items ti ON ti.item_id = a.item_id JOIN probabilities p ON p.answer_id = a.answer_id JOIN concepts c ON c.concept_id = i.concept_id 
WHERE a.student_test_id = 7 AND p.to_concept_depth = 0 ;

-- c_id로 선수, 후수 단위개념 찾기
select * from concepts;
select * from knowledge_tags;
SELECT concept_name FROM concepts WHERE concept_id IN (SELECT to_concept_id FROM knowledge_tags WHERE from_concept_id=843);
SELECT concept_name FROM concepts WHERE concept_id IN (SELECT from_concept_id FROM knowledge_tags WHERE to_concept_id=843);
