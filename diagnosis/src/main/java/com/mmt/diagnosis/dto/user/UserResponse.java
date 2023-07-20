package com.mmt.diagnosis.dto.user;

import lombok.Data;

@Data
public class UserResponse {

    private String userId;
    private String userPassword;
    private String userName;
    private String userPhone;

}
