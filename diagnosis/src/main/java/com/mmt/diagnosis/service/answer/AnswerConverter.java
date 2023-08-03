//package com.mmt.diagnosis.service.answer;
//
//import com.mmt.diagnosis.domain.Answer;
//import com.mmt.diagnosis.domain.Student;
//import com.mmt.diagnosis.dto.answer.IsRecordResponse;
//import com.mmt.diagnosis.dto.student.StudentResponse;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AnswerConverter {
//    public static IsRecordResponse convertToIsRecordResponse(Answer answer) {
//        IsRecordResponse isRecordResponse = new IsRecordResponse(false);
//        isRecordResponse.setAnswerId(answer.getAnswerId());
//        isRecordResponse.setTestId(answer.getTestId());
//        isRecordResponse.setTestName(answer.getTestName());
//        if (answer.getAnswerCode() != null){
//            isRecordResponse.setRecord(true);
//        }
//        return isRecordResponse;
//    }
//    public static List<IsRecordResponse> convertListToIsRecordResponseList(List<Answer> answerList) {
//        List<IsRecordResponse> responseList = new ArrayList<>();
//        for (Answer answer : answerList) {
//            responseList.add(convertToIsRecordResponse(answer));
//        }
//        return responseList;
//    }
//
//}
