show databases;
create database mmt;
use mmt;

-- DROP 순서 (CREATE의 역순)
	-- 확률 -> 답안 -> 학생_학습지, 학습지_문항 -> 학습지
    -- 문항, 지식체계 -> 단위개념
    -- 학생 -> 사용자

-- 테이블 이름 소문자로 수정
DROP TABLE IF EXISTS probabilities;
DROP TABLE IF EXISTS answers;
DROP TABLE IF EXISTS tests_items;
DROP TABLE IF EXISTS students_tests;
DROP TABLE IF EXISTS tests;

DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS knowledge_tags;
DROP TABLE IF EXISTS concepts;

DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTS users;

-- insert 순서
-- 단위개념 insert_concepts_escape_skillid -> 지식체계 insert_knowledge_tags
-- 문항 insert_items_missing
-- 진단 : 진단 학습지 insert_diag_test -> 진단 문항 insert_diag_items -> 진단학습지_진단문항 insert_diag_testsitems

-- 테이블 이름 소문자로 수정
-- DROP TABLE IF EXISTS PROBABILITIES;
-- DROP TABLE IF EXISTS ANSWERS;
-- DROP TABLE IF EXISTS TESTS_ITEMS;
-- DROP TABLE IF EXISTS STUDENTS_TESTS;
-- DROP TABLE IF EXISTS TESTS;

-- DROP TABLE IF EXISTS ITEMS;
-- DROP TABLE IF EXISTS KNOWLEDGE_TAGS;
-- DROP TABLE IF EXISTS CONCEPTS;

-- DROP TABLE IF EXISTS STUDENTS;
-- DROP TABLE IF EXISTS USERS;
