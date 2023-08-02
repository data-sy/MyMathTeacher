package com.mmt.diagnosis.repository;

import com.mmt.diagnosis.domain.Student;

import java.util.List;

public interface StudentRepository {

    void save(Student student);

    List<Student> findAll(String teacherId);

    Student findById(Long studentId);

    Student findName(Long studentId);

    boolean isStudentNotExist(Long id);

    void update(Student student);

    void delete(Long studentId);

}
