package com.mmt.diagnosis.service.studentTest;

import com.mmt.diagnosis.domain.StudentTests;
import com.mmt.diagnosis.dto.test.StudentTestsResponse;

import java.util.ArrayList;
import java.util.List;

public class StudentTestConverter {

    public static StudentTestsResponse convertToStudentTestResponse(StudentTests studentTests) {
        StudentTestsResponse studentTestsResponse = new StudentTestsResponse();
        studentTestsResponse.setStudentTestId(studentTests.getStudentTestId());
        studentTestsResponse.setTestId(studentTests.getTestId());
        studentTestsResponse.setTestName(studentTests.getTestName());
        studentTestsResponse.setTestComments(studentTests.getTestComments());
        studentTestsResponse.setRecord(studentTests.isRecord());
        return studentTestsResponse;
    }

    public static List<StudentTestsResponse> convertListToStudentTestResponseList(List<StudentTests> studentTestsList) {
        List<StudentTestsResponse> responseList = new ArrayList<>();
        for (StudentTests studentTests : studentTestsList) {
            responseList.add(convertToStudentTestResponse(studentTests));
        }
        return responseList;
    }

}
