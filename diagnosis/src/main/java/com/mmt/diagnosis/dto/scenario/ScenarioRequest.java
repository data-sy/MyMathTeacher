package com.mmt.diagnosis.dto.scenario;

import lombok.Data;

@Data
public class ScenarioRequest {

    private Long scenarioCase;
    private double[][] probabilityList;


}
