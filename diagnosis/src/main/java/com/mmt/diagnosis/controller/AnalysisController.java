package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.AI.AIInputRequest;
import com.mmt.diagnosis.service.answer.AnswerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnalysisController {

    private final AnswerService answerService;

    public AnalysisController(AnswerService answerService) {
        this.answerService = answerService;
    }


    /**
     * 현재 : Spring에서 전처리 : input에 딱 맞는 3치원 배열 모양으로
     * 리팩토링 : 그대로 보내고 Python에서 전처리하는 게 성능이 더 좋다면 이 방법으로 리팩토링
     * 리팩토링 : 답안을 기록하지 않은 stId에 대해서도 찾을 수 있게 해놨으므로, 프론트단에서 클릭할 수 없게 검증
     */
    @GetMapping("/analysis")
    public List<List<List<Integer>>> getAIInput(@RequestBody AIInputRequest request){
        return answerService.findAIInput(request.getStudentTestId());
    }
}
