package com.mmt.diagnosis.dto.viewDetail;

import com.mmt.diagnosis.dto.testItem.TestItemsResponse;
import lombok.Data;

import java.util.List;

@Data
public class ViewDetailResponse {

    private String testName;
    private String testComments;
    private String studentName;
    // Long itemId, String itemAnswer, String itemImagePath, int testItemNumber
    private List<TestItemsResponse> testItemsResponses;

}
