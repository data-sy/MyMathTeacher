package com.mmt.diagnosis.repository.answer;

import com.mmt.diagnosis.domain.Answer;
import com.mmt.diagnosis.domain.AnswerCode;
import com.mmt.diagnosis.repository.AnswerRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
@Primary
public class JdbcTemplateAnswerRepository implements AnswerRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateAnswerRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Answer answer) {
        Long studentTestId = answer.getStudentTestId();
        List<AnswerCode> answerCodeList = answer.getAnswerCodeList();
        String sql =  "INSERT INTO answers (student_test_id, item_id, answer_code) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setLong(1, studentTestId);
                AnswerCode answerCode = answerCodeList.get(i);
                ps.setLong(2, answerCode.getItemId());
                ps.setInt(3, answerCode.getAnswerCode());
            }
            @Override
            public int getBatchSize() {
                return answerCodeList.size();
            }
        });
    }
}
