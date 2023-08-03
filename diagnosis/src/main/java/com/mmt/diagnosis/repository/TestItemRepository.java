package com.mmt.diagnosis.repository;

import com.mmt.diagnosis.domain.TestItems;

import java.util.List;

public interface TestItemRepository {

    List<TestItems> findByTestId(Long testId);

}
