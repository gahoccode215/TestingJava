package com.solaris.user.service;

import com.solaris.user.exception.EmailNotificationServiceException;
import com.solaris.user.exception.UserServiceException;
import com.solaris.user.entity.User;
import com.solaris.user.service.impl.UserServiceImpl;
import com.solaris.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;
    @InjectMocks
    UserServiceImpl userService;
    @Mock
    UserRepository userRepository;

    @Mock
    MailService mailService;

    @BeforeEach
    void init(){
        firstName = "Alice";
        lastName = "Smith";
        email = "alice.smith@example.com";
        password = "password123";
        repeatPassword = "password123";
    }
    @Test
    @DisplayName("Test create user when user details provided returns user object")
    void testCreateUser_whenUserDetailsProvided_returnsUserObject(){

        when(userRepository.save(any(User.class))).thenReturn(true);
        //act
        User user = userService.createUser(firstName, lastName, email, password, repeatPassword);

        //assert
        assertNotNull(user, "User object should not be null");
        assertEquals(firstName, user.getFirstName(), "User object should contain same first name");
        assertEquals(lastName, user.getLastName(), "User object should contain same last name");
        assertEquals(email, user.getEmail(), "User object should contain same email");
        assertNotNull(user.getId(), "User object should have an ID");
        Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("Test create user when first name is empty throws IllegalArgumentException")
    void testCreateUser_whenFirstNameIsEmpty_throwsIllegalArgumentException(){
        String firstName = "";
        String expectedExceptionMessage = "First name cannot be empty";

        //act and assert
        IllegalArgumentException thrown =assertThrows(IllegalArgumentException.class, () -> userService.createUser(firstName, lastName, email, password, repeatPassword), "First name should not be empty");

        assertEquals(expectedExceptionMessage, thrown.getMessage(), "Exception message should match");
    }

    @Test
    @DisplayName("Test create user when save method throws exception throws UserServiceException")
    void testCreateUser_whenSaveMethodThrowsException_thenThrowsUserServiceException(){
    when(userRepository.save(any(User.class))).thenThrow(RuntimeException.class);
    //act and assert
    assertThrows(UserServiceException.class, () -> userService.createUser(firstName, lastName, email, password, repeatPassword), "User creation failed");
    Mockito.verify(userRepository, Mockito.times(1)).save(any(User.class));
    }

    @Test
    void testCreateUser_whenEmailNotificationExceptionThrown_throwsUserServiceException(){
        when(userRepository.save(any(User.class))).thenReturn(true);

        doThrow(EmailNotificationServiceException.class)
                .when(mailService)
                .verifyEmail(any(User.class));

        assertThrows(UserServiceException.class, () -> userService.createUser(firstName, lastName, email, password, repeatPassword), "User creation failed");
        Mockito.verify(mailService, Mockito.times(1)).verifyEmail(any(User.class));
    }
    @DisplayName("Schedule Email Confirmation is excuted")
    @Test
    void testCreateUser_whenUserCreated_verifyEmailIsCalled(){
        when(userRepository.save(any(User.class))).thenReturn(true);
        doCallRealMethod().when(mailService).verifyEmail(any(User.class));
        userService.createUser(firstName, lastName, email, password, repeatPassword);
        Mockito.verify(mailService, Mockito.times(1)).verifyEmail(any(User.class));
    }
}
