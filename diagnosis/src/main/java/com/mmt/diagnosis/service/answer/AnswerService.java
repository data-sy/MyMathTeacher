package com.mmt.diagnosis.service.answer;

import com.mmt.diagnosis.dto.answer.AnswerRequest;
import com.mmt.diagnosis.repository.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void create(AnswerRequest request) {
        answerRepository.save(AnswerConverter.convertToAnswer(request));
    }

}
