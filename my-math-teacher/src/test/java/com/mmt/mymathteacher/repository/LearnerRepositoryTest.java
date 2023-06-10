package com.mmt.mymathteacher.repository;

import com.mmt.mymathteacher.domain.users.Learner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LearnerRepositoryTest {
    LearnerRepository learnerRepository = new LearnerRepository();
    @AfterEach
    void afterEach() {
        learnerRepository.clearStore();
    }
    @Test
    void save() {
        //given
        Learner learner = new Learner("김가영", "pw1", "문산중학교", "2010/01/01", "수학에 관심이 많다.");
        //when
        Learner savedlearner = learnerRepository.save(learner);
//then
        Learner findlearner = learnerRepository.findById(learner.getLearnerId());
        assertThat(findlearner).isEqualTo(savedlearner);
    }

    @Test
    void findById() {
    }
    @Test
    void findByName() {
        // given
        Learner learner1 = new Learner();
        learner1.setLearnerName("김가영");
        learnerRepository.save(learner1);
        Learner learner2 = new Learner();
        learner2.setLearnerName("이나영");
        learnerRepository.save(learner2);
        // when
        Learner result = learnerRepository.findByName("김가영").get();
        // then
        assertThat(result).isEqualTo(learner1);
    }
    @Test
    void findAll() {
        //given
        Learner learner1 = new Learner("김가영", "pw1", "문산중학교", "2010/01/01", "수학에 관심이 많다.");
        Learner learner2 = new Learner("이나영", "pw2", "한국고등학교", "2013/01/01", "고등학생, 중학교 과정 맞춤 복습을 원함");
        learnerRepository.save(learner1);
        learnerRepository.save(learner2);
//when
        List<Learner> result = learnerRepository.findAll();
//then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(learner1, learner2);
    }

    @Test
    void update() {
        //given
        Learner learner = new Learner("김가영", "pw1", "문산중학교", "2010/01/01", "수학에 관심이 많다.");
        Learner savedlearner = learnerRepository.save(learner);
        Long learnerId = savedlearner.getLearnerId();
//when
        Learner updateParam = new Learner("이나영", "pw2", "한국고등학교", "2013/01/01", "고등학생, 중학교 과정 맞춤 복습을 원함");
        learnerRepository.update(learnerId, updateParam);
        Learner findlearner = learnerRepository.findById(learnerId);
//then
        assertThat(findlearner.getLearnerName()).isEqualTo(updateParam.getLearnerName());
        assertThat(findlearner.getLearnerPassword()).isEqualTo(updateParam.getLearnerPassword());
        assertThat(findlearner.getLearnerSchool()).isEqualTo(updateParam.getLearnerSchool());
        assertThat(findlearner.getLearnerBirthdate()).isEqualTo(updateParam.getLearnerBirthdate());
        assertThat(findlearner.getLearnerComments()).isEqualTo(updateParam.getLearnerComments());
    }

    @Test
    void clearStore() {
    }
}