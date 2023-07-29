package com.mmt.diagnosis.service;

import com.mmt.diagnosis.dto.testitem.TestItemData;
import com.mmt.diagnosis.dto.testitem.TestItemResponse;
import com.mmt.diagnosis.repository.TestItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestItemService {

    private final TestItemRepository testitemRepository;

    public TestItemService(TestItemRepository testitemRepository) {
        this.testitemRepository = testitemRepository;
    }

    public List<TestItemResponse> findTestItems(Long testId){
        return testitemRepository.findByTestId(testId);
    }

    public List<TestItemData> findDataList(Long testId){
        return testitemRepository.findDataList(testId);
    }
}
