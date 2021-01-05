package com.david.sample.repository;

import com.david.sample.model.User;

public interface UserDao {
    User findByUserName(String username);
    void save(User user);
}
