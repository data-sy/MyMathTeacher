package com.mmt.diagnosis.repository;

import com.mmt.diagnosis.domain.Probability;

import java.util.List;

public interface ProbabilityRepository {

    void save(List<Probability> probabilities);

    List<Probability> findToConcept(Long answerId, int conceptId);

    List<String> findConceptNameUnder50(Long studentTestId);

    List<String> findToConceptName(Long studentTestId);

    List<String> findFromConceptName(Long studentTestId);

}
