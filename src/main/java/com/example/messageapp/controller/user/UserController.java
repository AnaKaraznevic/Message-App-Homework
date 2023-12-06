package com.example.messageapp.controller.user;

import com.example.messageapp.exception.FailedToCreateUserException;
import com.example.messageapp.exception.UserAlreadyExistsException;
import com.example.messageapp.model.User;
import com.example.messageapp.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers().stream().map(UserResponse::new).toList();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {
        if (userService.getUserByEmail(userRequest.getEmail()) != null) {
            throw new UserAlreadyExistsException("User with email " + userRequest.getEmail() + " already registered.");
        } else if (userService.createUser(userRequest.toUser()) > 0) {
            var userByEmail = userService.getUserByEmail(userRequest.getEmail());
            return new UserResponse(userByEmail);
        } else {
            throw new FailedToCreateUserException("Failed to create user.");
        }
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteAndAnonymizeUser(userId);
    }
}
