package com.solaris.user.service;

import com.solaris.user.service.entity.User;
import com.solaris.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {

    @Test
    @DisplayName("Test create user when user details provided returns user object")
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
        assertEquals(firstName, user.getFirstName(), "User object should contain same first name");
        assertEquals(lastName, user.getLastName(), "User object should contain same last name");
        assertEquals(email, user.getEmail(), "User object should contain same email");
        assertNotNull(user.getId(), "User object should have an ID");
    }

}
