package com.mmt.diagnosis.domain;

import lombok.Data;

@Data
public class TestItems {
    // tests 테이블 + items 테이블 + tests_items 테이블

//    private Long testId;
//    private Long itemId;
    private int testItemNumber;
    private String itemImagePath;
    String itemAnswer;

}
