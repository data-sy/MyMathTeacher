select * from users;

select * from students;

select * from tests;

show tables;

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
