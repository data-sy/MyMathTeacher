-- DB
show databases;
use mmt;

-- table
show tables;
select * from users;
select * from roles;
select * from authorities;


-- DROP & CREATE
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id	BIGINT auto_increment,
	user_email VARCHAR(50),
	user_password VARCHAR(255),
	user_name VARCHAR(20),
	user_phone VARCHAR(20),
    activated TINYINT,
	PRIMARY KEY (user_id)
);
CREATE TABLE roles (
	role_name VARCHAR(20),
	PRIMARY KEY (role_name)
);
CREATE TABLE authorities (
	authority_id BIGINT auto_increment,
	user_id	BIGINT,
	role_name VARCHAR(20),
	PRIMARY KEY (authority_id),
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (role_name) REFERENCES roles (role_name)
);

-- INSERT
INSERT INTO users (user_email, user_password, user_name, activated) VALUES ('admin@gmail.com', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);
INSERT INTO users (user_email, user_password, user_name, activated) VALUES ('user@gmail.com', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1);

INSERT INTO roles (role_name) VALUES ('ROLE_USER');
INSERT INTO roles (role_name) VALUES ('ROLE_ADMIN');

INSERT INTO authorities (user_id, role_name) VALUES (1, 'ROLE_USER');
INSERT INTO authorities (user_id, role_name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO authorities (user_id, role_name) VALUES (2, 'ROLE_USER');
