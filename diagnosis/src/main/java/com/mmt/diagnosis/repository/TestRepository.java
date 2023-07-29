package com.mmt.diagnosis.repository;

import com.mmt.diagnosis.domain.Test;

import java.util.List;

public interface TestRepository {

    List<Test> findAll();

    Test findNameComments(Long testId);

}
