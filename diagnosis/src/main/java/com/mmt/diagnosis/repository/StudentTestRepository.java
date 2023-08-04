package com.mmt.diagnosis.repository;

import com.mmt.diagnosis.domain.StudentTests;

import java.util.List;

public interface StudentTestRepository {

    void save(Long studentId, Long testId);

    List<StudentTests> findByStudentId(Long studentId);

    StudentTests findIds(Long studentTestId);

}
