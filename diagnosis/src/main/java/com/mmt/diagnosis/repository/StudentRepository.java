package com.mmt.diagnosis.repository;

import com.mmt.diagnosis.domain.Student;

import java.util.List;

public interface StudentRepository {

    void saveStudent(Student student);

    List<Student> findAll(String teacherId);

    boolean isStudentNotExist(int id);

    void updateStudent(Student student);

    void deleteStudent(int id);


}
