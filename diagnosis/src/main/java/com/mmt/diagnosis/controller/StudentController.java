package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.student.StudentCreateRequest;
import com.mmt.diagnosis.dto.student.StudentResponse;
import com.mmt.diagnosis.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
//
//    @GetMapping("/students")
//    public List<StudentResponse> getStudents(){ return studentService.getStudents();}
//
//    @PutMapping("/students")
//    public void updateStudent(@RequestBody StudentCreateRequest request) {
//        studentService.updateStudent(request);
//    }
//
//    @DeleteMapping("/students")
//    public void deleteUser(@RequestParam int id) { studentService.deleteStudent(id); }

}
