package com.mmt.diagnosis.dto.AI;

import lombok.Data;

import java.util.List;

@Data
public class AIInputResponse {

    private Long studentTestid;
    private List<List<List<Integer>>> answerCodeResponseList;

    public AIInputResponse(Long studentTestid) {
        this.studentTestid = studentTestid;
    }
}
