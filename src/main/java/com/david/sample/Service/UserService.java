package com.david.sample.Service;

import com.david.sample.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User findUserByName(String theUserName);
    void save(User user);
}
