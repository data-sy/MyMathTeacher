package com.mmt.diagnosis.dto.concept;

import lombok.Data;

import java.util.List;

@Data
public class ConceptResponse {

    private int conceptId;
    private String conceptName;
    private String conceptSchoolLevel;
    private String conceptGradeLevel;
    private String conceptSemester;
    private String conceptDescription;
    private int conceptChapterId;
    private String conceptChapterName;
    private int conceptAchievementId;
    private String conceptAchievementName;
    private List<String> toConceptNameList;
    private List<String> fromConceptNameList;

}
