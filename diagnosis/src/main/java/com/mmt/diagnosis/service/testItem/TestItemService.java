package com.mmt.diagnosis.service.testItem;

import com.mmt.diagnosis.dto.test.TestResponse;
import com.mmt.diagnosis.dto.viewDetail.ViewDetailResponse;
import com.mmt.diagnosis.dto.testItem.TestItemsResponse;
import com.mmt.diagnosis.repository.TestItemRepository;
import com.mmt.diagnosis.service.student.StudentService;
import com.mmt.diagnosis.service.test.TestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestItemService {

    private final TestItemRepository testItemRepository;
    private final StudentService studentService;
    private final TestService testService;

    public TestItemService(TestItemRepository testitemRepository, StudentService studentService, TestService testService) {
        this.testItemRepository = testitemRepository;
        this.studentService = studentService;
        this.testService = testService;
    }

    public List<TestItemsResponse> findTestItems(Long testId){
        return TestItemConverter.convertListToTestItemsResponseList(testItemRepository.findByTestId(testId));
    }

    public ViewDetailResponse viewDetails(Long studentId, Long testId){
        ViewDetailResponse viewDetailResponse = new ViewDetailResponse();
        viewDetailResponse.setStudentName(studentService.findName(studentId));
        TestResponse testResponse = testService.findOne(testId);
        viewDetailResponse.setTestName(testResponse.getTestName());
        viewDetailResponse.setTestComments(testResponse.getTestComments());
        viewDetailResponse.setTestItemsResponses(findTestItems(testId));
        return viewDetailResponse;
    }

}
