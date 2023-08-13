package com.mmt.diagnosis.service.concept;

import com.mmt.diagnosis.domain.Concept;
import com.mmt.diagnosis.dto.concept.ConceptResponse;

public class ConceptConverter {

    public static ConceptResponse convertToConceptResponse(Concept concept){
        ConceptResponse response = new ConceptResponse();
        response.setConceptId(concept.getConceptId());
        response.setConceptName(concept.getConceptName());
        response.setConceptSchoolLevel(concept.getConceptSchoolLevel());
        response.setConceptGradeLevel(concept.getConceptGradeLevel());
        response.setConceptSemester(concept.getConceptSemester());
        response.setConceptDescription(concept.getConceptDescription());
        response.setConceptChapterId(concept.getConceptChapterId());
        response.setConceptChapterName(concept.getConceptChapterName());
        response.setConceptAchievementId(concept.getConceptAchievementId());
        response.setConceptAchievementName(concept.getConceptAchievementName());
        return response;
    }
}
