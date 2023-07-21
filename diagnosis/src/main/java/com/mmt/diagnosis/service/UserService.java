package com.mmt.diagnosis.service;

import com.mmt.diagnosis.domain.User;
import com.mmt.diagnosis.dto.user.UserConverter;
import com.mmt.diagnosis.dto.user.UserRequest;
import com.mmt.diagnosis.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserRequest request) {
        userRepository.saveUser(request.getUserId(), request.getUserPassword(), request.getUserName(), request.getUserPhone());
    }

}
