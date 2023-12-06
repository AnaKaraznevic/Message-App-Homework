package com.example.messageapp.service.user;

import com.example.messageapp.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long userId);

    int createUser(User user);

    void deleteAndAnonymizeUser(Long userId);

    User getUserByEmail(String email);
}
