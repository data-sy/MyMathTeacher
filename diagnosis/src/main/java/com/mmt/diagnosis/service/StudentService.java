package com.mmt.diagnosis.service;

import com.mmt.diagnosis.domain.Student;
import com.mmt.diagnosis.dto.student.StudentConverter;
import com.mmt.diagnosis.dto.student.StudentCreateRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.dto.student.StudentUpdateRequest;
import com.mmt.diagnosis.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void join(StudentCreateRequest request) {
        Student student = StudentConverter.convertToStudent(request);
        studentRepository.save(student);
    }

    public List<StudentResponse> findStudents(String teacherId){
        return StudentConverter.convertListToStudentResponseList(studentRepository.findAll(teacherId));
    }

    public StudentResponse findOne(int studentId){
        return StudentConverter.convertToStudentResponse(studentRepository.findById(studentId));
    }

    public void update(StudentUpdateRequest request) {
        if(studentRepository.isStudentNotExist(request.getStudentId())){
            throw new IllegalArgumentException();
        }
        Student student = StudentConverter.convertToStudent(request);
        studentRepository.update(student);
    }
    public void delete(int studentId) {
        if(studentRepository.isStudentNotExist(studentId)){
            throw new IllegalArgumentException();
        }
        studentRepository.delete(studentId);
    }

}
