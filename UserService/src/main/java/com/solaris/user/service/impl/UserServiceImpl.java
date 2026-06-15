package com.solaris.user.service.impl;

import com.solaris.user.service.UserService;
import com.solaris.user.service.entity.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    @Override
    public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {

        return new User(firstName, lastName, email, password, repeatPassword, UUID.randomUUID().toString());
    }
}
