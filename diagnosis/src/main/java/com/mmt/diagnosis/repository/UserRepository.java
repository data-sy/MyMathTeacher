package com.mmt.diagnosis.repository;

public interface UserRepository {

    void save(String id, String password, String name, String phone);

}
