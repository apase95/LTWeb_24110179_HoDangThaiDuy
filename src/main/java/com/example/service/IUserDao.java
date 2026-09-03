package com.example.service;

import com.example.entity.UserEntity;

public interface IUserDao {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
    void update(UserEntity user);
}