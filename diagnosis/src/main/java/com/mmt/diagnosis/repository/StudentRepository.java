package com.mmt.diagnosis.repository;

import com.mmt.diagnosis.domain.Student;

import java.util.List;

public interface StudentRepository {

    void save(Student student);

    List<Student> findAll(String teacherId);

    Student findById(int studentId);

    boolean isStudentNotExist(int id);

    void update(Student student);

    void delete(int studentId);

}
