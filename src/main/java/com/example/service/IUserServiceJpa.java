package com.example.service;

import com.example.entity.UserEntity;
import java.util.Date;

public interface IUserServiceJpa {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
    void update(UserEntity user);
    boolean activateUser(String username, String otp);
    boolean updatePassword(String username, String newPassword);
    boolean verifyOTP(String username, String otp);
    void saveOTP(String username, String otp, Date expiry);
}