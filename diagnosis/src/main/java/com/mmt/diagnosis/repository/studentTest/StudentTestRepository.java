package com.mmt.diagnosis.repository.studentTest;

import com.mmt.diagnosis.domain.StudentTests;

import java.util.List;

public interface StudentTestRepository {

    void save(Long studentId, Long testId);

    void save(Long studentId, Long testId, Long diagnosisId);

    List<StudentTests> findByStudentId(Long studentId);

    StudentTests findIds(Long studentTestId);

    List<Long> findStudentTestIds(Long studentTestId);

    StudentTests findDetails(Long studentTestId);

}
