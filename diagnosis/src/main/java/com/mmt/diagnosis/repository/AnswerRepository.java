package com.mmt.diagnosis.repository;

import com.mmt.diagnosis.domain.Answer;
import com.mmt.diagnosis.domain.AnswerCode;
import com.mmt.diagnosis.domain.Probability;

import java.util.List;

public interface AnswerRepository {

    void save(Answer answer);

    List<AnswerCode> findAnswerCode(Long studentTestId);

    List<Probability> findIds(Long studentTestId);

}
