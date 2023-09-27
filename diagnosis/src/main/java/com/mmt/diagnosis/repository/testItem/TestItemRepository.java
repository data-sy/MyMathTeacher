package com.mmt.diagnosis.repository.testItem;

import com.mmt.diagnosis.domain.TestItems;
import com.mmt.diagnosis.dto.testItem.TestItemsResponse;

import java.util.HashMap;
import java.util.List;

public interface TestItemRepository {

    void save(Long testId, List<TestItems> testItemsList);

    List<TestItems> findByTestId(Long testId);

}
