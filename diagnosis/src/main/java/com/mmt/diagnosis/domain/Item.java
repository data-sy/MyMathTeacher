package com.mmt.diagnosis.domain;

import lombok.Data;

@Data
public class Item {

    private Long itemId;
    private String itemImagePath;
    private int conceptId;
    private String conceptName;
    private int toConceptDepth;
    private double probabilityPercent;

}
