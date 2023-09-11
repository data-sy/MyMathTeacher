package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.preview.PreviewRequest;
import com.mmt.diagnosis.dto.preview.PreviewResponse;
import com.mmt.diagnosis.dto.student.StudentGetRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.dto.test.TestResponse;
import com.mmt.diagnosis.dto.testItem.TestItemsResponse;
import com.mmt.diagnosis.service.student.StudentService;
import com.mmt.diagnosis.service.studentTest.StudentTestService;
import com.mmt.diagnosis.service.test.TestService;
import com.mmt.diagnosis.service.testItem.TestItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/diagnosis")
public class DiagnosisController {

    private final StudentService studentService;
    private final TestService testService;
    private final TestItemService testItemService;
    private final StudentTestService studentTestService;

    public DiagnosisController(StudentService studentService, TestService testService, TestItemService testItemService, StudentTestService studentTestService) {
        this.studentService = studentService;
        this.testService = testService;
        this.testItemService = testItemService;
        this.studentTestService = studentTestService;
    }

    // 수정 : 학생 목록 학습지 목록은 학생컨트롤러에 만들어 둔 것 사용하기!
    /**
     * 학생 목록
     * 수정할 것 : 삭제! studentController에 있는 거 사용하면 돼
     */
    @GetMapping("/students")
    public List<StudentResponse> getStudents(@RequestBody StudentGetRequest request){
        return studentService.findStudents(request.getTeacherId());
    }

    /**
     * 학생 상세보기
     * 수정할 것 : 삭제! studentController에 있는 거 사용하면 돼
     */
    @GetMapping("/students/{studentId}")
    public StudentResponse getStudent(@PathVariable Long studentId){
        return studentService.findOne(studentId);
    }

    // 수정할 것 : 학습지 컨트롤러 만들어서 학습지 목록, 학습지 상세보기는 거기로 옮기기
    /**
     * 학습지 목록
     */
    @GetMapping("/tests")
    public List<TestResponse> getTests(){
        return testService.findTests();
    }

    /**
     * 학습지 상세보기
     */
    @GetMapping("/tests/{testId}")
    public List<TestItemsResponse> getTestItems(@PathVariable Long testId){
        // 리팩토링 : 사실 여기서는 답안 필요 없는데 메서드 재사용하려고 item_answer도 같이 리스펀스
        return testItemService.findTestItems(testId);
    }

    /**
     * 진단 학습지 미리보기 : 선택한 student_id와 test_id에 따른 진단 학습지 생성
     * 리팩토링 : 같은 학생, 같은 학습지를 선택했을 시 '재시험'이라는 것을 명시해주는 알람 띄우는 검증 로직 추가하기
     */
    @GetMapping("/diagnostic-test")
    public PreviewResponse getDiagnosticTest(@RequestBody PreviewRequest request){
        return testItemService.preview(request.getStudentId(), request.getTestId());
    }

    /**
     * 진단 학습지 다운로드 : (1) student_test 테이블에 입력 (2) 진단 학습지 다운로드
     */
    @PostMapping("/diagnostic-test")
    public void create(@RequestBody PreviewRequest request){
        studentTestService.create(request.getStudentId(), request.getTestId());
        // 진단 학습지 다운로드
            // 화면에 보인 이미지를 pdf로 저장해서 다운로드 할 수 있는 방법 찾기
    }

}
