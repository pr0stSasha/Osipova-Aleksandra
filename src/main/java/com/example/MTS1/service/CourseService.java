package com.example.MTS1.service;

import com.example.MTS1.model.Course;
import com.example.MTS1.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        log.info("Fetching all courses");
        return courseRepository.findAll();
    }

    public Course getCourseById(Long id) {
        log.info("Fetching course by id: {}", id);
        return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
    }

    public void deleteCourse(Long id) {
        log.info("Deleting course with id: {}", id);
        courseRepository.deleteById(id);
    }
}
