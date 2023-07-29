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