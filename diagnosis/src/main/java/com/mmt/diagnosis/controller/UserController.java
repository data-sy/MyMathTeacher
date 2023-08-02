package com.mmt.diagnosis.controller;

import com.mmt.diagnosis.dto.user.UserCreateRequest;
import com.mmt.diagnosis.service.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public void create(@RequestBody UserCreateRequest request) {
        userService.join(request);
    }

}
