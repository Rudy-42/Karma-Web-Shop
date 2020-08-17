package com.codemart.karmawebshop.service;

import com.codemart.karmawebshop.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    void addUser(User user);

    boolean exists(User user);

    User getUserById(long id);

    User getUserByUsername(String username);

    void delete(long id);
}
