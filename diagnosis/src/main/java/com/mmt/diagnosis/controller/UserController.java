package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.user.UserRequest;
import com.mmt.diagnosis.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserRequest request) {
        userService.saveUser(request);
    }

}
