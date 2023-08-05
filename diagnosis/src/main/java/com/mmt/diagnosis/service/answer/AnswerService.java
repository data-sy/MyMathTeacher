package com.mmt.diagnosis.service.answer;

import com.mmt.diagnosis.domain.AnswerCode;
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

    public List<List<List<Integer>>> findAIInput(Long studentTestId){
        // 최종 배열 선언
        List<List<AnswerCode>> result = new ArrayList<>();
        // 조건에 맞는 student_test_id들 찾기
        List<Long> stIdList = studentTestService.findBefore(studentTestId);
        // student_test_id별 정오답 기록을 최종 배열에 넣기
        for (Long stId : stIdList){
            result.add(answerRepository.findAnswerCode(stId));
        }
        // List<List<AnswerCode>>를 List<List<List<Integer>로 컨버터
        return AnswerConverter.convertToThreeDimensionalList(result);
    }
}
