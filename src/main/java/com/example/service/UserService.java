package com.example.service;

import com.example.model.User;

public class UserService {
    
    public User login(String username, String password) {
        if ("thaiduy".equals(username) && "123456".equals(password)) {
            User mockUser = new User();
            mockUser.setUserName("thaiduy");
            mockUser.setPassWord("123456");
            mockUser.setFullName("Thái Duy (Mock)");
            mockUser.setRoleid(3);
            return mockUser;
        }
        return null;
    }

    public boolean checkExistUsername(String username) {
        return "thaiduy".equals(username);
    }

    // Hàm giả lập Register
    public boolean register(String username, String password, String email, String fullname, String phone) {
        if (checkExistUsername(username)) {
            return false;
        }
        return true;
    }
}