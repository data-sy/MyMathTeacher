package com.mmt.diagnosis.repository.test;

import com.mmt.diagnosis.domain.Test;
import com.mmt.diagnosis.dto.test.IsRecordResponse;
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

    // student_id에 따른 test 목록 가져오기 : 컬럼은 test_name(TESTS 테이블), answer_code(ANSWERS 테이블)
    @Override
    public List<IsRecordResponse> findByStudentId(Long studentId){
        String sql = "SELECT t.test_name, a.answer_code FROM tests t INNER JOIN tests_items ti ON t.test_id = ti.test_id INNER JOIN answers a ON ti.test_item_id = a.test_item_id WHERE a.student_id = ?";
        return jdbcTemplate.query(sql, isRecordRowMapper(), studentId);
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
            test.setTestTimestamp(rs.getTimestamp("test_timestamp").toLocalDateTime());
            Long diagnosticTestId = rs.getLong("diagnostic_test_id");
            if (!rs.wasNull()) {
                test.setDiagnosticTestId(diagnosticTestId);
            }
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
    private RowMapper<IsRecordResponse> isRecordRowMapper() {
        return (rs, rowNum) -> {
            IsRecordResponse isRecord = new IsRecordResponse();
            isRecord.setTestName(rs.getString("test_name"));
//            isRecord.setAnswerCode(rs.getInt("answer_code"));
            return isRecord;
        };
    }
}
