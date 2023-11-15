package com.mmt.diagnosis.dto.scenario;

import lombok.Data;

import java.util.List;

@Data
public class ScenarioRequest {

    private Long scenarioCase;
    private List<List<Object>> probabilityList;

}
