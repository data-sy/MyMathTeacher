package com.mmt.diagnosis.repository.studentTest;

import com.mmt.diagnosis.domain.StudentTests;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Primary
public class JdbcTemplateStudentTestRepository implements StudentTestRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateStudentTestRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Long studentId, Long testId) {
        String sql = "INSERT INTO students_tests (student_id, test_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, studentId, testId);
    }

    @Override
    public void save(Long studentId, Long testId, Long diagnosisId) {
        String sql = "INSERT INTO students_tests (student_id, test_id, diagnosis_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, studentId, testId, diagnosisId);
    }

    @Override
    public List<StudentTests> findByStudentId(Long studentId) {
        // answers 테이블애 student_test_id가 있는지 없는지에 따라 t/f를 반환하게 만듦
        String sql = "SELECT st.student_test_id, t.test_id, t.test_name, t.test_comments, \n" +
                "CASE WHEN EXISTS (SELECT 1 FROM answers a WHERE a.student_test_id = st.student_test_id) \n" +
                "THEN TRUE ELSE FALSE END AS is_record \n" +
                "FROM students_tests st JOIN tests t ON st.test_id = t.test_id\n" +
                "WHERE st.student_id = ?;";
        return jdbcTemplate.query(sql, studentTestsRowMapper(), studentId);
    }

    @Override
    public StudentTests findIds(Long studentTestId) {
        String sql = "SELECT student_id, test_id FROM students_tests WHERE student_test_id=?";
        return jdbcTemplate.queryForObject(sql, idsRowMapper(), studentTestId);
    }

    @Override
    public List<Long> findStudentTestIds(Long studentTestId) {
        // 조건1 : 해당 학생
        String condition1 = "student_id = (SELECT student_id FROM students_tests WHERE student_test_id=?)";
        // 조건2 : 해당 학습지보다 이전의 학습지
        String condition2 = "student_test_timestamp <= (SELECT student_test_timestamp FROM students_tests WHERE student_test_id=?)";
        // 조건3 : 답안 기록이 있는 것들
        String condition3 = "EXISTS (SELECT 1 FROM answers a WHERE a.student_test_id = st.student_test_id)";
        String sql = String.format("SELECT student_test_id FROM students_tests st WHERE %s AND %s AND %s", condition1, condition2, condition3);
        return jdbcTemplate.queryForList(sql, Long.class, studentTestId, studentTestId);
    }

    @Override
    public StudentTests findDetails(Long studentTestId) {
        String sql = "SELECT s.student_name, s.student_birthdate, t.test_name FROM students_tests st JOIN students s ON s.student_id=st.student_id JOIN tests t ON t.test_id=st.test_id WHERE st.student_test_id = ?";
        return jdbcTemplate.queryForObject(sql, detailsRowMapper(), studentTestId);
    }

    private RowMapper<StudentTests> studentTestsRowMapper() {
        return (rs, rowNum) -> {
            StudentTests studentTests = new StudentTests();
            studentTests.setStudentTestId(rs.getLong("student_test_id"));
            studentTests.setTestId(rs.getLong("test_id"));
            studentTests.setTestName(rs.getString("test_name"));
            studentTests.setTestComments(rs.getString("test_comments"));
            studentTests.setRecord(rs.getBoolean("is_Record"));
            return studentTests;
        };
    }
    private RowMapper<StudentTests> idsRowMapper() {
        return (rs, rowNum) -> {
            StudentTests studentTests = new StudentTests();
            studentTests.setStudentId(rs.getLong("student_id"));
            studentTests.setTestId(rs.getLong("test_id"));
            return studentTests;
        };
    }
    private RowMapper<StudentTests> detailsRowMapper() {
        return (rs, rowNum) -> {
            StudentTests studentTests = new StudentTests();
            studentTests.setStudentName(rs.getString("student_name"));
            studentTests.setStudentBirthdate(rs.getDate("student_birthdate").toLocalDate());
            studentTests.setTestName(rs.getString("test_name"));
            return studentTests;
        };
    }
}
