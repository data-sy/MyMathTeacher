package com.mmt.diagnosis.domain;

import lombok.Data;

@Data
public class Scenario {

    private Long scenarioId;
    private int scenarioCase;
    private int momId;
    private int skillId;
    private String proList;

}
