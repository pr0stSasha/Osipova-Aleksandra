package com.example.MTS1.service;

import com.example.MTS1.model.Book;
import com.example.MTS1.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> saveAllBooks(List<Book> books) {
        return bookRepository.saveAll(books);
    }

    public Optional<Book> updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setAuthor(updatedBook.getAuthor());
            return bookRepository.save(existingBook);
        });
    }

    public Optional<Book> updateBookTitle(Long id, String title) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(title);
            return bookRepository.save(book);
        });
    }

    public Optional<Book> patchBook(Long id, Book partialData) {
        return bookRepository.findById(id).map(book -> {
            if (partialData.getTitle() != null) book.setTitle(partialData.getTitle());
            if (partialData.getAuthor() != null) book.setAuthor(partialData.getAuthor());
            return bookRepository.save(book);
        });
    }

    public Optional<Book> patchBookAuthor(Long id, String author) {
        return bookRepository.findById(id).map(book -> {
            book.setAuthor(author);
            return bookRepository.save(book);
        });
    }

    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deleteBooksByIds(List<Long> ids) {
        bookRepository.deleteAllById(ids);
    }
}
