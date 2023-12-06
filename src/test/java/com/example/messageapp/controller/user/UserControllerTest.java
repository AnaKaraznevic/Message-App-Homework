package com.example.messageapp.controller.user;

import com.example.messageapp.model.Role;
import com.example.messageapp.model.User;
import com.example.messageapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() throws Exception {

        when(userService.getAllUsers()).thenReturn(Collections.singletonList(new User(1L, "Jonas Jonaitis", "jonas@example.com", Role.REGULAR)));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Jonas Jonaitis"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].email").value("jonas@example.com"));
    }

    @Test
    void getUserById_existingUserId_shouldReturnUser() throws Exception {

        when(userService.getUserById(1L)).thenReturn(new User(1L, "Jonas Jonaitis", "jonas@example.com", Role.REGULAR));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/users/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Jonas Jonaitis"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("jonas@example.com"));
    }
}