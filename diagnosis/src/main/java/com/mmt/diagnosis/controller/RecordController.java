package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.answer.AnswerCreateRequest;
import com.mmt.diagnosis.dto.preview.PreviewResponse;
import com.mmt.diagnosis.service.answer.AnswerService;
import com.mmt.diagnosis.service.student.StudentService;
import com.mmt.diagnosis.service.studentTest.StudentTestService;
import com.mmt.diagnosis.service.test.TestService;
import com.mmt.diagnosis.service.testItem.TestItemService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/record")
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

//    /**
//     * 답안 기록 : 선택한 학습지 미리보기
//     */
//    @GetMapping("")
//    public PreviewResponse getTest(@RequestParam Long studentTestId){
//        return studentTestService.preview(studentTestId);
//    }
//    // +) 추가 : 재기록 시 student_test 테이블에 다시 저장하고 새 student_test_id 받은 뒤 답안기록 떠야 해

    /**
     * 답안 기록 저장
     */
    @PostMapping("")
    public void create(@RequestBody AnswerCreateRequest request){
        answerService.create(request);
    }

}
