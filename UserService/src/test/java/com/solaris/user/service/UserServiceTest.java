package com.solaris.user.service;

import com.solaris.user.service.entity.User;
import com.solaris.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {

    @Test
    void testCreateUser_whenUserDetailsProvided_returnsUserObject(){
        //arrange
        UserService userService = new UserServiceImpl();
        String firstName = "Alice";
        String lastName = "Smith";
        String email = "alice.smith@example.com";
        String password = "password123";
        String repeatPassword = "password123";
        //act
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);

        //assert
        assertNotNull(user, "User object should not be null");
    }
}
