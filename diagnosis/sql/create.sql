-- 단위개념 테이블
CREATE TABLE CONCEPTS (
	concept_id INT,
	concept_name VARCHAR(70),
	concept_school_level CHAR(2),
	concept_grade_level CHAR(2),
	concept_semester VARCHAR(3),
	concept_description TEXT,
	concept_chapter_id INT,
	concept_chapter_name VARCHAR(60),
	concept_achievement_id INT,
	concept_achievement_name VARCHAR(120),
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
	student_id BIGINT auto_increment,
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
	test_id	BIGINT auto_increment,
	test_name VARCHAR(20),
	test_comments VARCHAR(200),
	test_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	diagnostic_test_id BIGINT,
	PRIMARY KEY (test_id),
	FOREIGN KEY (diagnostic_test_id) REFERENCES TESTS (test_id)
);

-- 문항 테이블
CREATE TABLE ITEMS (
	item_id	BIGINT auto_increment,
	item_answer	VARCHAR(20),
	item_image_path	VARCHAR(255),
	concept_id INT,
	PRIMARY KEY (item_id),
	FOREIGN KEY (concept_id) REFERENCES CONCEPTS (concept_id)
);

-- 학습지-문항 테이블
CREATE TABLE TESTS_ITEMS (
	test_item_id BIGINT auto_increment,
	test_id	BIGINT,
	item_id	BIGINT,
	test_item_number INT,
	PRIMARY KEY (test_item_id),
	FOREIGN KEY (test_id) REFERENCES TESTS (test_id),
	FOREIGN KEY (item_id) REFERENCES ITEMS (item_id)
);

-- 답안 테이블
CREATE TABLE ANSWERS (
	answer_id BIGINT auto_increment,
	student_id BIGINT,
	test_item_id BIGINT,
	answer_code	INT,
	answer_probability DECIMAL(5,2),
	answer_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (answer_id),
	FOREIGN KEY (student_id) REFERENCES STUDENTS (student_id),
	FOREIGN KEY (test_item_id) REFERENCES TESTS_ITEMS (test_item_id)
);
