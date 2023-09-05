-- test DB
create database test;
use test;

-- jwt 테스트
DROP TABLE IF EXISTS user;

CREATE TABLE user (
	user_id BIGINT auto_increment,
    user_email VARCHAR(20),
	user_password VARCHAR(20),
	user_name VARCHAR(20),
	user_phone VARCHAR(20),
	provider VARCHAR(20),
	PRIMARY KEY (user_id)
);

INSERT INTO user (user_email, user_password) VALUES ('test@gmail.com', 'testtest');
INSERT INTO user (user_name, user_password) VALUES ('test@gmail.com', 'testtesttest');

select * from user;
