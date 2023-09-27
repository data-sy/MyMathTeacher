package com.mmt.diagnosis.dto.personal;

import com.mmt.diagnosis.dto.testItem.TestItemsResponse;
import lombok.Data;

import java.util.List;

@Data
public class PersonalTestResponse {

    // 리팩토링 : 프론트 단에서 이동시키면 없어질 필드들 studentName, newTestName, testName, studentTestId
    private String studentName;
    private String newTestName;
    private String testName;
    private Long studentTestId;

    private List<TestItemsResponse> testItemsResponseList;


}
