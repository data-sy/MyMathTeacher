package com.mmt.diagnosis.dto.download;

import com.mmt.diagnosis.dto.testitem.TestItemData;
import lombok.Data;

import java.util.List;

@Data
public class DownloadResponse {

    private String testName;
    private String testComments;
    private String studentName;
    // int testItemNumber, String itemAnswer, String itemImagePath
    private List<TestItemData> testItemDataList;

}
