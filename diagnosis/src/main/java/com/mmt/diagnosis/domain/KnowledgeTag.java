package com.mmt.diagnosis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeTag {

    private int knowledgeTagId;
    private int fromConceptId;
    private int ToConceptId;

}
