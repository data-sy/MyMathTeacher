package com.mmt.diagnosis.service.studentTest;

import com.mmt.diagnosis.domain.StudentTests;
import com.mmt.diagnosis.dto.answer.IsRecordResponse;
import com.mmt.diagnosis.dto.preview.PreviewResponse;
import com.mmt.diagnosis.repository.StudentTestRepository;
import com.mmt.diagnosis.service.testItem.TestItemService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentTestService {

    private final StudentTestRepository studentTestRepository;
    private final TestItemService testItemService;

    public StudentTestService(StudentTestRepository studentTestRepository, TestItemService testItemService) {
        this.studentTestRepository = studentTestRepository;
        this.testItemService = testItemService;
    }

    public void create(Long studentId, Long testId){
        studentTestRepository.save(studentId, testId);
    }

    public List<IsRecordResponse> findTests(Long studentId){
        return StudentTestConverter.convertListToStudentTestResponseList(studentTestRepository.findByStudentId(studentId));
    }

    public PreviewResponse preview(Long studentTestId) {
        // 리팩토링 : 지금은 메서드 재사용으로 해결. 리팩토링 때는 성능, 클린코드 고려해보자
        // st_id에서 s_id, t_id 꺼내오기
        StudentTests studentTests = studentTestRepository.findIds(studentTestId);
        // testItemService의 preview 사용
        PreviewResponse previewResponse = testItemService.preview(studentTests.getStudentId(), studentTests.getTestId());
        previewResponse.setStudentTestId(studentTestId);
        return previewResponse;
    }

}
