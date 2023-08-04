package com.mmt.diagnosis.dto.answer;

import com.mmt.diagnosis.dto.testItem.TestItemsResponse;
import lombok.Data;

import java.util.List;

@Data
public class AnswerRecordResponse {

    private String testName;
    private String testComments;
    private String studentName;
    // Long itemId, String itemAnswer, String itemImagePath, int testItemNumber
    private List<TestItemsResponse> testItemsResponses;

}
