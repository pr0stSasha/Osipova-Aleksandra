package com.example.MTS1.controller;

import com.example.MTS1.api.CourseControllerDocs;
import com.example.MTS1.model.Course;
import com.example.MTS1.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController implements CourseControllerDocs {

    private final CourseService courseService;

    // 2 GET
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseService.findCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 2 POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@RequestBody Course course) {
        return courseService.saveCourse(course);
    }

    @PostMapping("/batch")
    public List<Course> createCoursesBatch(@RequestBody List<Course> courses) {
        return courseService.saveAllCourses(courses);
    }

    // 2 PUT
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourse(id, course)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/description")
    public ResponseEntity<Course> updateCourseDescription(@PathVariable Long id, @RequestBody Course course) {
        return courseService.updateCourseDescription(id, course.getDescription())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 2 PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Course> patchCourse(@PathVariable Long id, @RequestBody Course course) {
        return courseService.patchCourse(id, course)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/title")
    public ResponseEntity<Course> patchCourseTitle(@PathVariable Long id, @RequestBody Course course) {
        return courseService.patchCourseTitle(id, course.getTitle())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 2 DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        if (courseService.deleteCourse(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> deleteCoursesBatch(@RequestBody List<Long> ids) {
        courseService.deleteCoursesByIds(ids);
        return ResponseEntity.noContent().build();
    }
}
