package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.personal.*;
import com.mmt.diagnosis.service.concept.ConceptService;
import com.mmt.diagnosis.service.personal.PersonalService;
import com.mmt.diagnosis.service.probability.ProbabilityService;
import com.mmt.diagnosis.service.student.StudentService;
import com.mmt.diagnosis.service.studentTest.StudentTestService;
import com.mmt.diagnosis.service.test.TestService;
import com.mmt.diagnosis.service.testItem.TestItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/personalization")
public class PersonalizationController {

    private final StudentService studentService;
    private final StudentTestService studentTestService;
    private final ProbabilityService probabilityService;
    private final ConceptService conceptService;
    private final PersonalService personalService;
    private final TestService testService;
    private final TestItemService testItemService;

    public PersonalizationController(StudentService studentService, StudentTestService studentTestService, ProbabilityService probabilityService, ConceptService conceptService, PersonalService personalService, TestService testService, TestItemService testItemService) {
        this.studentService = studentService;
        this.studentTestService = studentTestService;
        this.probabilityService = probabilityService;
        this.conceptService = conceptService;
        this.personalService = personalService;
        this.testService = testService;
        this.testItemService = testItemService;
    }
//
//    // 학생 목록, 학습지 목록 삭제
//    /**
//     * 학생 목록
//     */
//    @GetMapping("/students")
//    public List<StudentResponse> getStudents(@RequestBody StudentGetRequest request){
//        return studentService.findStudents(request.getTeacherId());
//    }
//
//    /**
//     * 학습지 목록
//     */
//    @GetMapping("/tests")
//    public List<IsRecordResponse> getTests(@RequestBody IsRecordRequest request){
//        return studentTestService.findTests(request.getStudentId());
//    }

//    /**
//     * 선택한 학습지의 분석 결과 보기
//     */
//    @GetMapping("/tests/{studentTestId}")
//    public DetailsResponse getDetails(@PathVariable Long studentTestId){
//        return probabilityService.findDetails(studentTestId);
//    }
//
//    /**
//     * [상세보기] 선택한 단위개념 자세히 보기
//     */
//    @GetMapping("/tests/items/{conceptId}")
//    public ConceptResponse getConcept(@PathVariable int conceptId){
//        return conceptService.findOne(conceptId);
//    }

    /**
     * 맞춤 학습지 만들기
     * 리팩토링 : 현재는 데이터 이동도 같이 하고 있음. 이 부분 프론트에서 개선하기
     */
    @GetMapping("/personalized-items")
    public PersonalItemsResponse getPersonalItems(@RequestBody PersonalItemsGetRequest request){
        return personalService.findPersonalItems(request);
    }

    /**
     * 맞춤 학습지 미리보기
     * 리팩토링 : 현재는 데이터 이동도 같이 하고 있음. 이 부분 프론트에서 개선하기
     */
    @GetMapping("")
    public PersonalTestResponse getPersonalTest(@RequestBody PersonalTestGetRequest request){
        return personalService.preview(request);
    }

    /**
     * 맞춤 학습지 다운로드
     * 리팩토링 : 현재는 데이터 이동도 같이 하고 있음. 이 부분 프론트에서 개선하기
     */
    @PostMapping("")
    public void create(@RequestBody PersonalCreateRequest request){
        Long testId = testService.create(request.getNewTestName(), request.getNewTestComments());
        studentTestService.create(request.getStudentId(), testId, request.getStudentTestId());
        testItemService.create(testId, request.getTestItemCreateRequestList());
    }

}
