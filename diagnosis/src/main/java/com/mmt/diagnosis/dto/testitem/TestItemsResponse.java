package com.mmt.diagnosis.dto.testItem;

import lombok.Data;

@Data
public class TestItemsResponse {

    private int testItemNumber;
    private String itemImagePath;
    private String itemAnswer;

}
