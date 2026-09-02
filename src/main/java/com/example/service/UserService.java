package com.example.service;

import com.example.dao.UserDAO;
import com.example.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public User login(String username, String password) {
        return userDAO.getUserByUsernamePassword(username, password);
    }

    public boolean checkExistUsername(String username) {
        return userDAO.checkExistUsername(username);
    }

    public boolean register(String username, String password, String email, String fullname, String phone) {
        if (checkExistUsername(username)) {
            return false;
        }
        User user = new User();
        user.setEmail(email);
        user.setUserName(username);
        user.setFullName(fullname);
        user.setPassWord(password);
        user.setRoleid(3);
        user.setPhone(phone);
        user.setCreatedDate(new java.util.Date());
        return userDAO.insertUser(user);
    }
}