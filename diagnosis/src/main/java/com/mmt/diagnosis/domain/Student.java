package com.mmt.diagnosis.domain;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private int studentId;
    private String studentName;
    private String studentPhone;
    private LocalDate studentBirthdate;
    private String studentSchool;
    private String studentComments;
    private String teacherId;

}
