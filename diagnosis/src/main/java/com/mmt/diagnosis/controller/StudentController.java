package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.student.StudentCreateRequest;
import com.mmt.diagnosis.dto.student.StudentGetRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.dto.student.StudentUpdateRequest;
import com.mmt.diagnosis.service.student.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public void create(@RequestBody StudentCreateRequest request) {
        studentService.join(request);
    }

    @GetMapping("/students")
    public List<StudentResponse> getStudents(@RequestBody StudentGetRequest request){
        return studentService.findStudents(request.getTeacherId());
    }

    @GetMapping("/students/{studentId}")
    public StudentResponse getStudentById(@PathVariable Long studentId){
        return studentService.findOne(studentId);
    }

    @PutMapping("/students")
    public void update(@RequestBody StudentUpdateRequest request) {
        studentService.update(request);
    }

    @DeleteMapping("/students")
    public void delete(@RequestParam Long studentId) { studentService.delete(studentId); }

}
