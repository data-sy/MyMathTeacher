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
        String sql =  "INSERT INTO answers (student_id, test_id, item_id) SELECT ? AS student_id, test_id, item_id FROM tests_items WHERE test_id=?";
        jdbcTemplate.update(sql, studentId, testId);
        // Q. 한 번 실행 시킬 때 마다 answer_id 11개가 날라감. why?????
    }

}
