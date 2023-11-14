// 모든 노드 보기
MATCH (n) RETURN (n)

// 모든 노드와 관계 삭제
MATCH (a) optional MATCH (a)-[r]-() DELETE a, r;

// concepts 노드 생성
LOAD CSV WITH HEADERS FROM "file:///concepts_ele_lower.csv" AS row
CREATE (:ele_lower {name: row.name, concept_id: toInteger(row.id), desc: row.desc, school_level:row.school_level, grade_level:row.grade_level, semester:row.semester, chapter_id:toInteger(row.chapter_id), chapter_name_1: row.chapter_name_1, chapter_name_2: row.chapter_name_2, chapter_name_3: row.chapter_name_3, achievement_id:toInteger(row.achievement_id), achievement_name:row.achievement_name, skill_id:toInteger(row.skill_id)});
LOAD CSV WITH HEADERS FROM "file:///concepts_ele_senior.csv" AS row
CREATE (:ele_senior {name: row.name, concept_id: toInteger(row.id), desc: row.desc, school_level:row.school_level, grade_level:row.grade_level, semester:row.semester, chapter_id:toInteger(row.chapter_id), chapter_name_1: row.chapter_name_1, chapter_name_2: row.chapter_name_2, chapter_name_3: row.chapter_name_3, achievement_id:toInteger(row.achievement_id), achievement_name:row.achievement_name, skill_id:toInteger(row.skill_id)});
LOAD CSV WITH HEADERS FROM "file:///concepts_mid_1.csv" AS row
CREATE (:mid_1 {name: row.name, concept_id: toInteger(row.id), desc: row.desc, school_level:row.school_level, grade_level:row.grade_level, semester:row.semester, chapter_id:toInteger(row.chapter_id), chapter_name_1: row.chapter_name_1, chapter_name_2: row.chapter_name_2, chapter_name_3: row.chapter_name_3, achievement_id:toInteger(row.achievement_id), achievement_name:row.achievement_name, skill_id:toInteger(row.skill_id)});
LOAD CSV WITH HEADERS FROM "file:///concepts_mid_2.csv" AS row
CREATE (:mid_2 {name: row.name, concept_id: toInteger(row.id), desc: row.desc, school_level:row.school_level, grade_level:row.grade_level, semester:row.semester, chapter_id:toInteger(row.chapter_id), chapter_name_1: row.chapter_name_1, chapter_name_2: row.chapter_name_2, chapter_name_3: row.chapter_name_3, achievement_id:toInteger(row.achievement_id), achievement_name:row.achievement_name, skill_id:toInteger(row.skill_id)});
LOAD CSV WITH HEADERS FROM "file:///concepts_mid_3.csv" AS row
CREATE (:mid_3 {name: row.name, concept_id: toInteger(row.id), desc: row.desc, school_level:row.school_level, grade_level:row.grade_level, semester:row.semester, chapter_id:toInteger(row.chapter_id), chapter_name_1: row.chapter_name_1, chapter_name_2: row.chapter_name_2, chapter_name_3: row.chapter_name_3, achievement_id:toInteger(row.achievement_id), achievement_name:row.achievement_name, skill_id:toInteger(row.skill_id)});
LOAD CSV WITH HEADERS FROM "file:///concepts_high_1.csv" AS row
CREATE (:high_1 {name: row.name, concept_id: toInteger(row.id), desc: row.desc, school_level:row.school_level, grade_level:row.grade_level, semester:row.semester, chapter_id:toInteger(row.chapter_id), chapter_name_1: row.chapter_name_1, chapter_name_2: row.chapter_name_2, chapter_name_3: row.chapter_name_3, achievement_id:toInteger(row.achievement_id), achievement_name:row.achievement_name, skill_id:toInteger(row.skill_id)});
LOAD CSV WITH HEADERS FROM "file:///concepts_high_2.csv" AS row
CREATE (:high_2 {name: row.name, concept_id: toInteger(row.id), desc: row.desc, school_level:row.school_level, grade_level:row.grade_level, semester:row.semester, chapter_id:toInteger(row.chapter_id), chapter_name_1: row.chapter_name_1, chapter_name_2: row.chapter_name_2, chapter_name_3: row.chapter_name_3, achievement_id:toInteger(row.achievement_id), achievement_name:row.achievement_name, skill_id:toInteger(row.skill_id)});
LOAD CSV WITH HEADERS FROM "file:///concepts_high_3.csv" AS row
CREATE (:high_3 {name: row.name, concept_id: toInteger(row.id), desc: row.desc, school_level:row.school_level, grade_level:row.grade_level, semester:row.semester, chapter_id:toInteger(row.chapter_id), chapter_name_1: row.chapter_name_1, chapter_name_2: row.chapter_name_2, chapter_name_3: row.chapter_name_3, achievement_id:toInteger(row.achievement_id), achievement_name:row.achievement_name, skill_id:toInteger(row.skill_id)});

// knowledge_space 관계 생성
LOAD CSV WITH HEADERS FROM "file:///knowledge_space.csv" AS row
MATCH (a {concept_id: toInteger(row.to_concept_id)}), (b {concept_id: toInteger(row.from_concept_id)})
CREATE (a)-[r:KNOWLEDGE_SPACE {knowledge_space_id: toInteger(row.id) }]->(b)


// 질의
// MATCH ()-[]->() RETURN

// 선수지식 확인 (예. 지식id=4015)
// depth 1
MATCH (a)-[r1]->(b{concept_id:4015})
RETURN a, r1, b
// depth 2
MATCH (a)-[r1]->(b)-[r2]->(c{concept_id:4015})
RETURN a, r1, b, r2, c
// depth 3
MATCH (a)-[r1]->(b)-[r2]->(c)-[r3]->(d{concept_id:4015})
RETURN a, r1, b, r2, c, r3, d
// depth 1 에 대해서는 양방향으로 한다면?
MATCH (a)-[r1]->(b)-[r2]-(c)-[r3]->(d{concept_id:4015})
RETURN a, r1, b, r2, c, r3, d


// 같은 소단원 (중은 2, 대는 1) 보기. 관계까지 나옴.
MATCH (n {concept_id: 4015}), (m)
WHERE m.chapter_name_3 = n.chapter_name_3
RETURN n, m;
