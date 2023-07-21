package com.mmt.diagnosis.repository;

import com.mmt.diagnosis.domain.User;

import java.util.Optional;

public interface UserRepository {

    void saveUser(String id, String password, String name, String phone);

    Optional<User> findById(String id);

}
