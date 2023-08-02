package com.mmt.diagnosis.service.testItem;

import com.mmt.diagnosis.dto.testItem.TestItemsResponse;
import com.mmt.diagnosis.repository.TestItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestItemService {

    private final TestItemRepository testitemRepository;

    public TestItemService(TestItemRepository testitemRepository) {
        this.testitemRepository = testitemRepository;
    }

    public List<TestItemsResponse> findTestItems(Long testId){
        return TestItemConverter.convertListToTestItemsResponseList(testitemRepository.findTestItems(testId));
    }

}
