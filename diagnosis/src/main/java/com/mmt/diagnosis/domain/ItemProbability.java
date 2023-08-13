package com.mmt.diagnosis.domain;

import lombok.Data;

@Data
public class ItemProbability {

    private Long itemId;
    private int testItemNumber;
    private String itemImagePath;
    private int conceptId;
    private String conceptName;
    private double probabilityPercent;

}
