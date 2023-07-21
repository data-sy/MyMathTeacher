package com.mmt.diagnosis.repository.student;

import com.mmt.diagnosis.domain.Student;
import com.mmt.diagnosis.domain.User;
import com.mmt.diagnosis.repository.StudentRepository;
import com.mmt.diagnosis.repository.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class JdbcTemplateStudentRepository implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateStudentRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveStudent(Student student) {
        String sql = "INSERT INTO students(student_name, student_phone, student_birthdate, student_school, student_comments, teacher_id ) VALUES(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, student.getStudentName(), student.getStudentPhone(), student.getStudentBirthdate(), student.getStudentSchool(), student.getStudentComments(), student.getTeacherId());
    }
//
//    @Override
//    public Optional<User> findById(String id) {
//        String sql = "SELECT * FROM users WHERE user_id = ?";
//        List<User> result = jdbcTemplate.query(sql, userRowMapper(), id);
//        return Optional.empty();
//    }
//
//    private RowMapper<User> userRowMapper() {
//        return (rs, rowNum) -> {
//            User user = new User();
//            user.setUserId(rs.getString("user_id"));
//            user.setUserPassword(rs.getString("user_password"));
//            user.setUserName(rs.getString("user_name"));
//            user.setUserPhone(rs.getString("user_phone"));
//            return user;
//        };
//    }
}
