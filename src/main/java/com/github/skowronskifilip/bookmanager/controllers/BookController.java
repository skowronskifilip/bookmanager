package com.github.skowronskifilip.bookmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.github.skowronskifilip.bookmanager.dto.BookAddEditDTO;
import com.github.skowronskifilip.bookmanager.models.database.Book;
import com.github.skowronskifilip.bookmanager.services.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/add")
    public ResponseEntity<String> addBook(@RequestBody BookAddEditDTO bookAddEditDTO) {
        bookService.addBook(bookAddEditDTO);
        return ResponseEntity.ok("Success");
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/remove/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable int bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.ok("Success");
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/edit/{bookId}")
    public ResponseEntity<String> editBook(@PathVariable int bookId, @RequestBody BookAddEditDTO bookAddEditDTO) {
        bookService.editBook(bookId, bookAddEditDTO);
        return ResponseEntity.ok("Success");
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookInfo(@PathVariable int bookId) {
        Optional<Book> book = bookService.getBookById(bookId);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/")
    public ResponseEntity<List<Book>> getAllBooks(
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        List<Book> books = bookService.getAllBooks(sortBy, sortOrder);
        return ResponseEntity.ok(books);
    }

    @Secured("ROLE_USER")
    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks(
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        List<Book> books = bookService.getAvailableBooks(sortBy, sortOrder);
        return ResponseEntity.ok(books);
    }

    @Secured("ROLE_USER")
    @GetMapping("/available/search")
    public ResponseEntity<List<Book>> getAvailableBooksByTitle(
            @RequestParam("title") String title,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        List<Book> books = bookService.getAvailableBooksByTitle(title, sortBy, sortOrder);
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(books);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/search")
    public ResponseEntity<List<Book>> getBooksByTitle(
            @RequestParam("title") String title,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String sortOrder) {

        List<Book> books = bookService.getBooksByTitle(title, sortBy, sortOrder);
        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(books);
    }
}