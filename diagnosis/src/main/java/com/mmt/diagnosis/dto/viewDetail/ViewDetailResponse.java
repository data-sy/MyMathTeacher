package com.mmt.diagnosis.dto.viewDetail;

import com.mmt.diagnosis.dto.testItem.TestItemsResponse;
import lombok.Data;

import java.util.List;

@Data
public class ViewDetailResponse {

    private String testName;
    private String testComments;
    private String studentName;
    // int testItemNumber, String itemImagePath, String itemAnswer
    private List<TestItemsResponse> testItemsResponses;

}
