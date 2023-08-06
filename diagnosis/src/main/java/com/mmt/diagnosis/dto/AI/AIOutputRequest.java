package com.mmt.diagnosis.dto.AI;

import lombok.Data;

@Data
public class AIOutputRequest {

    private Long studentTestId;
    private double[] probabilityList;

}
