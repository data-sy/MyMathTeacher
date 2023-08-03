package com.mmt.diagnosis.service.studentTest;

import com.mmt.diagnosis.domain.StudentTests;
import com.mmt.diagnosis.dto.answer.IsRecordResponse;
import com.mmt.diagnosis.repository.StudentTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentTestService {

    private final StudentTestRepository studentTestRepository;

    public StudentTestService(StudentTestRepository studentTestRepository) {
        this.studentTestRepository = studentTestRepository;
    }

    public void create(Long studentId, Long testId){
        studentTestRepository.save(studentId, testId);
    }

    public List<IsRecordResponse> findAll(Long studentId){
        List<StudentTests> studentTestsList = studentTestRepository.findByStudentId(studentId);
        return StudentTestConverter.convertListToStudentTestResponseList(studentTestsList);
    }

}
