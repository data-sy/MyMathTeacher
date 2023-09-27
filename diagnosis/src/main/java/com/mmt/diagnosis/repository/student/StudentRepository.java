package com.mmt.diagnosis.repository.student;

import com.mmt.diagnosis.domain.Student;

import java.util.List;

public interface StudentRepository {

    void save(Student student);

    List<Student> findAll(Long teacherId);

//    Student findById(Long studentId);

    Student findName(Long studentId);

    boolean isStudentNotExist(Long studentId);

    void update(Student student);

    void delete(Long studentId);

}
