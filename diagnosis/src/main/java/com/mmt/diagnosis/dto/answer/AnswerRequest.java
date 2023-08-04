package com.mmt.diagnosis.dto.answer;

import lombok.Data;

import java.util.List;

@Data
public class AnswerRequest {

    private Long studentTestId;
    private List<AnswerCodeRequest> answerCodeRequestList;

}
