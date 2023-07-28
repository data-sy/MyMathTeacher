package com.mmt.diagnosis.dto.test;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TestResponse {

    private Long testId;
    private String testName;
    private String testComments;
    private LocalDateTime testTimestamp;
    private Long diagnosticTestId;

}
