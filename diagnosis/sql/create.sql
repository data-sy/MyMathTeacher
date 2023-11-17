-- CREATE 순서
	-- 단위개념 -> 지식체계, 문항
    -- 학습지 -> 학습지_문항
    -- 사용자 (-> 롤 -> 권한) -> 학생
    -- 학생_학습지 -> 답안 -> 확률

-- 단위개념 테이블
CREATE TABLE concepts (
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
    skill_id INT,
	PRIMARY KEY (concept_id)
);

-- 지식체계 테이블
CREATE TABLE knowledge_space (
	knowledge_space_id INT,
	from_concept_id INT,
	to_concept_id INT,
	PRIMARY KEY (knowledge_space_id),
	FOREIGN KEY (from_concept_id) REFERENCES concepts (concept_id),
	FOREIGN KEY (to_concept_id) REFERENCES concepts (concept_id)
);

-- 문항 테이블
CREATE TABLE items (
	item_id BIGINT auto_increment,
	item_answer VARCHAR(100),
	item_image_path VARCHAR(255),
	concept_id INT,
	PRIMARY KEY (item_id),
	FOREIGN KEY (concept_id) REFERENCES concepts (concept_id)
);

-- 학습지 테이블
CREATE TABLE tests (
	test_id BIGINT auto_increment,
	test_name VARCHAR(50),
	test_comments VARCHAR(200),
	PRIMARY KEY (test_id)
);

-- 학습지-문항 테이블
CREATE TABLE tests_items (
	test_item_id BIGINT auto_increment,
	test_id	BIGINT,
	item_id	BIGINT,
	test_item_number INT,
	PRIMARY KEY (test_item_id),
	FOREIGN KEY (test_id) REFERENCES tests (test_id),
	FOREIGN KEY (item_id) REFERENCES items (item_id)
);

-- 사용자 테이블 -- jwt로 이동
-- CREATE TABLE users (
-- 	user_id	VARCHAR(20),
-- 	user_password VARCHAR(20),
-- 	user_name VARCHAR(20),
-- 	user_phone VARCHAR(20),
-- 	PRIMARY KEY (user_id)
-- );

-- 학생 테이블
CREATE TABLE students (
	student_id BIGINT auto_increment,
	student_name VARCHAR(20),
	student_phone VARCHAR(20),
	student_birthdate DATE,
	student_school VARCHAR(20),
	student_comments VARCHAR(200),
	teacher_id BIGINT,
	PRIMARY KEY (student_id),
	FOREIGN KEY (teacher_id) REFERENCES users (user_id)
);

-- 학생_학습지 테이블
CREATE TABLE students_tests (
	student_test_id BIGINT auto_increment,
    student_id BIGINT,
    test_id BIGINT,
	student_test_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	diagnosis_id BIGINT,
	PRIMARY KEY (student_test_id),
	FOREIGN KEY (student_id) REFERENCES students (student_id),
	FOREIGN KEY (test_id) REFERENCES tests (test_id),
    FOREIGN KEY (diagnosis_id) REFERENCES students_tests (student_test_id)
);

-- 답안 테이블
CREATE TABLE answers (
	answer_id BIGINT auto_increment,
	student_test_id BIGINT,
	item_id BIGINT,
	answer_code INT,
	PRIMARY KEY (answer_id),
	FOREIGN KEY (student_test_id) REFERENCES students_tests (student_test_id),
	FOREIGN KEY (item_id) REFERENCES tests_items (item_id)
);

-- 확률 테이블
CREATE TABLE probabilities (
	probability_id BIGINT auto_increment,
	answer_id BIGINT,
    concept_id INT,
    to_concept_depth INT,
	probability_percent DOUBLE,
	probability_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (probability_id),
	FOREIGN KEY (answer_id) REFERENCES answers (answer_id),
	FOREIGN KEY (concept_id) REFERENCES concepts (concept_id)
);

-- 시나리오 테이블
CREATE TABLE scenario (
	scenario_id BIGINT auto_increment,
    scenario_case INT,
    mom_id INT,
    skill_id INT,
    pro_list VARCHAR(200),
	PRIMARY KEY (scenario_id),
	FOREIGN KEY (mom_id) REFERENCES concepts (concept_id)
-- 	FOREIGN KEY (skill_id) REFERENCES concepts (skill_id)
);
