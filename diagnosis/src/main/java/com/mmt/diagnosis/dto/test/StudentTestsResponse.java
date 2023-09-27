package com.mmt.diagnosis.dto.test;

import lombok.Data;

@Data
public class StudentTestsResponse {

    private Long studentTestId;
    private Long testId;
    private String testName;
    private String testComments;
    private boolean isRecord;

    // 네이밍 컨벤션에 따른 boolean 타입의 isㅇㅇㅇ 필드 : getter, setter의 명명 규칙이 달라서 따로 적어둠
    // getter
    public boolean isRecord() {
        return isRecord;
    }
    // setter
    public void setRecord(boolean record) {
        isRecord = record;
    }

}
