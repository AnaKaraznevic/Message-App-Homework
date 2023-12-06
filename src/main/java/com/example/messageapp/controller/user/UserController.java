package com.example.messageapp.controller.user;

import com.example.messageapp.exception.FailedToCreateUserException;
import com.example.messageapp.exception.UserAlreadyExistsException;
import com.example.messageapp.model.User;
import com.example.messageapp.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get All Users", description = "Retrieve a list of all users.")
    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers().stream().map(UserResponse::new).toList();
    }

    @Operation(summary = "Get User by ID", description = "Retrieve user details by ID.")
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @Operation(summary = "Create User", description = "Create a new user.")
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

    @Operation(summary = "Delete User", description = "Delete a user and anonymize associated messages.")
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteAndAnonymizeUser(userId);
    }
}
