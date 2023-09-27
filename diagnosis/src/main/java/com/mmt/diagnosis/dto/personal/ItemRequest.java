package com.mmt.diagnosis.dto.personal;

import lombok.Data;

@Data
public class ItemRequest {

    private Long itemId;
    private String itemImagePath;
    private int conceptId;
    private String conceptName;
    private double probabilityPercent;

}
