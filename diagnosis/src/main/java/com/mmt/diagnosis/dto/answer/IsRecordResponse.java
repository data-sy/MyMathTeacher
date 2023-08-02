package com.mmt.diagnosis.dto.answer;

import lombok.Data;

@Data
public class IsRecordResponse {

    private Long answerId;
    private Long testId;
    private String testName;
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

    public IsRecordResponse(boolean isRecord) {
        this.isRecord = isRecord;
    }
}
