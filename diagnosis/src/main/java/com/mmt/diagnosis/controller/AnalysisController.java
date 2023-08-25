package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.AI.AIInputRequest;
import com.mmt.diagnosis.dto.AI.AIInputResponse;
import com.mmt.diagnosis.dto.AI.AIOutputRequest;
import com.mmt.diagnosis.service.answer.AnswerService;
import com.mmt.diagnosis.service.probability.ProbabilityService;
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
     * AI input 데이터 플라스크에 제공
     */
    @GetMapping("/ai-input")
    public AIInputResponse getAIInput(@RequestBody AIInputRequest request){
        return answerService.findAIInput(request.getStudentTestId());
    }

    /**
     * AI output 데이터 DB에 저장
     */
    @PostMapping("/ai-output")
    public void create(@RequestBody AIOutputRequest request){
        probabilityService.create(request.getStudentTestId(), request.getProbabilityList());
    }

}
