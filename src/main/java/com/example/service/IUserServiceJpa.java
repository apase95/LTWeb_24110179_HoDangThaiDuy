package com.example.service;

import com.example.entity.UserEntity;
import java.util.Date;

public interface IUserServiceJpa {
    UserEntity findByUsername(String username);
    void update(UserEntity user);
    void saveOTP(String username, String otp, Date expiry);
    boolean activateUser(String username, String otp);
    boolean updatePassword(String username, String newPassword);
}