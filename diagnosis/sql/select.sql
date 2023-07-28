select * from users;

delete from users;

select * from students;

delete from students;

select * from tests;

show tables;

delete from concepts;

select count(*) from concepts;

select count(*) from knowledge_tags;

select * from items;
-- 진단평가 문항들 보기
select * from items where item_id>4890;

select count(*) from items;

select * from tests_items;

SELECT i.item_id, i.item_image_path, ti.test_item_number FROM tests_items ti JOIN items i ON ti.item_id = i.item_id WHERE ti.test_id = 1;
