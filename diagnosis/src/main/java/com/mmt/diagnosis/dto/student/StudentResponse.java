package com.mmt.diagnosis.dto.student;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentResponse {

    private int studentId;
    private String studentName;
    private String studentPhone;
    private LocalDate studentBirthdate;
    private String studentSchool;
    private String studentComments;
    private String teacherId;

}
