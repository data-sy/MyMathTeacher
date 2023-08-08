package com.mmt.diagnosis.dto.personal;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Data
public class PersonalResponse {

    private Long studentTestId;
    private String studentName;
    private LocalDate studentBirthdate;

    private HashMap<String, List<ItemResponse>> itemResponseHashMap;

    private List<String> conceptNameList;
    private List<String> toConceptNameList;
    private List<String> fromConceptNameList;

}
