package com.solaris.user.service.impl;

import com.solaris.user.exception.UserServiceException;
import com.solaris.user.service.UserService;
import com.solaris.user.entity.User;
import com.solaris.user.repository.UserRepository;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {
        if(firstName == null || firstName.trim().isEmpty()){
            throw new IllegalArgumentException("First name cannot be empty");
        }

        User user = new User(firstName, lastName, email, password, repeatPassword, UUID.randomUUID().toString());
        boolean isUserCreated;
        try{
            isUserCreated = userRepository.save(user);
        }catch (RuntimeException ex){
            throw new UserServiceException("Could not create user");
        }
        if(!isUserCreated) throw new UserServiceException("Could not create user");
        return user;
    }
}
