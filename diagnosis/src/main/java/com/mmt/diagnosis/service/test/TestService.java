package com.mmt.diagnosis.service.test;

import com.mmt.diagnosis.dto.answer.IsRecordResponse;
import com.mmt.diagnosis.dto.test.TestResponse;
import com.mmt.diagnosis.repository.TestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;

    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<TestResponse> findTests(){
        return TestConverter.convertListToTestResponseList(testRepository.findAll());
    }

    public TestResponse findNameComments(Long testId){
        return TestConverter.convertToTestNameComments(testRepository.findNameComments(testId));
    }

}
