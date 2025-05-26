package com.example.MTS1.service;

import com.example.MTS1.model.User;
import com.example.MTS1.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
