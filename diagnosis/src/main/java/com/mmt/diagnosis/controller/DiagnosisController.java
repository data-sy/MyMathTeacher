package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.diagnosticTest.DiagnosticTestRequest;
import com.mmt.diagnosis.dto.diagnosticTest.DiagnosticTestResponse;
import com.mmt.diagnosis.dto.student.StudentGetRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.dto.test.TestResponse;
import com.mmt.diagnosis.dto.testItem.TestItemsResponse;
import com.mmt.diagnosis.service.answer.AnswerService;
import com.mmt.diagnosis.service.student.StudentService;
import com.mmt.diagnosis.service.test.TestService;
import com.mmt.diagnosis.service.testItem.TestItemService;
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
    public List<TestItemsResponse> getTestItems(@PathVariable Long testId){
        // 리팩토링 : 사실 여기서는 답안 필요 없는데 메서드 재사용하려고 item_answer도 같이 리스펀스
        return testItemService.findTestItems(testId);
    }

    /**
     * 진단 학습지 상세보기 : 선택한 student_id와 test_id에 따른 진단 학습지 생성
     * 리팩토링 : 같은 학생, 같은 학습지를 선택했을 시 '재시험'이라는 것을 명시해주는 알람 띄우는 검증 로직 추가하기
     */
    @GetMapping("/diagnosis/diagnostic-test")
    public DiagnosticTestResponse getDiagnosticTest(@RequestBody DiagnosticTestRequest request){
        // 리팩토링 : 각각의 서비스를 사용하기 때문에 여기서 객체 생성하고 집어 넣었지만, 컨트롤러에서 하는 게 맞을까?
        DiagnosticTestResponse diagnosticTestResponse = new DiagnosticTestResponse();
        diagnosticTestResponse.setStudentName(studentService.findName(request.getStudentId()));
        diagnosticTestResponse.setTestName(testService.findNameComments(request.getTestId()).getTestName());
        diagnosticTestResponse.setTestComments(testService.findNameComments(request.getTestId()).getTestComments());
        diagnosticTestResponse.setTestItemsResponses(testItemService.findTestItems(request.getTestId()));
        return diagnosticTestResponse;
    }

    /**
     * 진단 학습지 다운로드 : (1)answers 테이블에 입력 (2)진단 학습지 다운로드
     */
    @PostMapping("/diagnosis/diagnostic-test")
    public void create(@RequestBody DiagnosticTestRequest request){
        // answers 테이블에 insert
        answerService.create(request);
        // 진단 학습지 다운로드
            // preview에서 화면에 보인 이미지를 pdf로 저장해서 다운로드 할 수 있는 방법 찾기
    }

}
