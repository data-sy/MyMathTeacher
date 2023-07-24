package com.mmt.diagnosis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestItem {

    private int testItemId;
    private int testId;
    private int itemId;
    private int testItemNumber;

}
