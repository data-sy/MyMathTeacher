package com.mmt.diagnosis.repository.test;

import com.mmt.diagnosis.domain.Test;

import java.util.List;

public interface TestRepository {

    Long save(String testName, String testComments);

    List<Test> findAll();

    Test findById(Long testId);

}
