package com.mmt.diagnosis.domain;

import lombok.Data;

@Data
public class Answer {
    // students 테이블 + tests 테이블 + items 테이블 + tests_items 테이블

    private Long answerId;
    private Long studentId;
    private Long testId;
    private String testName;
    private Long itemId;
    private Integer answerCode;

}
