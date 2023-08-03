package com.mmt.diagnosis.service.test;

import com.mmt.diagnosis.domain.Test;
import com.mmt.diagnosis.dto.test.TestResponse;

import java.util.ArrayList;
import java.util.List;

public class TestConverter {

    public static TestResponse convertToTestResponse(Test test) {
        TestResponse testResponse = new TestResponse();
        testResponse.setTestId(test.getTestId());
        testResponse.setTestName(test.getTestName());
        testResponse.setTestComments(test.getTestComments());
        return testResponse;
    }

    public static List<TestResponse> convertListToTestResponseList(List<Test> testList) {
        List<TestResponse> responseList = new ArrayList<>();
        for (Test test : testList) {
            responseList.add(convertToTestResponse(test));
        }
        return responseList;
    }

}
