//package com.mmt.diagnosis.service.answer;
//
//import com.mmt.diagnosis.dto.answer.IsRecordRequest;
//import com.mmt.diagnosis.dto.answer.IsRecordResponse;
//import com.mmt.diagnosis.dto.viewDetail.ViewDetailRequest;
//import com.mmt.diagnosis.repository.AnswerRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class AnswerService {
//
//    private final AnswerRepository answerRepository;
//
//    public AnswerService(AnswerRepository answerRepository) {
//        this.answerRepository = answerRepository;
//    }
//
//    public void create(ViewDetailRequest request) {
//        answerRepository.save(request.getStudentId(), request.getTestId());
//    }
//
//    public List<IsRecordResponse> findTests(IsRecordRequest request) {
//        return AnswerConverter.convertListToIsRecordResponseList(answerRepository.findByStudentId(request.getStudentId()));
//    }
//
//}
