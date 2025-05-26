package com.example.MTS1.controller;

import com.example.MTS1.model.Book;
import com.example.MTS1.service.BookService;
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

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @DisplayName("✅ Позитивный тест: Получение книги по ID")
    void getBookById_success() throws Exception {
        Book book = Book.builder()
                .id(1L)
                .title("Clean Code")
                .author("Robert Martin")
                .build();

        Mockito.when(bookService.findBookById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.author").value("Robert Martin"));
    }

    @Test
    @DisplayName("✅ Позитивный тест: Получение другой книги")
    void getBookById_success_other() throws Exception {
        Book book = Book.builder()
                .id(2L)
                .title("Effective Java")
                .author("Joshua Bloch")
                .build();

        Mockito.when(bookService.findBookById(2L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Effective Java"))
                .andExpect(jsonPath("$.author").value("Joshua Bloch"));
    }

    @Test
    @DisplayName("❌ Негативный тест: Книга не найдена")
    void getBookById_notFound() throws Exception {
        Mockito.when(bookService.findBookById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/99"))
                .andExpect(status().isNotFound());
    }
}
