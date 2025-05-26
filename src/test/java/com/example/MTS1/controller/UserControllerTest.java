package com.example.MTS1.controller;

import com.example.MTS1.model.User;
import com.example.MTS1.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getUserById_shouldReturnUser_whenExists() throws Exception {
        User user = new User(1L, "Alice", "alice@example.com", null, null);
        Mockito.when(userService.findUserById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    void getAllUsers_shouldReturnListOfUsers() throws Exception {
        List<User> users = List.of(new User(1L, "Bob", "bob@example.com", null, null));
        Mockito.when(userService.findAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("bob@example.com"));
    }

    @Test
    void getUserById_shouldReturnNotFound_whenUserMissing() throws Exception {
        Mockito.when(userService.findUserById(100L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/users/100"))
                .andExpect(status().isNotFound());
    }
}
