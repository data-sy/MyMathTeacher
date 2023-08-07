package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.answer.IsRecordRequest;
import com.mmt.diagnosis.dto.answer.IsRecordResponse;
import com.mmt.diagnosis.dto.concept.ConceptResponse;
import com.mmt.diagnosis.dto.details.DetailsResponse;
import com.mmt.diagnosis.dto.student.StudentGetRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.service.concept.ConceptService;
import com.mmt.diagnosis.service.probability.ProbabilityService;
import com.mmt.diagnosis.service.student.StudentService;
import com.mmt.diagnosis.service.studentTest.StudentTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonalizationController {

    private final StudentService studentService;
    private final StudentTestService studentTestService;
    private final ProbabilityService probabilityService;
    private final ConceptService conceptService;

    public PersonalizationController(StudentService studentService, StudentTestService studentTestService, ProbabilityService probabilityService, ConceptService conceptService) {
        this.studentService = studentService;
        this.studentTestService = studentTestService;
        this.probabilityService = probabilityService;
        this.conceptService = conceptService;
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
     * 
     */

}
