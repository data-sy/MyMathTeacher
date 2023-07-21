package com.mmt.diagnosis.dto.student;

import com.mmt.diagnosis.domain.Student;

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

}
