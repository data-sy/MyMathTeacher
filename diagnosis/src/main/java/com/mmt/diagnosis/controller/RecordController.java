package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.answer.AnswerCreateRequest;
import com.mmt.diagnosis.dto.answer.AnswerGetRequest;
import com.mmt.diagnosis.dto.answer.IsRecordRequest;
import com.mmt.diagnosis.dto.answer.IsRecordResponse;
import com.mmt.diagnosis.dto.preview.PreviewResponse;
import com.mmt.diagnosis.dto.student.StudentGetRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.service.answer.AnswerService;
import com.mmt.diagnosis.service.student.StudentService;
import com.mmt.diagnosis.service.studentTest.StudentTestService;
import com.mmt.diagnosis.service.test.TestService;
import com.mmt.diagnosis.service.testItem.TestItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecordController {

    private final StudentService studentService;
    private final StudentTestService studentTestService;
    private final TestItemService testItemService;
    private final AnswerService answerService;

    public RecordController(StudentService studentService, TestService testService, StudentTestService studentTestService, TestItemService testItemService, AnswerService answerService) {
        this.studentService = studentService;
        this.studentTestService = studentTestService;
        this.testItemService = testItemService;
        this.answerService = answerService;
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
     * 학습지 목록 : 학생의 답안 기록 여부 포함
     */
    @GetMapping("/record/tests")
    public List<IsRecordResponse> getTests(@RequestBody IsRecordRequest request){
        return studentTestService.findTests(request.getStudentId());
    }

    /**
     * 답안 기록 : 선택한 학습지 상세보기
     */
    @GetMapping("record/answers")
    public PreviewResponse getTest(@RequestBody AnswerGetRequest request){
        return studentTestService.preview(request.getStudentTestId());
    }

    /**
     * 답안 기록 저장
     */
    @PostMapping("record/answers")
    public void create(@RequestBody AnswerCreateRequest request){
        answerService.create(request);
    }

}
