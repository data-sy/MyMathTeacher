package com.mmt.diagnosis.dto.student;

import com.mmt.diagnosis.domain.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentConverter {

    public static Student convertToStudent(StudentCreateRequest studentCreateRequest) {
        Student student = new Student();
        student.setStudentName(studentCreateRequest.getStudentName());
        student.setStudentPhone(studentCreateRequest.getStudentPhone());
        student.setStudentBirthdate(studentCreateRequest.getStudentBirthdate());
        student.setStudentSchool(studentCreateRequest.getStudentSchool());
        student.setStudentComments(studentCreateRequest.getStudentComments());
        student.setTeacherId(studentCreateRequest.getTeacherId());
        return student;
    }
    public static Student convertToStudent(StudentUpdateRequest studentUpdateRequest) {
        Student student = new Student();
        student.setStudentId(studentUpdateRequest.getStudentId());
        student.setStudentName(studentUpdateRequest.getStudentName());
        student.setStudentPhone(studentUpdateRequest.getStudentPhone());
        student.setStudentBirthdate(studentUpdateRequest.getStudentBirthdate());
        student.setStudentSchool(studentUpdateRequest.getStudentSchool());
        student.setStudentComments(studentUpdateRequest.getStudentComments());
        student.setTeacherId(studentUpdateRequest.getTeacherId());
        return student;
    }


    public static StudentResponse convertToStudentResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setStudentId(student.getStudentId());
        studentResponse.setStudentName(student.getStudentName());
        studentResponse.setStudentPhone(student.getStudentPhone());
        studentResponse.setStudentBirthdate(student.getStudentBirthdate());
        studentResponse.setStudentSchool(student.getStudentSchool());
        studentResponse.setStudentComments(student.getStudentComments());
        studentResponse.setTeacherId(student.getTeacherId());
        return studentResponse;
    }
    public static List<StudentResponse> convertListToStudentResponseList(List<Student> studentList) {
        List<StudentResponse> responseList = new ArrayList<>();
        for (Student student : studentList) {
            responseList.add(convertToStudentResponse(student));
        }
        return responseList;
    }
}
