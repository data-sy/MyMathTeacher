package com.mmt.diagnosis.repository;

public interface AnswerRepository {

    void save(Long studentId, Long testId);
}
