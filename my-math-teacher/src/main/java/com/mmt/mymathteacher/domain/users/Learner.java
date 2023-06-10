package com.mmt.mymathteacher.domain.users;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Learner {
    private Long learnerId;
    private String learnerName;
    private String learnerPassword;
    private String learnerJoindate;
    private String learnerSchool;
    private String learnerBirthdate;
    private String learnerComments;
    private Long teacherId;

    // null인 경우를 감안해야 할 때는 int가 아니라 Integer 사용하기

    public Learner(){

    }

    public Learner(String learnerName, String learnerPassword, String learnerSchool, String learnerBirthdate, String learnerComments) {
        this.learnerName = learnerName;
        this.learnerPassword = learnerPassword;
        this.learnerSchool = learnerSchool;
        this.learnerBirthdate = learnerBirthdate;
        this.learnerComments = learnerComments;
    }


}
