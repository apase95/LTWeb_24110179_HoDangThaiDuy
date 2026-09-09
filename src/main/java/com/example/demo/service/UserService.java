package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public UserEntity findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public void save(UserEntity user) {
        if (user.getPassword() != null && !user.getPassword().startsWith("$2a")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(user);
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public List<UserEntity> search(String keyword) {
        return userRepository.findByUsernameContainingOrFullnameContaining(keyword, keyword);
    }

    public void enableUser(Integer id, boolean active) {
        UserEntity user = findById(id);
        if (user != null) {
            user.setActive(active);
            userRepository.save(user);
        }
    }

    public void saveOTP(String username, String otp, Date expiry) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setOtp(otp);
        user.setOtpExpiry(expiry);
        userRepository.save(user);
    }

    public boolean activateUser(String username, String otp) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        if (user != null && user.getOtp() != null && user.getOtp().equals(otp)) {
            if (user.getOtpExpiry() != null && user.getOtpExpiry().after(new Date())) {
                user.setActive(true);
                user.setOtp(null);
                user.setOtpExpiry(null);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean verifyOTP(String username, String otp) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        if (user != null && user.getOtp() != null && user.getOtp().equals(otp)) {
            if (user.getOtpExpiry() != null && user.getOtpExpiry().after(new Date())) {
                user.setOtp(null);
                user.setOtpExpiry(null);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean updatePassword(String username, String newPassword) {
        UserEntity user = userRepository.findByUsername(username).orElse(null);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}