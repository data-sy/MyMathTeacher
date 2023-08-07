package com.mmt.diagnosis.dto.details;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DetailsResponse {

    private String studentName;
    private LocalDate studentBirthdate;
    private String testName;

    private List<아직못정함> 못정함;

    private List<String> conceptNameList;
    private List<String> toConceptNameList;
    private List<String> fromConceptNameList;

}
