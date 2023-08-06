package com.mmt.diagnosis.repository.probability;

import com.mmt.diagnosis.domain.Probability;
import com.mmt.diagnosis.repository.ProbabilityRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@Primary
public class JdbcTemplateProbabilityRepository implements ProbabilityRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateProbabilityRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(List<Probability> probabilities) {
        String sql =  "INSERT INTO probabilities (answer_id, concept_id, to_concept_depth, probability_percent) VALUES (?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Probability probability = probabilities.get(i);
                ps.setLong(1, probability.getAnswerId());
                ps.setInt(2, probability.getConceptId());
                ps.setInt(3, probability.getToConceptDepth());
                ps.setDouble(4, probability.getProbabilityPercent());
            }
            @Override
            public int getBatchSize() {
                return probabilities.size();
            }
        });
    }

    @Override
    public List<Probability> findToConcept(Long answerId, int conceptId) {
        String sql = "SELECT k.to_concept_id, c.skill_id FROM concepts c JOIN knowledge_tags k ON c.concept_id=k.to_concept_id WHERE k.from_concept_id = ?";
        return jdbcTemplate.query(sql, idsRowMapper(answerId), conceptId);
    }

    private RowMapper<Probability> idsRowMapper(Long answerId) {
        return (rs, rowNum) -> {
            Probability probability = new Probability();
            probability.setAnswerId(answerId);
            probability.setConceptId(rs.getInt("to_concept_id"));
            probability.setSkillId(rs.getInt("skill_id"));
            return probability;
        };
    }

}
