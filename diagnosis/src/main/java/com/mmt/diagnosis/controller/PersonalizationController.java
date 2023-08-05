package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.answer.IsRecordRequest;
import com.mmt.diagnosis.dto.answer.IsRecordResponse;
import com.mmt.diagnosis.dto.student.StudentGetRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.service.student.StudentService;
import com.mmt.diagnosis.service.studentTest.StudentTestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonalizationController {

    private final StudentService studentService;
    private final StudentTestService studentTestService;

    public PersonalizationController(StudentService studentService, StudentTestService studentTestService) {
        this.studentService = studentService;
        this.studentTestService = studentTestService;
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
     * 학생 목록
     */
    /**
     * 학생 목록
     */
    /**
     * 학생 목록
     */

}
