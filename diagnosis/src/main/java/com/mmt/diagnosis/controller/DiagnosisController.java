package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.student.StudentGetRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.dto.test.TestResponse;
import com.mmt.diagnosis.service.StudentService;
import com.mmt.diagnosis.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiagnosisController {

    private final StudentService studentService;
    private final TestService testService;

    public DiagnosisController(StudentService studentService, TestService testService) {
        this.studentService = studentService;
        this.testService = testService;
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
    public StudentResponse getStudentById(@PathVariable int studentId){
        return studentService.findOne(studentId);
    }

    /**
     * 학습지 목록
     */
    @GetMapping("/diagnosis/tests")
    public List<TestResponse> getTests(){
        return testService.findTests();
    }

}
