package com.mmt.diagnosis.repository;

import com.mmt.diagnosis.domain.Test;
import com.mmt.diagnosis.dto.test.IsRecordResponse;

import java.util.List;

public interface TestRepository {

    List<Test> findAll();
    List<IsRecordResponse> findByStudentId(Long studuntId);

    Test findNameComments(Long testId);

}
