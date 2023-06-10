package com.mmt.mymathteacher.repository;

import com.mmt.mymathteacher.domain.users.Learner;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class LearnerRepository {

    private static final Map<Long, Learner> store = new ConcurrentHashMap<>(); //static 사용
    // AtomicLong 시도했는데 오류나서 우선 long으로
    private static long sequence = 0L; //static 사용
    public Learner save(Learner learner) {
        learner.setLearnerId(++sequence);
        // 가입일도 자동으로 셋 되도록 설정
        learner.setLearnerJoindate(String.valueOf(LocalDate.now()));
        store.put(learner.getLearnerId(), learner);
        return learner;
    }

    // 옵셔널로 감쌀까?
    public Learner findById(Long learnerId) {
        return store.get(learnerId);
    }

    // 이름으로 찾기 기능 추가
    public Optional<Learner> findByName(String name){
        return store.values().stream().filter(learner -> learner.getLearnerName().equals(name)).findAny();
    }

    public List<Learner> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long LearnerId, Learner updateParam) {
        Learner findLearner = findById(LearnerId);
        findLearner.setLearnerName(updateParam.getLearnerName());
        findLearner.setLearnerPassword(updateParam.getLearnerPassword());
        findLearner.setLearnerSchool(updateParam.getLearnerSchool());
        findLearner.setLearnerBirthdate(updateParam.getLearnerBirthdate());
        findLearner.setLearnerComments(updateParam.getLearnerComments());
    }

    public void clearStore() {
        store.clear();
    }

}
