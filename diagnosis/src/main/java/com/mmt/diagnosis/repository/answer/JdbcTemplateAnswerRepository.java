package com.mmt.diagnosis.repository.answer;

import com.mmt.diagnosis.repository.AnswerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@Primary
public class JdbcTemplateAnswerRepository implements AnswerRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateAnswerRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Long studentId, Long testId) {
        String subQuery = "SELECT test_item_id FROM tests_items WHERE test_id=?";
        String insertSql = String.format("INSERT INTO answers (test_item_id) %s", subQuery);
        jdbcTemplate.update(insertSql, testId);
        String updateSql = String.format("UPDATE answers SET student_id=? WHERE test_item_id IN (%s)", subQuery);
        jdbcTemplate.update(updateSql, studentId, testId);
    }

}
