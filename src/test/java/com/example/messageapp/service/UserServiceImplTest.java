package com.example.messageapp.service;

import com.example.messageapp.exception.UserNotFoundException;
import com.example.messageapp.model.Role;
import com.example.messageapp.model.User;
import com.example.messageapp.repository.MessageRepository;
import com.example.messageapp.repository.UserRepository;
import com.example.messageapp.service.user.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void getUserById_existingUserId_shouldReturnUser() {

        when(userRepository.findUserById(1L)).thenReturn(Optional.of(new User(1L, "Jonas Jonaitis", "jonas@example.com", Role.REGULAR)));

        User user = userService.getUserById(1L);

        assertNotNull(user);
        assertEquals("Jonas Jonaitis", user.getName());
        assertEquals("jonas@example.com", user.getEmail());
    }

    @Test
    void getUserById_nonExistingUserId_shouldThrowUserNotFoundException() {

        when(userRepository.findUserById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void deleteAndAnonymizeUser_existingUserId_shouldDeleteUserAndAnonymizeMessages() {

        User user = new User(1L, "Jonas Jonaitis", "jonas@example.com", Role.REGULAR);
        when(userRepository.findUserById(1L)).thenReturn(Optional.of(user));

        userService.deleteAndAnonymizeUser(1L);

        verify(userRepository, times(1)).deleteUser(1L);
        verify(messageRepository, times(1)).anonymizeMessagesByUserId(1L, User.ANONYMOUS_USER_ID);
    }

    @Test
    void deleteAndAnonymizeUser_nonExistingUserId_shouldThrowUserNotFoundException() {

        when(userRepository.findUserById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteAndAnonymizeUser(1L));
        verify(userRepository, never()).deleteUser(anyLong());
        verify(messageRepository, never()).anonymizeMessagesByUserId(anyLong(), anyLong());
    }
}
