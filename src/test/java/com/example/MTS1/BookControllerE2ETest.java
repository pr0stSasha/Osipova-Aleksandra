package com.example.MTS1;

import com.example.MTS1.model.Book;
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
class BookControllerE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String baseUrl;

    @BeforeEach
    void setup() {
        baseUrl = "http://localhost:" + port + "/api/books";
    }

    @Test
    void testCreateBook() {
        Book book = new Book(null, "1984", "George Orwell", null);
        ResponseEntity<Book> response = restTemplate.postForEntity(baseUrl, book, Book.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("1984", response.getBody().getTitle());
    }
}
