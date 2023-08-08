package com.mmt.diagnosis.dto.personal;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PersonalGetRequest {

    private Long studentTestId;
    private String studentName;
    private LocalDate studentBirthdate;

    private List<ItemRequest> itemRequestList;

    private List<String> conceptNameList;
    private List<String> toConceptNameList;
    private List<String> fromConceptNameList;

}
