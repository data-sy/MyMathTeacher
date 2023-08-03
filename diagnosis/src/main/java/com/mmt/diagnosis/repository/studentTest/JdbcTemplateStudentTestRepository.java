package com.mmt.diagnosis.repository.studentTest;

import com.mmt.diagnosis.domain.StudentTests;
import com.mmt.diagnosis.repository.StudentTestRepository;
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
    public List<StudentTests> findByStudentId(Long studentId) {
        String sql = "SELECT st.student_test_id, t.test_name, t.test_comments, \n" +
                "CASE WHEN EXISTS (SELECT 1 FROM answers a WHERE a.student_test_id = st.student_test_id) \n" +
                "THEN TRUE ELSE FALSE END AS is_record \n" +
                "FROM students_tests st JOIN tests t ON st.test_id = t.test_id\n" +
                "WHERE st.student_id = ?;";
        return jdbcTemplate.query(sql, studentTestsRowMapper(), studentId);
    }

    private RowMapper<StudentTests> studentTestsRowMapper() {
        return (rs, rowNum) -> {
            StudentTests studentTests = new StudentTests();
            studentTests.setStudentTestId(rs.getLong("student_test_id"));
            studentTests.setTestName(rs.getString("test_name"));
            studentTests.setTestComments(rs.getString("test_comments"));
            studentTests.setRecord(rs.getBoolean("is_Record"));
            return studentTests;
        };
    }
}
