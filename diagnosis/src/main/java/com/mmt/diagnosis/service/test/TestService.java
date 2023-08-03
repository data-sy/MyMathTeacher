package com.mmt.diagnosis.service.test;

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

    public TestResponse findOne(Long testId){
        return TestConverter.convertToTestResponse(testRepository.findById(testId));
    }

}
