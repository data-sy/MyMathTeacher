package com.mmt.diagnosis.domain;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Student {

    private Long studentId;
    private String studentName;
    private String studentPhone;
    private LocalDate studentBirthdate;
    private String studentSchool;
    private String studentComments;
    private Long teacherId;

}
