package com.dh.usermanagement.services;

import com.dh.usermanagement.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    Optional<User> findUserById(Long id);
    List<User> findAllUsers();
    User saveUser(User user);
}
