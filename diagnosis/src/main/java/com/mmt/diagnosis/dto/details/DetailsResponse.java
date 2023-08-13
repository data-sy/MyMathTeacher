package com.mmt.diagnosis.dto.details;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DetailsResponse {

    private Long StudentTestId;
    private String studentName;
    private LocalDate studentBirthdate;
    private String testName;

    private List<ItemProbabilityResponse> itemProbabilityResponseList;

    private List<String> conceptNameList;
    private List<String> toConceptNameList;
    private List<String> fromConceptNameList;

}
