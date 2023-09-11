package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.answer.AnswerCreateRequest;
import com.mmt.diagnosis.dto.answer.AnswerGetRequest;
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
//
//    // 학생 목록, 학습지 목록 삭제
//    /**
//     * 학생 목록
//     * 리팩토링 : id와 이름만 받는 메서드 오버로드 만들기 (지금은 모든 정보 다 받음)
//     */
//    @GetMapping("/students")
//    public List<StudentResponse> getStudents(@RequestBody StudentGetRequest request){
//        return studentService.findStudents(request.getTeacherId());
//    }
//
//    // 기록과 맞춤의 학습지 목록보기는 바디로 보내는 게 아니라 파라미터로 보내기
//    //  public void delete(@RequestParam Long studentId) 처럼
//    // 여기서 파라미터는 studentId
//    /**
//     * 학습지 목록 : 학생의 답안 기록 여부 포함
//     */
//    @GetMapping("/tests")
//    public List<IsRecordResponse> getTests(@RequestBody IsRecordRequest request){
//        return studentTestService.findTests(request.getStudentId());
//    }

    /**
     * 답안 기록 : 선택한 학습지 미리보기
     */
    @GetMapping("")
    public PreviewResponse getTest(@RequestBody AnswerGetRequest request){
        return studentTestService.preview(request.getStudentTestId());
    }
    // +) 추가 : 재기록 시 student_test 테이블에 다시 저장하고 새 student_test_id 받은 뒤 답안기록 떠야 해

    /**
     * 답안 기록 저장
     */
    @PostMapping("")
    public void create(@RequestBody AnswerCreateRequest request){
        answerService.create(request);
    }

}
