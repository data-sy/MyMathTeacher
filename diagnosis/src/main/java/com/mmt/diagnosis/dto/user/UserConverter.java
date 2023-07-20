package com.mmt.diagnosis.dto.user;

import com.mmt.diagnosis.domain.User;

public class UserConverter {

    public static User convertToUser(UserRequest userRequest) {
        User user = new User();
        user.setUserId(userRequest.getUserId());
        user.setUserPassword(userRequest.getUserPassword());
        user.setUserName(userRequest.getUserName());
        user.setUserPhone(userRequest.getUserPhone());
        return user;
    }

    public static UserResponse convertToUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(user.getUserId());
        userResponse.setUserPassword(user.getUserPassword());
        userResponse.setUserName(user.getUserName());
        userResponse.setUserPhone(user.getUserPhone());
        return userResponse;
    }

}
