package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.answer.IsRecordRequest;
import com.mmt.diagnosis.dto.student.StudentGetRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.dto.answer.IsRecordResponse;
import com.mmt.diagnosis.dto.viewDetail.ViewDetailRequest;
import com.mmt.diagnosis.dto.viewDetail.ViewDetailResponse;
//import com.mmt.diagnosis.service.answer.AnswerService;
import com.mmt.diagnosis.service.student.StudentService;
import com.mmt.diagnosis.service.studentTest.StudentTestService;
import com.mmt.diagnosis.service.testItem.TestItemService;
import com.mmt.diagnosis.service.test.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecordController {

    private final StudentService studentService;
    private final StudentTestService studentTestService;
    private final TestItemService testItemService;
//    private final AnswerService answerService;

    public RecordController(StudentService studentService, TestService testService, StudentTestService studentTestService, TestItemService testItemService) {
        this.studentService = studentService;
        this.studentTestService = studentTestService;
        this.testItemService = testItemService;
//        this.answerService = answerService;
    }

    /**
     * 학생 목록
     * 리팩토링 : id와 이름만 받는 메서드 오버로드 만들기 (지금은 모든 정보 다 받음)
     */
    @GetMapping("/record/students")
    public List<StudentResponse> getStudents(@RequestBody StudentGetRequest request){
        return studentService.findStudents(request.getTeacherId());
    }

    /**
     * 학습지 목록 : 학생의 답안 기록 여부
     */
    @GetMapping("/record/tests")
    public List<IsRecordResponse> getTests(@RequestBody IsRecordRequest request){
        return studentTestService.findAll(request.getStudentId());
    }

    /**
     * 답안 기록 : 선택한 학습지 상세보기
     */
//    @GetMapping("record/answers")
//    public ViewDetailResponse getDiagnosticTest(@RequestBody ViewDetailRequest request){
//        return testItemService.viewDetails(request.getStudentId(), request.getTestId());
//    }

    /**
     * 답안 기록 저장
     */
//    @PostMapping("record/answers")
//    public void create(@RequestBody AnswerCreateRequest request){
//
//    }


}