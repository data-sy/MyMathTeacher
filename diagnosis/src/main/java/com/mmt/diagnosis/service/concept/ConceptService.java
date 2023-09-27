package com.mmt.diagnosis.service.concept;

import com.mmt.diagnosis.dto.concept.ConceptResponse;
import com.mmt.diagnosis.repository.concept.ConceptRepository;
import org.springframework.stereotype.Service;

@Service
public class ConceptService {

    private final ConceptRepository conceptRepository;

    public ConceptService(ConceptRepository conceptRepository) {
        this.conceptRepository = conceptRepository;
    }

    public ConceptResponse findOne(int conceptId){
        ConceptResponse response = ConceptConverter.convertToConceptResponse(conceptRepository.findById(conceptId));
        response.setToConceptNameList(conceptRepository.findToConceptName(conceptId));
        response.setFromConceptNameList(conceptRepository.findFromConceptName(conceptId));
        return response;
    }

}
