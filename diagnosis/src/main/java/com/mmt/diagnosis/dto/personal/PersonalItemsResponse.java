package com.mmt.diagnosis.dto.personal;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class PersonalItemsResponse {

    private Long studentTestId;
    private String studentName;
    private LocalDate studentBirthdate;

    private Map<String, List<ItemResponse>> itemResponseMap;

    private List<String> conceptNameList;
    private List<String> toConceptNameList;
    private List<String> fromConceptNameList;

}
