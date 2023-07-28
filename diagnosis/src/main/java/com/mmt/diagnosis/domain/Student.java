package com.mmt.diagnosis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private Long studentId;
    private String studentName;
    private String studentPhone;
    private LocalDate studentBirthdate;
    private String studentSchool;
    private String studentComments;
    private String teacherId;

}
