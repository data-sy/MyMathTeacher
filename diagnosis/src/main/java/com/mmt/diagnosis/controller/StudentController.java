package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.student.StudentCreateRequest;
import com.mmt.diagnosis.dto.student.StudentGetRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.dto.student.StudentUpdateRequest;
import com.mmt.diagnosis.service.student.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 학생 등록
     */
    @PostMapping("")
    public void create(@RequestBody StudentCreateRequest request) {
        studentService.join(request);
    }

    /**
     * 학생 목록보기
     */
    @GetMapping("")
    public List<StudentResponse> getStudents(@RequestBody StudentGetRequest request){
        return studentService.findStudents(request.getTeacherId());
    }

//    /**
//     * 학생 상세보기
//     */
//    @GetMapping("/{studentId}")
//    public StudentResponse getStudent(@PathVariable Long studentId){
//        return studentService.findOne(studentId);
//    }

    /**
     * 학생 수정
     */
    @PutMapping("")
    public void update(@RequestBody StudentUpdateRequest request) {
        studentService.update(request);
    }

    /**
     * 학생 삭제
     */
    @DeleteMapping("")
    public void delete(@RequestParam Long studentId) { studentService.delete(studentId); }

}
