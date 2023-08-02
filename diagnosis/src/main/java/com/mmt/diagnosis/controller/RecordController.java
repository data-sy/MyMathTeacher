package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.answer.IsRecordRequest;
import com.mmt.diagnosis.dto.student.StudentGetRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.dto.answer.IsRecordResponse;
import com.mmt.diagnosis.service.answer.AnswerService;
import com.mmt.diagnosis.service.student.StudentService;
import com.mmt.diagnosis.service.testItem.TestItemService;
import com.mmt.diagnosis.service.test.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecordController {

    private final StudentService studentService;
    private final TestService testService;
    private final TestItemService testItemService;
    private final AnswerService answerService;

    public RecordController(StudentService studentService, TestService testService, TestItemService testItemService, AnswerService answerService) {
        this.studentService = studentService;
        this.testService = testService;
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
     * 학습지 목록 : 학생의 답안 기록 여부
     */
    @GetMapping("/record/tests")
    public List<IsRecordResponse> getTests(@RequestBody IsRecordRequest request){
        return answerService.findTests(request);
    }
    /**
     * 답안 기록
     */

    /**
     * 답안 기록 저장
     */


}
