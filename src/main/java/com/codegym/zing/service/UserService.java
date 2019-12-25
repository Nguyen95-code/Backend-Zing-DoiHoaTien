package com.codegym.zing.service;

import com.codegym.zing.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends GeneralService<User>, UserDetailsService {
    User findByUsername(String username);

    User getCurrentUser();

    UserDetails loadUserById(Long id);

    boolean checkLogin(User user);

    boolean isRegister(User user);
}
