package com.solaris.user.repository;

import com.solaris.user.entity.User;

public interface UserRepository {

    boolean save(User user);
}
