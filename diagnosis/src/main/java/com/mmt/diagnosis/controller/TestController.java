package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.concept.ConceptResponse;
import com.mmt.diagnosis.dto.test.StudentTestsRequest;
import com.mmt.diagnosis.dto.test.StudentTestsResponse;
import com.mmt.diagnosis.dto.test.TestResponse;
import com.mmt.diagnosis.dto.testItem.TestItemsResponse;
import com.mmt.diagnosis.service.concept.ConceptService;
import com.mmt.diagnosis.service.studentTest.StudentTestService;
import com.mmt.diagnosis.service.test.TestService;
import com.mmt.diagnosis.service.testItem.TestItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tests")
public class TestController {

    private final TestService testService;
    private final TestItemService testItemService;
    private final ConceptService conceptService;
    private final StudentTestService studentTestService;

    public TestController(TestService testService, TestItemService testItemService, ConceptService conceptService, StudentTestService studentTestService) {
        this.testService = testService;
        this.testItemService = testItemService;
        this.conceptService = conceptService;
        this.studentTestService = studentTestService;
    }

    /**
     * 전체 학습지 목록
     */
    @GetMapping("")
    public List<TestResponse> getTests(){
        return testService.findTests();
    }

    /**
     * 학생에 따른 학습지 목록
     */
    @GetMapping("/student")
    public List<StudentTestsResponse> getTests(@RequestBody StudentTestsRequest request){
        return studentTestService.findTests(request.getStudentId());
    }

    /**
     * 학습지 상세보기
     */
    @GetMapping("/{testId}")
    public List<TestItemsResponse> getTestItems(@PathVariable Long testId){
        // 리팩토링 : 사실 여기서는 답안 필요 없는데 메서드 재사용하려고 item_answer도 같이 리스펀스
        return testItemService.findTestItems(testId);
    }

    /**
     * 문항 상세보기 : [상세보기]버튼 클릭 시 단위개념 자세히 보기
     */
    @GetMapping("/items/{conceptId}")
    public ConceptResponse getConcept(@PathVariable int conceptId){
        return conceptService.findOne(conceptId);
    }

}
