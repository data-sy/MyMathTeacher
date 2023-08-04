package com.mmt.diagnosis.dto.preview;

import com.mmt.diagnosis.dto.testItem.TestItemsResponse;
import lombok.Data;

import java.util.List;

@Data
public class PreviewResponse {

    private Long studentTestId;
    private String testName;
    private String testComments;
    private String studentName;
    // Long itemId, String itemAnswer, String itemImagePath, int testItemNumber
    private List<TestItemsResponse> testItemsResponses;

}
