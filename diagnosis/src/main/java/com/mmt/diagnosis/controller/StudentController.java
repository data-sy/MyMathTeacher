package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.student.StudentCreateRequest;
import com.mmt.diagnosis.dto.student.StudentUpdateRequest;
import com.mmt.diagnosis.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/students")
    public void saveStudent(@RequestBody StudentCreateRequest request) {
        studentService.saveStudent(request);
    }



    @PutMapping("/students")
    public void updateStudent(@RequestBody StudentUpdateRequest request) {
        studentService.updateStudent(request);
    }

    @DeleteMapping("/students")
    public void deleteStudent(@RequestParam int id) { studentService.deleteStudent(id); }

}
