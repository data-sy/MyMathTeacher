package com.mmt.diagnosis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Concept {

    private int conceptId;
    private String conceptName;
    private String conceptSchoolLevel;
    private String conceptGradeLevel;
    private String conceptSemester;
    private String conceptDescription;
    private String conceptChapterId;
    private String conceptChapterName;
    private String conceptAchievementId;
    private String conceptAchievementName;

}
