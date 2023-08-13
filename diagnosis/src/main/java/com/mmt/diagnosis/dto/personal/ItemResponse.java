package com.mmt.diagnosis.dto.personal;

import lombok.Data;

@Data
public class ItemResponse {

    private Long itemId;
    private String itemImagePath;
    private int conceptId;
    private String conceptName;
    private int toConceptDepth;
    private double probabilityPercent;

}
