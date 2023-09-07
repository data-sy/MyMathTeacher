//package com.mmt.diagnosis.service.user;
//
//import com.mmt.diagnosis.dto.user.UserCreateRequest;
//import com.mmt.diagnosis.repository.user.UserRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserService {
//
//    private final UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public void join(UserCreateRequest request) {
//        userRepository.save(request.getUserId(), request.getUserPassword(), request.getUserName(), request.getUserPhone());
//    }
//
//}
