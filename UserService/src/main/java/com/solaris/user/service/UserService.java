package com.solaris.user.service;

import com.solaris.user.service.entity.User;

public interface UserService {
    User createUser(
            String firstName,
            String lastName,
            String email,
            String password,
            String repeatPassword
    );
}

