package com.solaris.user.service;

import com.solaris.user.entity.User;

public interface MailService {
    void verifyEmail(User user);
}
