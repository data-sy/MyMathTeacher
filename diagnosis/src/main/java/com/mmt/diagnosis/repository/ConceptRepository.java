package com.mmt.diagnosis.repository;

import com.mmt.diagnosis.domain.Concept;

import java.util.List;

public interface ConceptRepository {

    Concept findById(int conceptId);

    List<String> findToConceptName(int conceptId);

    List<String> findFromConceptName(int conceptId);

}
