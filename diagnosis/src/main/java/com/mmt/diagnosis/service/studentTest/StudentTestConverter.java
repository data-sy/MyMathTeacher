package com.mmt.diagnosis.service.studentTest;

import com.mmt.diagnosis.domain.StudentTests;
import com.mmt.diagnosis.dto.answer.IsRecordResponse;

import java.util.ArrayList;
import java.util.List;

public class StudentTestConverter {

    public static IsRecordResponse convertToStudentTestResponse(StudentTests studentTests) {
        IsRecordResponse isRecordResponse = new IsRecordResponse();
        isRecordResponse.setStudentTestId(studentTests.getStudentTestId());
        isRecordResponse.setTestName(studentTests.getTestName());
        isRecordResponse.setTestComments(studentTests.getTestComments());
        isRecordResponse.setRecord(studentTests.isRecord());
        return isRecordResponse;
    }

    public static List<IsRecordResponse> convertListToStudentTestResponseList(List<StudentTests> studentTestsList) {
        List<IsRecordResponse> responseList = new ArrayList<>();
        for (StudentTests studentTests : studentTestsList) {
            responseList.add(convertToStudentTestResponse(studentTests));
        }
        return responseList;
    }

}
