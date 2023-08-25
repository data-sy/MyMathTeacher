package com.mmt.diagnosis.service.answer;

import com.mmt.diagnosis.dto.AI.AIInputResponse;
import com.mmt.diagnosis.dto.answer.AnswerCreateRequest;
import com.mmt.diagnosis.repository.AnswerRepository;
import com.mmt.diagnosis.service.studentTest.StudentTestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final StudentTestService studentTestService;

    public AnswerService(AnswerRepository answerRepository, StudentTestService studentTestService) {
        this.answerRepository = answerRepository;
        this.studentTestService = studentTestService;
    }

    public void create(AnswerCreateRequest request) {
        answerRepository.save(AnswerConverter.convertToAnswer(request));
    }

    public AIInputResponse findAIInput(Long studentTestId){
        AIInputResponse aiInputResponse = new AIInputResponse(studentTestId);
        List<List<List<Integer>>> answerCodeResponseList = new ArrayList<>();
        // 조건에 맞는 student_test_id들 찾기
        List<Long> stIdList = studentTestService.findBefore(studentTestId);
        // student_test_id별 정오답 기록을 answerCodeList에 넣기
        for (Long stId : stIdList){
            answerCodeResponseList.add(AnswerConverter.convertToIntegerList(answerRepository.findAnswerCode(stId)));
        }
        aiInputResponse.setAnswerCodeResponseList(answerCodeResponseList);
        return aiInputResponse;
    }
}
