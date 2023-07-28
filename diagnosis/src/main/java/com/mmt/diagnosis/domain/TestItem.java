package com.mmt.diagnosis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestItem {

    private Long testItemId;
    private Long testId;
    private Long itemId;
    private int testItemNumber;

}
