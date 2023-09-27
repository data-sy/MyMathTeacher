package com.mmt.diagnosis.dto.AI;

import lombok.Data;

import java.util.List;

@Data
public class AIInputResponse {

    private Long studentTestId;
    private List<List<Integer>> answerCodeResponseList;

    public AIInputResponse(Long studentTestid) {
        this.studentTestId = studentTestid;
    }
}
