package com.example.MTS1.controller;

import com.example.MTS1.api.BookControllerDocs;
import com.example.MTS1.model.Book;
import com.example.MTS1.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController implements BookControllerDocs {

    private final BookService bookService;

    // 2 GET
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 2 POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book createBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @PostMapping("/batch")
    public List<Book> createBooksBatch(@RequestBody List<Book> books) {
        return bookService.saveAllBooks(books);
    }

    // 2 PUT
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(id, book)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/change-title")
    public ResponseEntity<Book> updateBookTitle(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBookTitle(id, book.getTitle())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 2 PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<Book> patchBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.patchBook(id, book)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/author")
    public ResponseEntity<Book> patchBookAuthor(@PathVariable Long id, @RequestBody Book book) {
        return bookService.patchBookAuthor(id, book.getAuthor())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 2 DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Void> deleteBooksBatch(@RequestBody List<Long> ids) {
        bookService.deleteBooksByIds(ids);
        return ResponseEntity.noContent().build();
    }
}
