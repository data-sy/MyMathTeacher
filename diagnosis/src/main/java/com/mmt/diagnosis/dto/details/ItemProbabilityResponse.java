package com.mmt.diagnosis.dto.details;

import lombok.Data;

@Data
public class ItemProbabilityResponse {

    private Long itemId;
    private int testItemNumber;
    private String itemImagePath ;
    private int conceptId;
    private String conceptName;
    private double probabilityPercent;

}
