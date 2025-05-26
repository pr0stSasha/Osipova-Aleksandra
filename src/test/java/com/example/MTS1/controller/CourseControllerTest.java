package com.example.MTS1.controller;

import com.example.MTS1.model.Course;
import com.example.MTS1.service.CourseService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Test
    @DisplayName("✅ Позитивный тест: Получение курса по ID")
    void getCourseById_success() throws Exception {
        Course course = Course.builder()
                .id(1L)
                .title("Java")
                .description("Java course")
                .build();

        Mockito.when(courseService.findCourseById(1L)).thenReturn(Optional.of(course));

        mockMvc.perform(get("/api/courses/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Java"))
                .andExpect(jsonPath("$.description").value("Java course"));
    }

    @Test
    @DisplayName("✅ Позитивный тест: Получение другого курса")
    void getCourseById_success_other() throws Exception {
        Course course = Course.builder()
                .id(2L)
                .title("Spring")
                .description("Spring Boot course")
                .build();

        Mockito.when(courseService.findCourseById(2L)).thenReturn(Optional.of(course));

        mockMvc.perform(get("/api/courses/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Spring"))
                .andExpect(jsonPath("$.description").value("Spring Boot course"));
    }

    @Test
    @DisplayName("❌ Негативный тест: Курс не найден")
    void getCourseById_notFound() throws Exception {
        Mockito.when(courseService.findCourseById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/courses/99"))
                .andExpect(status().isNotFound());
    }
}
