package com.mmt.diagnosis.service;

import com.mmt.diagnosis.domain.Student;
import com.mmt.diagnosis.dto.student.StudentConverter;
import com.mmt.diagnosis.dto.student.StudentCreateRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.dto.student.StudentUpdateRequest;
import com.mmt.diagnosis.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<StudentResponse> getStudents(String teacherId){
        return StudentConverter.convertListToStudentResponseList(studentRepository.findAll(teacherId));
    }

    public void updateStudent(StudentUpdateRequest request) {
        if(studentRepository.isStudentNotExist(request.getStudentId())){
            throw new IllegalArgumentException();
        }
        Student student = StudentConverter.convertToStudent(request);
        studentRepository.updateStudent(student);
    }
    public void deleteStudent(int id) {
        if(studentRepository.isStudentNotExist(id)){
            throw new IllegalArgumentException();
        }
        studentRepository.deleteStudent(id);
    }

}
