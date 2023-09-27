package com.mmt.diagnosis.domain;

import lombok.Data;

@Data
public class TestItems {
    // tests_items 테이블 + tests 테이블 + items 테이블

    // items
    private Long itemId;
    private String itemAnswer;
    private String itemImagePath;
    // tests_items
    private int testItemNumber;

    // 디버깅 용 : System.out.println(testItems);
    @Override
    public String toString() {
        return String.format("testItems{ itemId = %d, itemAnswer = %s, itemImagePath = %s, testItemNumber = %d }", itemId, itemAnswer, itemImagePath, testItemNumber);
    }

}
