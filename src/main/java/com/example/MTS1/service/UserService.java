package com.example.MTS1.service;

import com.example.MTS1.model.User;
import com.example.MTS1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> createUsersBatch(List<User> users) {
        return userRepository.saveAll(users);
    }

    public Optional<User> updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            return userRepository.save(existingUser);
        });
    }

    public Optional<User> updateUserEmail(Long id, String email) {
        return userRepository.findById(id).map(user -> {
            user.setEmail(email);
            return userRepository.save(user);
        });
    }

    public Optional<User> patchUser(Long id, User partialData) {
        return userRepository.findById(id).map(user -> {
            if (partialData.getName() != null) user.setName(partialData.getName());
            if (partialData.getEmail() != null) user.setEmail(partialData.getEmail());
            return userRepository.save(user);
        });
    }

    public Optional<User> patchUserName(Long id, String name) {
        return userRepository.findById(id).map(user -> {
            user.setName(name);
            return userRepository.save(user);
        });
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteUsersByIds(List<Long> ids) {
        userRepository.deleteAllById(ids);
    }
}
