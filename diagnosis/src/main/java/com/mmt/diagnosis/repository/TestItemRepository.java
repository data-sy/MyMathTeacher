package com.mmt.diagnosis.repository;

import com.mmt.diagnosis.dto.testitem.TestItemData;
import com.mmt.diagnosis.dto.testitem.TestItemResponse;

import java.util.List;

public interface TestItemRepository {

    List<TestItemResponse> findByTestId(Long testId);

    List<TestItemData> findDataList(Long testId);
}
