package com.mmt.diagnosis.service.answer;

import com.mmt.diagnosis.domain.Answer;
import com.mmt.diagnosis.domain.AnswerCode;
import com.mmt.diagnosis.dto.answer.AnswerCodeCreateRequest;
import com.mmt.diagnosis.dto.answer.AnswerCodeResponse;
import com.mmt.diagnosis.dto.answer.AnswerCreateRequest;

import java.util.ArrayList;
import java.util.List;

public class AnswerConverter {

    public static AnswerCode convertToAnswerCode(AnswerCodeCreateRequest request){
        AnswerCode answerCode = new AnswerCode();
        answerCode.setItemId(request.getItemId());
        answerCode.setAnswerCode(request.getAnswerCode());
        return answerCode;
    }

    public static List<AnswerCode> convertListToAnswerCodeList(List<AnswerCodeCreateRequest> requestList) {
        List<AnswerCode> answerCodeList = new ArrayList<>();
        for (AnswerCodeCreateRequest request : requestList) {
            answerCodeList.add(convertToAnswerCode(request));
        }
        return answerCodeList;
    }

    public static Answer convertToAnswer(AnswerCreateRequest request) {
        Answer answer = new Answer();
        answer.setStudentTestId(request.getStudentTestId());
        answer.setAnswerCodeList(convertListToAnswerCodeList(request.getAnswerCodeCreateRequestList()));
        return answer;
    }

//    // response 수정으로 더 이상 쓰이지 않는 메서드
//    public static List<Integer> convertToIntegerList(AnswerCode answerCode){
//        List<Integer> integerList = new ArrayList<>();
//        integerList.add(answerCode.getSkillId());
//        integerList.add(answerCode.getAnswerCode());
//        return integerList;
//    }
//    public static List<List<List<Integer>>> convertToThreeDimensionalList(List<List<AnswerCode>> lists){
//        List<List<List<Integer>>> result = new ArrayList<>();
//        for (List<AnswerCode> list : lists ){
//            List<List<Integer>> temp = new ArrayList<>();
//            for (AnswerCode answerCode : list){
//                temp.add(convertToIntegerList(answerCode));
//            }
//            result.add(temp);
//        }
//        return result;
//    }

    public static AnswerCodeResponse convertToAnswerCodeResponse(AnswerCode answerCode){
        AnswerCodeResponse answerCodeResponse = new AnswerCodeResponse();
        answerCodeResponse.setSkillId(answerCode.getSkillId());
        answerCodeResponse.setAnswerCode(answerCode.getAnswerCode());
        return answerCodeResponse;
    }
    public static List<AnswerCodeResponse> convertListToAnswerCodeResponseList(List<AnswerCode> answerCodeList) {
        List<AnswerCodeResponse> answerCodeResponseList = new ArrayList<>();
        for (AnswerCode answerCode : answerCodeList) {
            answerCodeResponseList.add(convertToAnswerCodeResponse(answerCode));
        }
        return answerCodeResponseList;
    }
}
