package com.mmt.diagnosis.dto.scenario;

import lombok.Data;

import java.util.List;

@Data
public class ScenarioRequest {

    private int scenarioCase;
    private int conceptId;
    private List<Integer> checkList;
    private List<List<Double>> probabilityList;

}
