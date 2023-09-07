package com.mmt.diagnosis.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Roles {

    @Id
    private String roleName;
}
