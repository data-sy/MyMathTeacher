-- DROP 순서 : 답안 -> 학습지_문항 -> 학습지, 문항. 학생 -> 사용자. 지식체계 -> 단위개념
DROP TABLE IF EXISTS ANSWERS;
DROP TABLE IF EXISTS TESTS_ITEMS;

DROP TABLE IF EXISTS ITEMS;
DROP TABLE IF EXISTS TESTS;
DROP TABLE IF EXISTS STUDENTS;
DROP TABLE IF EXISTS USERS;

DROP TABLE IF EXISTS KNOWLEDGE_TAGS;
DROP TABLE IF EXISTS CONCEPTS;

-- 단위개념 테이블
CREATE TABLE CONCEPTS (
	concept_id INT,
	concept_name VARCHAR(20),
	concept_school_level CHAR(2),
	concept_grade_level CHAR(2),
	concept_semester CHAR(3),
	concept_description VARCHAR(200),
	concept_chapter_id VARCHAR(20),
	concept_chapter_name VARCHAR(20),
	concept_achievement_id VARCHAR(20),
	concept_achievement_name VARCHAR(20),
	PRIMARY KEY (concept_id)
);

-- 지식체계 테이블
CREATE TABLE KNOWLEDGE_TAGS (
	knowledge_tag_id INT,
	from_concept_id INT,
	to_concept_id INT,
	PRIMARY KEY (knowledge_tag_id),
	FOREIGN KEY (from_concept_id) REFERENCES CONCEPTS (concept_id),
	FOREIGN KEY (to_concept_id) REFERENCES CONCEPTS (concept_id)
);

-- 사용자 테이블
CREATE TABLE USERS (
	user_id	VARCHAR(20),
	user_password VARCHAR(20),
	user_name VARCHAR(20),
	user_phone VARCHAR(20),
	PRIMARY KEY (user_id)
);

-- 학생 테이블
CREATE TABLE STUDENTS (
	student_id	VARCHAR(20),
	student_name VARCHAR(20),
	student_phone VARCHAR(20),
	student_birthdate DATE,
	student_school VARCHAR(20),
	student_comments VARCHAR(200),
	teacher_id VARCHAR(20),
	PRIMARY KEY (student_id),
	FOREIGN KEY (teacher_id) REFERENCES USERS (user_id)
);

-- 학습지 테이블
CREATE TABLE TESTS (
	test_id	INT,
	test_name VARCHAR(20),
	test_comments VARCHAR(200),
	test_timestamp DATETIME,
	diagnostic_test_id INT,
	PRIMARY KEY (test_id),
	FOREIGN KEY (diagnostic_test_id) REFERENCES TESTS (test_id)
);

-- 문항 테이블
CREATE TABLE ITEMS (
	item_id	INT,
	item_answer	VARCHAR(20),
	item_image_path	VARCHAR(255),
	concept_id INT,
	PRIMARY KEY (item_id),
	FOREIGN KEY (concept_id) REFERENCES CONCEPTS (concept_id)
);

-- 학습지-문항 테이블
CREATE TABLE TESTS_ITEMS (
	test_item_id INT,
	test_id	INT,
	item_id	INT,
	test_item_number INT,
	PRIMARY KEY (test_item_id),
	FOREIGN KEY (test_id) REFERENCES TESTS (test_id),
	FOREIGN KEY (item_id) REFERENCES ITEMS (item_id)
);

-- 답안 테이블
CREATE TABLE ANSWERS (
	answer_id INT,
	student_id	VARCHAR(20),
	test_item_id INT,
	answer_code	INT,
	answer_probability DECIMAL(5,2),
	answer_timestamp DATETIME,
	PRIMARY KEY (answer_id),
	FOREIGN KEY (student_id) REFERENCES STUDENTS (student_id),
	FOREIGN KEY (test_item_id) REFERENCES TESTS_ITEMS (test_item_id)
);
