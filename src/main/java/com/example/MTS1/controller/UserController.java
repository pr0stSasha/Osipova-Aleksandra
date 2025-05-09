package com.example.MTS1.controller;

import com.example.MTS1.model.User;
import com.example.MTS1.model.Course;
import com.example.MTS1.repository.UserRepository;
import com.example.MTS1.repository.CourseRepository;
import com.example.MTS1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository, CourseRepository courseRepository, UserService userService) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/course/{courseId}")
    public List<User> getUsersByCourse(@PathVariable Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        return userRepository.findByCourse(course);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}
