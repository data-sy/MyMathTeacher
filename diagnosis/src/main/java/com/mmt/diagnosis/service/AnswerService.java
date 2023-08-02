package com.mmt.diagnosis.service;

import com.mmt.diagnosis.dto.diagnosticTest.DiagnosticTestRequest;
import com.mmt.diagnosis.repository.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void create(DiagnosticTestRequest request) {
        answerRepository.save(request.getStudentId(), request.getTestId());
    }

}
