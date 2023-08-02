package com.mmt.diagnosis.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Test {

    private Long testId;
    private String testName;
    private String testComments;
    private LocalDateTime testTimestamp;
    private Long diagnosticTestId;

}
