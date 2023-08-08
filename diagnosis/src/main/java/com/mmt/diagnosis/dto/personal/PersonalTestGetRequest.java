package com.mmt.diagnosis.dto.personal;

import lombok.Data;

import java.util.HashMap;

@Data
public class PersonalTestGetRequest {

    // 리팩토링 : 프론트 단에서 이동시키면 없어질 필드들 studentName, newTestName, studentTestId, testName
    private String studentName;
    private String newTestName;
    private String testName;
    private Long studentTestId;

    private HashMap<Long, Integer> itemNumHashMap;

}
