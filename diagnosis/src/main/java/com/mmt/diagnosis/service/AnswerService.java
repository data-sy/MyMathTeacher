package com.mmt.diagnosis.service;

import com.mmt.diagnosis.dto.download.DownloadRequest;
import com.mmt.diagnosis.repository.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void create(DownloadRequest request) {
        answerRepository.save(request.getStudentId(), request.getTestId());
    }
//
//    public List<StudentResponse> findStudents(String teacherId){
//        return StudentConverter.convertListToStudentResponseList(studentRepository.findAll(teacherId));
//    }
//
//    public StudentResponse findOne(Long studentId){
//        return StudentConverter.convertToStudentResponse(studentRepository.findById(studentId));
//    }
//
//    public void update(StudentUpdateRequest request) {
//        if(studentRepository.isStudentNotExist(request.getStudentId())){
//            throw new IllegalArgumentException();
//        }
//        Student student = StudentConverter.convertToStudent(request);
//        studentRepository.update(student);
//    }
//
//    public void delete(Long studentId) {
//        if(studentRepository.isStudentNotExist(studentId)){
//            throw new IllegalArgumentException();
//        }
//        studentRepository.delete(studentId);
//    }

}
