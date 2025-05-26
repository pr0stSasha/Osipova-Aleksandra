package com.example.MTS1;

import com.example.MTS1.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.yaml")
class UserControllerE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api/users";
    }

    @Test
    void testGetAllUsers() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(baseUrl, User[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testCreateUser() {
        User user = new User(null, "Alice", "alice@example.com", null, null);
        ResponseEntity<User> response = restTemplate.postForEntity(baseUrl, user, User.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Alice", response.getBody().getName());
    }

    @Test
    void testDeleteUser_NotFound() {
        ResponseEntity<Void> response = restTemplate.exchange(baseUrl + "/999", HttpMethod.DELETE, null, Void.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
