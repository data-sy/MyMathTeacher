package com.mmt.diagnosis.dto.AI;

import com.mmt.diagnosis.domain.AnswerCode;
import com.mmt.diagnosis.dto.answer.AnswerCodeResponse;
import lombok.Data;

import java.util.List;

@Data
public class AIInputResponse {

    private Long studentTestid;
    private List<List<AnswerCodeResponse>> answerCodeResponseList;

    public AIInputResponse(Long studentTestid) {
        this.studentTestid = studentTestid;
    }
}
