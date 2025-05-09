package com.example.MTS1.service;

import com.example.MTS1.model.Book;
import com.example.MTS1.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        log.info("Fetching all books");
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        log.info("Fetching book by id: {}", id);
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book saveBook(Book book) {
        log.info("Saving book: {}", book.getTitle());
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        log.info("Deleting book with id: {}", id);
        bookRepository.deleteById(id);
    }
}
