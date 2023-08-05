package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.AI.AIInputRequest;
import com.mmt.diagnosis.dto.AI.AIInputResponse;
import com.mmt.diagnosis.dto.AI.AIOutputRequest;
import com.mmt.diagnosis.service.ProbabilityService;
import com.mmt.diagnosis.service.answer.AnswerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalysisController {

    private final AnswerService answerService;
    private final ProbabilityService probabilityService;

    public AnalysisController(AnswerService answerService, ProbabilityService probabilityService) {
        this.answerService = answerService;
        this.probabilityService = probabilityService;
    }


    /**
     * AI(플라스크)로 보낼 입력데이터
     * 리팩토링 : 답안을 기록하지 않은 stId에 대해서도 찾을 수 있게 해놨으므로, 프론트단에서 클릭할 수 없게 검증
     */
    @GetMapping("/analysis")
    public AIInputResponse getAIInput(@RequestBody AIInputRequest request){
        return answerService.findAIInput(request.getStudentTestId());
    }

    /**
     * AI(플라스크)에서 받은 inference 결과를 DB에 저장하기
     */
    @PostMapping("/analysis")
    public void create(@RequestBody AIOutputRequest request){
        probabilityService.create(request.getProbabilityList());
    }


}
