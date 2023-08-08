package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.answer.IsRecordRequest;
import com.mmt.diagnosis.dto.answer.IsRecordResponse;
import com.mmt.diagnosis.dto.concept.ConceptResponse;
import com.mmt.diagnosis.dto.details.DetailsResponse;
import com.mmt.diagnosis.dto.personal.PersonalItemsGetRequest;
import com.mmt.diagnosis.dto.personal.PersonalItemsResponse;
import com.mmt.diagnosis.dto.personal.PersonalTestGetRequest;
import com.mmt.diagnosis.dto.personal.PersonalTestResponse;
import com.mmt.diagnosis.dto.student.StudentGetRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.service.concept.ConceptService;
import com.mmt.diagnosis.service.personal.PersonalService;
import com.mmt.diagnosis.service.probability.ProbabilityService;
import com.mmt.diagnosis.service.student.StudentService;
import com.mmt.diagnosis.service.studentTest.StudentTestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonalizationController {

    private final StudentService studentService;
    private final StudentTestService studentTestService;
    private final ProbabilityService probabilityService;
    private final ConceptService conceptService;
    private final PersonalService personalService;

    public PersonalizationController(StudentService studentService, StudentTestService studentTestService, ProbabilityService probabilityService, ConceptService conceptService, PersonalService personalService) {
        this.studentService = studentService;
        this.studentTestService = studentTestService;
        this.probabilityService = probabilityService;
        this.conceptService = conceptService;
        this.personalService = personalService;
    }

    /**
     * 학생 목록
     */
    @GetMapping("/personalization/students")
    public List<StudentResponse> getStudents(@RequestBody StudentGetRequest request){
        return studentService.findStudents(request.getTeacherId());
    }

    /**
     * 학습지 목록
     */
    @GetMapping("/personalization/tests")
    public List<IsRecordResponse> getTests(@RequestBody IsRecordRequest request){
        return studentTestService.findTests(request.getStudentId());
    }

    /**
     * 선택한 학습지의 분석 결과 보기
     */
    @GetMapping("/personalization/tests/{studentTestId}")
    public DetailsResponse getDetails(@PathVariable Long studentTestId){
        return probabilityService.findDetails(studentTestId);
    }

    /**
     * [상세보기] 선택한 단위개념 자세히 보기
     */
    @GetMapping("personalization/tests/items/{conceptId}")
    public ConceptResponse getConcept(@PathVariable int conceptId){
        return conceptService.findOne(conceptId);
    }

    /**
     * 맞춤 학습지 만들기
     * 리팩토링 : 현재는 데이터 이동도 같이 하고 있음. 이 부분 프론트에서 개선하기
     */
    @GetMapping("personalization/personalized-assessment-items")
    public PersonalItemsResponse getPersonalItems(@RequestBody PersonalItemsGetRequest request){
        return personalService.findPersonalItems(request);
    }

    /**
     * 맞춤 학습지 미리보기
     */
    @GetMapping("personalization/personalized-tests")
    public PersonalTestResponse getPersonalTest(@RequestBody PersonalTestGetRequest request){
        return personalService.preview(request);
    }

    /**
     * 맞춤 학습지 다운로드
     */
    @PostMapping("personalization/personalized-tests")
    public void create(){
        // 필요한 곳에 다 저장
    }


}
