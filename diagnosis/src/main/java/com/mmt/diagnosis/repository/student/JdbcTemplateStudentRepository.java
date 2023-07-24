package com.mmt.diagnosis.repository.student;

import com.mmt.diagnosis.domain.Student;
import com.mmt.diagnosis.repository.StudentRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Primary
public class JdbcTemplateStudentRepository implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateStudentRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Student student) {
        String sql = "INSERT INTO students(student_name, student_phone, student_birthdate, student_school, student_comments, teacher_id ) VALUES(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, student.getStudentName(), student.getStudentPhone(), student.getStudentBirthdate(), student.getStudentSchool(), student.getStudentComments(), student.getTeacherId());
    }

    @Override
    public List<Student> findAll(String teacherId){
        String sql = "SELECT * FROM students WHERE teacher_id = ?";
        return jdbcTemplate.query(sql, studentRowMapper(), teacherId);
    }

    @Override
    public Student findById(int studentId){
        System.out.println("studentId : " + studentId);
        String sql = "SELECT * FROM students WHERE student_id = ?";
        return jdbcTemplate.query(sql, studentRowMapper(), studentId).get(0);
    }

    @Override
    public boolean isStudentNotExist(int id){
        String readSql = "SELECT * FROM students WHERE student_id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    @Override
    public void update(Student student){
        String sql = "UPDATE students SET student_name=? , student_phone=?, student_birthdate=?, student_school=?, student_comments=?, teacher_id=? WHERE student_id = ?";
        jdbcTemplate.update(sql, student.getStudentName(), student.getStudentPhone(), student.getStudentBirthdate(), student.getStudentSchool(), student.getStudentComments(), student.getTeacherId(), student.getStudentId());
    }

    @Override
    public void delete(int studentId) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        jdbcTemplate.update(sql, studentId);
    }

    private RowMapper<Student> studentRowMapper() {
        return (rs, rowNum) -> {
            Student student = new Student();
            student.setStudentId(rs.getInt("student_id"));
            student.setStudentName(rs.getString("student_name"));
            student.setStudentPhone(rs.getString("student_phone"));
            student.setStudentBirthdate(rs.getDate("student_birthdate").toLocalDate());
            student.setStudentSchool(rs.getString("student_school"));
            student.setStudentComments(rs.getString("student_comments"));
            student.setTeacherId(rs.getString("teacher_id"));
            return student;
        };
    }
}
