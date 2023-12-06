package com.example.messageapp.controller.user;

import com.example.messageapp.model.User;

public record UserResponse(Long id, String name, String email) {
    public UserResponse(User user) {
        this(user.getId(), user.getName(), user.getEmail());
    }
}
