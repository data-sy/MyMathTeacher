package com.mmt.diagnosis.repository.concept;

import com.mmt.diagnosis.domain.Concept;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Primary
public class JdbcTemplateConceptRepository implements ConceptRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateConceptRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Concept findById(int conceptId) {
        String sql = "SELECT * FROM concepts WHERE concept_id = ?";
        return jdbcTemplate.queryForObject(sql, conceptRowMapper(), conceptId);
    }

    @Override
    public List<String> findToConceptName(int conceptId) {
        String sql = "SELECT concept_name FROM concepts WHERE concept_id IN (SELECT to_concept_id FROM knowledge_space WHERE from_concept_id=?)";
        return jdbcTemplate.queryForList(sql, String.class, conceptId);
    }

    @Override
    public List<String> findFromConceptName(int conceptId) {
        String sql = "SELECT concept_name FROM concepts WHERE concept_id IN (SELECT from_concept_id FROM knowledge_space WHERE to_concept_id=?)";
        return jdbcTemplate.queryForList(sql, String.class, conceptId);
    }

    private RowMapper<Concept> conceptRowMapper() {
        return (rs, rowNum) -> {
            Concept concept = new Concept();
            concept.setConceptId(rs.getInt("concept_id"));
            concept.setConceptName(rs.getString("concept_name"));
            concept.setConceptSchoolLevel(rs.getString("concept_school_level"));
            concept.setConceptGradeLevel(rs.getString("concept_grade_level"));
            concept.setConceptSemester(rs.getString("concept_semester"));
            concept.setConceptDescription(rs.getString("concept_description"));
            concept.setConceptChapterId(rs.getInt("concept_chapter_id"));
            concept.setConceptChapterName(rs.getString("concept_chapter_name"));
            concept.setConceptAchievementId(rs.getInt("concept_achievement_id"));
            concept.setConceptAchievementName(rs.getString("concept_Achievement_name"));
            return concept;
        };
    }
}
