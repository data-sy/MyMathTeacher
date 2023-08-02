package com.mmt.diagnosis.dto.diagnosticTest;

import com.mmt.diagnosis.dto.testItem.TestItemsResponse;
import lombok.Data;

import java.util.List;

@Data
public class DiagnosticTestResponse {

    private String testName;
    private String testComments;
    private String studentName;
    // int testItemNumber, String itemImagePath, String itemAnswer
    private List<TestItemsResponse> testItemsResponses;

}
