package com.mmt.diagnosis.domain;

import lombok.Data;

import java.util.List;

@Data
public class Answer {

    private Long studentTestId;
    private List<AnswerCode> answerCodeList;

}
