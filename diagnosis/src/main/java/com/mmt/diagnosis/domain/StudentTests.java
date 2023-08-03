package com.mmt.diagnosis.domain;

import lombok.Data;

@Data
public class StudentTests {
    // students_tests 테이블 + students 테이블 + tests 테이블

    // students_tests
    private Long studentTestId;
    // tests
    private String testName;
    private String testComments;

    private boolean isRecord;

    // 디버깅 용 : System.out.println(studentTests);
    @Override
    public String toString() {
        return String.format("StudentTests{ studentTestId = %d, testName = %s, testComments = %s, isRecord = %b }", studentTestId, testName, testComments, isRecord);
    }

}
