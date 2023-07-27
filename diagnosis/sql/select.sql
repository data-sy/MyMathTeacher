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
