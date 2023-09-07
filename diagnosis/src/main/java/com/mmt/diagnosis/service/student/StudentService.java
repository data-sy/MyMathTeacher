package com.mmt.diagnosis.service.student;

import com.mmt.diagnosis.domain.Student;
import com.mmt.diagnosis.dto.student.StudentCreateRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.dto.student.StudentUpdateRequest;
import com.mmt.diagnosis.repository.student.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public StudentResponse findOne(Long studentId){
        return StudentConverter.convertToStudentResponse(studentRepository.findById(studentId));
    }

    public String findName(Long studentId){
        return studentRepository.findName(studentId).getStudentName();
    }

    public void update(StudentUpdateRequest request) {
        if(studentRepository.isStudentNotExist(request.getStudentId())){
            throw new IllegalArgumentException();
        }
        Student student = StudentConverter.convertToStudent(request);
        studentRepository.update(student);
    }

    public void delete(Long studentId) {
        if(studentRepository.isStudentNotExist(studentId)){
            throw new IllegalArgumentException();
        }
        studentRepository.delete(studentId);
    }

}
