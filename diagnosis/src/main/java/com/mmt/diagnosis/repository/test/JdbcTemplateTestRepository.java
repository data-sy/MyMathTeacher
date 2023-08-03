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

    // 리팩토링할 것 : 진단학습지나 로그인한 선생님을 teacherId로 가지는 학생과 연관이 있는 학습지만 보여주기
    @Override
    public List<Test> findAll(){
        String sql = "SELECT * FROM tests";
        return jdbcTemplate.query(sql, testRowMapper());
    }

    @Override
    public Test findNameComments(Long testId){
        String sql = "SELECT test_name, test_comments FROM tests WHERE test_id = ?";
        return jdbcTemplate.queryForObject(sql, testNameCommentsRowMapper(), testId);
    }

    private RowMapper<Test> testRowMapper() {
        return (rs, rowNum) -> {
            Test test = new Test();
            test.setTestId(rs.getLong("test_id"));
            Optional.ofNullable(rs.getString("test_name")).ifPresent(test::setTestName);
            Optional.ofNullable(rs.getString("test_comments")).ifPresent(test::setTestComments);
            return test;
        };
    }
    private RowMapper<Test> testNameCommentsRowMapper() {
        return (rs, rowNum) -> {
            Test test = new Test();
            test.setTestName(rs.getString("test_name"));
            test.setTestComments(rs.getString("test_comments"));
            return test;
        };
    }

}
