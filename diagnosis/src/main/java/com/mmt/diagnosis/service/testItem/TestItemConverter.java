package com.mmt.diagnosis.service.testItem;

import com.mmt.diagnosis.domain.TestItems;
import com.mmt.diagnosis.dto.testItem.TestItemsResponse;

import java.util.ArrayList;
import java.util.List;

public class TestItemConverter {

    public static TestItemsResponse convertToTestItemsResponse(TestItems testItems) {
        TestItemsResponse testItemsResponse = new TestItemsResponse();
        testItemsResponse.setItemId(testItems.getItemId());
        testItemsResponse.setItemAnswer(testItems.getItemAnswer());
        testItemsResponse.setItemImagePath(testItems.getItemImagePath());
        testItemsResponse.setTestItemNumber(testItems.getTestItemNumber());
        return testItemsResponse;
    }

    public static List<TestItemsResponse> convertListToTestItemsResponseList(List<TestItems> testItemsList) {
        List<TestItemsResponse> responseList = new ArrayList<>();
        for (TestItems testItems : testItemsList) {
            responseList.add(convertToTestItemsResponse(testItems));
        }
        return responseList;
    }

}
