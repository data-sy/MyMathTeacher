package com.mmt.diagnosis.repository.probability;

import com.mmt.diagnosis.domain.Item;
import com.mmt.diagnosis.domain.ItemProbability;
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

    @Override
    public List<String> findConceptNameUnder50(Long studentTestId, int depth) {
        String sql = "SELECT c.concept_name FROM concepts c JOIN probabilities p ON c.concept_id=p.concept_id JOIN answers a ON a.answer_id=p.answer_id WHERE a.student_test_id = ? AND p.to_concept_depth=? AND p.probability_percent<= 0.5";
        return jdbcTemplate.queryForList(sql, String.class , studentTestId, depth);
    }

    @Override
    public List<String> findToConceptName(Long studentTestId) {
        String sql = "SELECT c.concept_name FROM concepts c JOIN probabilities p ON c.concept_id=p.concept_id JOIN answers a ON a.answer_id=p.answer_id WHERE a.student_test_id = ? AND p.to_concept_depth > 0 ORDER BY p.probability_percent";
        return jdbcTemplate.queryForList(sql, String.class , studentTestId);
    }

    @Override
    public List<String> findFromConceptName(Long studentTestId) {
        String subQuery = "SELECT c.concept_id FROM answers a JOIN items i ON i.item_id=a.item_id JOIN concepts c ON c.concept_id=i.concept_id JOIN probabilities p ON p.answer_id=a.answer_id WHERE p.probability_percent>0.6 AND a.student_test_id = ?";
        String sql = String.format("SELECT c.concept_name FROM concepts c JOIN knowledge_tags k ON c.concept_id = k.from_concept_id WHERE k.to_concept_id IN (%s);", subQuery);
        return jdbcTemplate.queryForList(sql, String.class , studentTestId);
    }

    @Override
    public List<ItemProbability> findItemProbability(Long studentTestId) {
        String join = "JOIN items i ON i.item_id = a.item_id JOIN tests_items ti ON ti.item_id = a.item_id JOIN probabilities p ON p.answer_id = a.answer_id JOIN concepts c ON c.concept_id = i.concept_id";
        String sql = String.format("SELECT i.item_id, ti.test_item_number, i.item_image_path, c.concept_id, c.concept_name, p.probability_percent FROM answers a %s WHERE a.student_test_id = ? AND p.to_concept_depth = 0", join);
        return jdbcTemplate.query(sql, itemProbabilityRowMapper(), studentTestId);
    }

    @Override
    public List<Item> findItem(Long studentTestId, int toConceptDepth) {
        String join = "JOIN concepts c ON p.concept_id=c.concept_id JOIN items i ON i.concept_id=c.concept_id";
        String suqQuery = "SELECT answer_id FROM answers WHERE student_test_id = ?";
        String sql = String.format("SELECT i.item_id, i.item_image_path, i.concept_id, c.concept_name, p.probability_percent FROM probabilities p %s WHERE p.answer_id IN (%s) AND p.to_concept_depth = ?", join, suqQuery);
        return jdbcTemplate.query(sql, itemRowMapper(toConceptDepth), studentTestId, toConceptDepth);
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

    private RowMapper<ItemProbability> itemProbabilityRowMapper() {
        return (rs, rowNum) -> {
            ItemProbability itemProbability = new ItemProbability();
            itemProbability.setItemId(rs.getLong("item_id"));
            itemProbability.setTestItemNumber(rs.getInt("test_item_number"));
            itemProbability.setItemImagePath(rs.getString("item_image_path"));
            itemProbability.setConceptId(rs.getInt("concept_id"));
            itemProbability.setConceptName(rs.getString("concept_name"));
            itemProbability.setProbabilityPercent(rs.getDouble("probability_percent"));
            return itemProbability;
        };
    }

    private RowMapper<Item> itemRowMapper(int toConceptDepth) {
        return (rs, rowNum) -> {
            Item item = new Item();
            item.setItemId(rs.getLong("item_id"));
            item.setItemImagePath(rs.getString("item_image_path"));
            item.setConceptId(rs.getInt("concept_id"));
            item.setConceptName(rs.getString("concept_name"));
            item.setToConceptDepth(toConceptDepth);
            item.setProbabilityPercent(rs.getDouble("probability_percent"));
            return item;
        };
    }

}
