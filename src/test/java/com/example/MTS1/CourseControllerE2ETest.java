package com.example.MTS1;

import com.example.MTS1.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.yaml")
class CourseControllerE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String baseUrl;

    @BeforeEach
    void init() {
        baseUrl = "http://localhost:" + port + "/api/courses";
    }

    @Test
    void testCreateAndGetCourse() {
        Course course = new Course(null, "Math", "Algebra", null, null);
        ResponseEntity<Course> post = restTemplate.postForEntity(baseUrl, course, Course.class);

        assertEquals(HttpStatus.CREATED, post.getStatusCode());
        Long courseId = post.getBody().getId();

        ResponseEntity<Course> get = restTemplate.getForEntity(baseUrl + "/" + courseId, Course.class);
        assertEquals(HttpStatus.OK, get.getStatusCode());
        assertEquals("Math", get.getBody().getTitle());
    }
}
