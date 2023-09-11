package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.AI.AIInputRequest;
import com.mmt.diagnosis.dto.AI.AIInputResponse;
import com.mmt.diagnosis.dto.AI.AIOutputRequest;
import com.mmt.diagnosis.dto.AI.AnalysisRequest;
import com.mmt.diagnosis.dto.details.DetailsResponse;
import com.mmt.diagnosis.service.answer.AnswerService;
import com.mmt.diagnosis.service.probability.ProbabilityService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
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
    @GetMapping("/predict")
    public AIInputResponse getAIInput(@RequestBody AIInputRequest request){
        return answerService.findAIInput(request.getStudentTestId());
    }

    /**
     * AI output 데이터 DB에 저장
     */
    @PostMapping("/predict")
    public void create(@RequestBody AIOutputRequest request){
        probabilityService.create(request.getStudentTestId(), request.getProbabilityList());
    }

    /**
     * 선택한 학습지의 분석 결과 보기
     */
    @GetMapping("/analysis")
    public DetailsResponse getDetails(@RequestBody AnalysisRequest request){
        return probabilityService.findDetails(request.getStudentTestId());
    }

}
