package com.mmt.diagnosis.repository.student;

import com.mmt.diagnosis.domain.Student;
import com.mmt.diagnosis.repository.StudentRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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

    @Override
    public boolean isStudentNotExist(int id){
        String readSql = "SELECT * FROM students WHERE student_id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    @Override
    public void updateStudent(Student student){
        String sql = "UPDATE students SET student_name=? , student_phone=?, student_birthdate=?, student_school=?, student_comments=?, teacher_id=? WHERE student_id = ?";
        jdbcTemplate.update(sql, student.getStudentName(), student.getStudentPhone(), student.getStudentBirthdate(), student.getStudentSchool(), student.getStudentComments(), student.getTeacherId(), student.getStudentId());
    }

    @Override
    public void deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE student_id = ?";
        jdbcTemplate.update(sql, id);
    }

}
