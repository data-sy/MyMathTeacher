-- DROP 순서 (CREATE의 역순)
	-- 확률 -> 답안 -> 학생_학습지, 학습지_문항 -> 학습지
    -- 문항, 지식체계 -> 단위개념
    -- 학생 -> 사용자

DROP TABLE IF EXISTS PROBABILITYS;
DROP TABLE IF EXISTS ANSWERS;
DROP TABLE IF EXISTS TESTS_ITEMS;
DROP TABLE IF EXISTS STUDENTS_TESTS;
DROP TABLE IF EXISTS TESTS;

DROP TABLE IF EXISTS ITEMS;
DROP TABLE IF EXISTS KNOWLEDGE_TAGS;
DROP TABLE IF EXISTS CONCEPTS;

DROP TABLE IF EXISTS STUDENTS;
DROP TABLE IF EXISTS USERS;

-- insert 순서
-- 단위개념 insert_concepts_escape_skillid -> 지식체계 insert_knowledge_tags
-- 문항 insert_items_missing
-- 진단 : 진단 학습지 insert_diag_test -> 진단 문항 insert_diag_items -> 진단학습지_진단문항 insert_diag_testsitems
