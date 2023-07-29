package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.download.DownloadRequest;
import com.mmt.diagnosis.dto.download.DownloadResponse;
import com.mmt.diagnosis.dto.student.StudentGetRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.dto.test.TestResponse;
import com.mmt.diagnosis.dto.testitem.TestItemResponse;
import com.mmt.diagnosis.service.StudentService;
import com.mmt.diagnosis.service.TestItemService;
import com.mmt.diagnosis.service.TestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiagnosisController {

    private final StudentService studentService;
    private final TestService testService;
    private final TestItemService testItemService;
    private final AnswerService answerService;

    public DiagnosisController(StudentService studentService, TestService testService, TestItemService testItemService, AnswerService answerService) {
        this.studentService = studentService;
        this.testService = testService;
        this.testItemService = testItemService;
        this.answerService = answerService;
    }

    /**
     * 학생 목록
     */
    @GetMapping("/diagnosis/students")
    public List<StudentResponse> getStudents(@RequestBody StudentGetRequest request){
        return studentService.findStudents(request.getTeacherId());
    }

    /**
     * 학생 상세보기
     */
    @GetMapping("/diagnosis/students/{studentId}")
    public StudentResponse getStudentById(@PathVariable Long studentId){
        return studentService.findOne(studentId);
    }

    /**
     * 학습지 목록
     */
    @GetMapping("/diagnosis/tests")
    public List<TestResponse> getTests(){
        return testService.findTests();
    }

    /**
     * 학습지 상세보기
     */
    @GetMapping("/diagnosis/tests/{testId}")
    public List<TestItemResponse> getTestItems(@PathVariable Long testId){
        return testItemService.findTestItems(testId);
    }

    /**
     * 진단학습지 다운로드 : (1) answers 테이블에 입력 (2) 진단 학습지 다운로드
     * 리팩토링 : 우선 한 메서드 안에 만들고 필요하다면 나중에 쪼개기
     */
    @PostMapping("/diagnosis/download")
    public List<DownloadResponse> create(@RequestBody DownloadRequest request){
        // answers 테이블에 insert
        answerService.create(request);

    }

}
