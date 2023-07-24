package com.mmt.diagnosis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String userId;
    private String userPassword;
    private String userName;
    private String userPhone;

}
