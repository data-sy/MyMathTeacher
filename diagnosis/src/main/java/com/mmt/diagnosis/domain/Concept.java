package com.mmt.diagnosis.domain;

import lombok.Data;

@Data
public class Concept {

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

}
