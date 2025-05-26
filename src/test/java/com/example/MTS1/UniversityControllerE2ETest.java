package com.example.MTS1;

import com.example.MTS1.model.University;
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
class UniversityControllerE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String baseUrl;

    @BeforeEach
    void setup() {
        baseUrl = "http://localhost:" + port + "/api/universities";
    }

    @Test
    void testCreateUniversity() {
        University university = new University(null, "MIT", "USA", null);
        ResponseEntity<University> response = restTemplate.postForEntity(baseUrl, university, University.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("MIT", response.getBody().getName());
    }
}
