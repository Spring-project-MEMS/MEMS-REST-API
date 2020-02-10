package com.fixit.domain;

import com.fixit.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService  extends UserDetailsService {
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    User findByEgn(String egn);
    User add(User user);
    User update(User user);
    User remove(Long id);
    long count();
    boolean isUsernameTaken(String username);
    boolean isEmailTaken(String email);
    boolean isEgnTaken(String email);
}
