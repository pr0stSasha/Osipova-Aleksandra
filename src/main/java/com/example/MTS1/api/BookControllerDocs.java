package com.example.MTS1.api;

import com.example.MTS1.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Book Controller", description = "Operations related to Books")
public interface BookControllerDocs {

    @Operation(summary = "Get all books")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Books retrieved successfully")
    })
    List<Book> getAllBooks();

    @Operation(summary = "Get book by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    ResponseEntity<Book> getBookById(
            @Parameter(description = "ID of the book", required = true)
            @PathVariable Long id);

    @Operation(summary = "Create a new book")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Book created successfully")
    })
    Book createBook(
            @Parameter(description = "Book to create", required = true)
            @RequestBody Book book);

    @Operation(summary = "Create multiple books")
    List<Book> createBooksBatch(
            @Parameter(description = "Books to create", required = true)
            @RequestBody List<Book> books);

    @Operation(summary = "Update an existing book")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    ResponseEntity<Book> updateBook(
            @Parameter(description = "ID of the book", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated book data", required = true)
            @RequestBody Book book);

    @Operation(summary = "Update book title")
    ResponseEntity<Book> updateBookTitle(
            @Parameter(description = "ID of the book", required = true)
            @PathVariable Long id,
            @Parameter(description = "New title", required = true)
            @RequestBody Book book);

    @Operation(summary = "Patch a book")
    ResponseEntity<Book> patchBook(
            @Parameter(description = "ID of the book", required = true)
            @PathVariable Long id,
            @Parameter(description = "Partial data", required = true)
            @RequestBody Book book);

    @Operation(summary = "Patch book author")
    ResponseEntity<Book> patchBookAuthor(
            @Parameter(description = "ID of the book", required = true)
            @PathVariable Long id,
            @Parameter(description = "New author", required = true)
            @RequestBody Book book);

    @Operation(summary = "Delete book by ID")
    ResponseEntity<Void> deleteBook(
            @Parameter(description = "ID of the book", required = true)
            @PathVariable Long id);

    @Operation(summary = "Delete multiple books")
    ResponseEntity<Void> deleteBooksBatch(
            @Parameter(description = "List of book IDs", required = true)
            @RequestBody List<Long> ids);
}
