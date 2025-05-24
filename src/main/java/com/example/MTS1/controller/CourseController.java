package com.example.MTS1.controller;

import com.example.MTS1.model.Course;
import com.example.MTS1.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> listCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourse(@PathVariable Long id) {
        return courseService.findCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
