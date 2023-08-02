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
