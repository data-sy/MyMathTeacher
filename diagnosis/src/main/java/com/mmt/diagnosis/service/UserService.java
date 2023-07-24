package com.mmt.diagnosis.service;

import com.mmt.diagnosis.dto.user.UserCreateRequest;
import com.mmt.diagnosis.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void join(UserCreateRequest request) {
        userRepository.save(request.getUserId(), request.getUserPassword(), request.getUserName(), request.getUserPhone());
    }

}
