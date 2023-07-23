package com.mmt.diagnosis.repository;

import com.mmt.diagnosis.domain.Student;
import com.mmt.diagnosis.domain.User;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {

    void saveStudent(Student student);

    boolean isStudentNotExist(int id);

    void updateStudent(Student student);

    void deleteStudent(int id);


}
