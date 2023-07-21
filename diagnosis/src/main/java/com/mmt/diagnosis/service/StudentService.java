package com.mmt.diagnosis.service;

import com.mmt.diagnosis.domain.Student;
import com.mmt.diagnosis.dto.student.StudentConverter;
import com.mmt.diagnosis.dto.student.StudentCreateRequest;
import com.mmt.diagnosis.dto.user.UserRequest;
import com.mmt.diagnosis.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(StudentCreateRequest request) {
        Student student = StudentConverter.convertToStudent(request);
        studentRepository.saveStudent(student);
    }
//    public void getStudents(UserRequest request) {
//        studentRepository.saveUser(request.getUserId(), request.getUserPassword(), request.getUserName(), request.getUserPhone());
//    }
//    public void updateStudent(UserRequest request) {
//        studentRepository.saveUser(request.getUserId(), request.getUserPassword(), request.getUserName(), request.getUserPhone());
//    }
//    public void deleteStudent(UserRequest request) {
//        studentRepository.saveUser(request.getUserId(), request.getUserPassword(), request.getUserName(), request.getUserPhone());
//    }

}
