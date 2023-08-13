package com.mmt.diagnosis.dto.answer;

import lombok.Data;

@Data
public class AnswerCodeCreateRequest {

    private Long itemId;
    private Integer answerCode;

    // 디버깅 용 : System.out.println(answerCodeRequest);
    @Override
    public String toString() {
        return String.format("AnswerCodeRequest{ itemId = %d, answerCode = %d }", itemId, answerCode);
    }

}
