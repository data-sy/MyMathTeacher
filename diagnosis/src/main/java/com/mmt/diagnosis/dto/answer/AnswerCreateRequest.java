package com.mmt.diagnosis.dto.answer;

import lombok.Data;

import java.util.List;

@Data
public class AnswerCreateRequest {

    private Long studentTestId;
    private List<AnswerCodeCreateRequest> answerCodeCreateRequestList;

}
