package com.example.service;

import com.example.dao.jpa.UserDaoJpa;
import com.example.entity.UserEntity;

public class UserServiceJpaImpl implements IUserServiceJpa {
    private IUserDao userDao = new UserDaoJpa();

    @Override
    public UserEntity findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void update(UserEntity user) {
        userDao.update(user);
    }
}