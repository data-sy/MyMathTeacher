package com.mmt.diagnosis.service.answer;

import com.mmt.diagnosis.domain.Answer;
import com.mmt.diagnosis.domain.AnswerCode;
import com.mmt.diagnosis.dto.answer.AnswerCodeRequest;
import com.mmt.diagnosis.dto.answer.AnswerRequest;

import java.util.ArrayList;
import java.util.List;

public class AnswerConverter {

    public static AnswerCode convertToAnswerCode(AnswerCodeRequest request){
        AnswerCode answerCode = new AnswerCode();
        answerCode.setItemId(request.getItemId());
        answerCode.setAnswerCode(request.getAnswerCode());
        return answerCode;
    }

    public static List<AnswerCode> convertListToAnswerCodeList(List<AnswerCodeRequest> requestList) {
        List<AnswerCode> answerCodeList = new ArrayList<>();
        for (AnswerCodeRequest request : requestList) {
            answerCodeList.add(convertToAnswerCode(request));
        }
        return answerCodeList;
    }

    public static Answer convertToAnswer(AnswerRequest request) {
        Answer answer = new Answer();
        answer.setStudentTestId(request.getStudentTestId());
        answer.setAnswerCodeList(convertListToAnswerCodeList(request.getAnswerCodeRequestList()));
        return answer;
    }

}
