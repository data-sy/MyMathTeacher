package com.mmt.diagnosis.repository.test;

import com.mmt.diagnosis.domain.Test;
import com.mmt.diagnosis.repository.TestRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcTemplateTestRepository implements TestRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateTestRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 리팩토링할 것 : 진단id가 null이거나 로그인한 선생님을 teacherId로 가지는 학생과 연관이 있는 학습지만 보여주기
    @Override
    public List<Test> findAll(){
        String sql = "SELECT * FROM tests";
        return jdbcTemplate.query(sql, testRowMapper());
    }

    private RowMapper<Test> testRowMapper() {
        return (rs, rowNum) -> {
            Test test = new Test();
            test.setTestId(rs.getInt("test_id"));
            Optional.ofNullable(rs.getString("test_name")).ifPresent(test::setTestName);
            Optional.ofNullable(rs.getString("test_comments")).ifPresent(test::setTestComments);
            test.setTestTimestamp(rs.getTimestamp("test_timestamp").toLocalDateTime());
            int diagnosticTestId = rs.getInt("diagnostic_test_id");
            if (!rs.wasNull()) {
                test.setDiagnosticTestId(diagnosticTestId);
            }
            return test;
        };
    }
}
