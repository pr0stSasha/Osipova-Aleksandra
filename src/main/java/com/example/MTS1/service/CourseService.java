package com.example.MTS1.service;

import com.example.MTS1.model.Course;
import com.example.MTS1.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    // Исправлено имя метода на findAllCourses() — чтобы совпадало с контроллером
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> findCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Метод назван saveCourse(), чтобы совпадать с контроллером createCourse()
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> saveAllCourses(List<Course> courses) {
        return courseRepository.saveAll(courses);
    }

    public Optional<Course> updateCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id).map(existingCourse -> {
            existingCourse.setTitle(updatedCourse.getTitle());
            existingCourse.setDescription(updatedCourse.getDescription());
            return courseRepository.save(existingCourse);
        });
    }

    public Optional<Course> updateCourseDescription(Long id, String description) {
        return courseRepository.findById(id).map(course -> {
            course.setDescription(description);
            return courseRepository.save(course);
        });
    }

    public Optional<Course> patchCourse(Long id, Course partialData) {
        return courseRepository.findById(id).map(course -> {
            if (partialData.getTitle() != null) course.setTitle(partialData.getTitle());
            if (partialData.getDescription() != null) course.setDescription(partialData.getDescription());
            return courseRepository.save(course);
        });
    }

    // Добавлен отсутствующий метод patchCourseTitle
    public Optional<Course> patchCourseTitle(Long id, String title) {
        return courseRepository.findById(id).map(course -> {
            course.setTitle(title);  // Если в Course поле называется name, используем setName
            return courseRepository.save(course);
        });
    }

    public boolean deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteCoursesByIds(List<Long> ids) {
        courseRepository.deleteAllById(ids);
    }
}
