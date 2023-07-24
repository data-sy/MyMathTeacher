package com.mmt.diagnosis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    private int answerId;
    private int studentId;
    private int testItemId;
    private int answerCode;
    private double answerProbability;
    private LocalDateTime answerTimestamp;

}
