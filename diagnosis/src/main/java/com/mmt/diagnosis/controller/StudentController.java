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

    @PostMapping("")
    public void create(@RequestBody StudentCreateRequest request) {
        studentService.join(request);
    }

    @GetMapping("")
    public List<StudentResponse> getStudents(@RequestBody StudentGetRequest request){
        return studentService.findStudents(request.getTeacherId());
    }

    @GetMapping("/{studentId}")
    public StudentResponse getStudent(@PathVariable Long studentId){
        return studentService.findOne(studentId);
    }

    @PutMapping("")
    public void update(@RequestBody StudentUpdateRequest request) {
        studentService.update(request);
    }

    @DeleteMapping("")
    public void delete(@RequestParam Long studentId) { studentService.delete(studentId); }

}
