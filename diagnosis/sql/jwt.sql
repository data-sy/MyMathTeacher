-- DB
show databases;
use mmt;

show tables;

select * from users;
select * from authority;
select * from user_authority;

delete from user_authority where user_id=5;
delete from users where user_id=5;

-- DROP & CREATE
DROP TABLE IF EXISTS user_authority;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS authority;

CREATE TABLE users (
	user_id	BIGINT auto_increment,
	user_email VARCHAR(50),
	user_password VARCHAR(200),
	user_name VARCHAR(20),
	user_phone VARCHAR(20),
    activated TINYINT,
    oauth2id VARCHAR(200),
    auth_provider VARCHAR(20),
	PRIMARY KEY (user_id)
);
CREATE TABLE authority (
	authority_name VARCHAR(20),
	PRIMARY KEY (authority_name)
);
CREATE TABLE user_authority (
	user_autho_id BIGINT auto_increment,
	user_id	BIGINT,
	authority_name VARCHAR(20),
	PRIMARY KEY (user_autho_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (authority_name) REFERENCES authority (authority_name)
);

-- INSERT
INSERT INTO users (user_email, user_password, user_name, activated) VALUES ('admin@gmail.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);
INSERT INTO users (user_email, user_password, user_name, activated) VALUES ('user@gmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1);

INSERT INTO authority (authority_name) VALUES ('ROLE_USER');
INSERT INTO authority (authority_name) VALUES ('ROLE_ADMIN');

INSERT INTO user_authority (user_id, authority_name) VALUES (1, 'ROLE_USER');
INSERT INTO user_authority (user_id, authority_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO user_authority (user_id, authority_name) VALUES (2, 'ROLE_USER');


-- -- 테이블 이름 기존 꺼 사용하기
-- -- table
-- show tables;
-- select * from users;
-- select * from roles;
-- select * from authorities;


-- -- DROP & CREATE
-- DROP TABLE IF EXISTS authorities;
-- DROP TABLE IF EXISTS roles;
-- DROP TABLE IF EXISTS users;

-- CREATE TABLE users (
-- 	user_id	BIGINT auto_increment,
-- 	user_email VARCHAR(50),
-- 	user_password VARCHAR(255),
-- 	user_name VARCHAR(20),
-- 	user_phone VARCHAR(20),
--     activated TINYINT,
-- 	PRIMARY KEY (user_id)
-- );
-- CREATE TABLE roles (
-- 	role_name VARCHAR(20),
-- 	PRIMARY KEY (role_name)
-- );
-- CREATE TABLE authorities (
-- 	authority_id BIGINT auto_increment,
-- 	user_id	BIGINT,
-- 	role_name VARCHAR(20),
-- 	PRIMARY KEY (authority_id),
--     FOREIGN KEY (user_id) REFERENCES users (user_id),
--     FOREIGN KEY (role_name) REFERENCES roles (role_name)
-- );

-- -- INSERT
-- INSERT INTO users (user_email, user_password, user_name, activated) VALUES ('admin@gmail.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);
-- INSERT INTO users (user_email, user_password, user_name, activated) VALUES ('user@gmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1);

-- INSERT INTO roles (role_name) VALUES ('ROLE_USER');
-- INSERT INTO roles (role_name) VALUES ('ROLE_ADMIN');

-- INSERT INTO authorities (user_id, role_name) VALUES (1, 'ROLE_USER');
-- INSERT INTO authorities (user_id, role_name) VALUES (1, 'ROLE_ADMIN');
-- INSERT INTO authorities (user_id, role_name) VALUES (2, 'ROLE_USER');

