package com.mmt.diagnosis.dto.personal;

import com.mmt.diagnosis.dto.testItem.TestItemCreateRequest;
import lombok.Data;

import java.util.List;

@Data
public class PersonalCreateRequest {

    private Long studentId;
    private String newTestName;
    private String newTestComments;
    private Long studentTestId;
    private List<TestItemCreateRequest> testItemCreateRequestList;

}
