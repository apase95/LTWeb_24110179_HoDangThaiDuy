package com.example.service;

import com.example.entity.UserEntity;

public interface IUserDao {
    UserEntity findByUsername(String username);
    void update(UserEntity user);
}