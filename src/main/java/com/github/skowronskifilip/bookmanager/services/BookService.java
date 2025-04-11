package com.github.skowronskifilip.bookmanager.services;

import com.github.skowronskifilip.bookmanager.dto.BookAddEditDTO;
import com.github.skowronskifilip.bookmanager.models.database.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks(String sortBy, String sortOrder);
    List<Book> getAvailableBooks(String sortBy, String sortOrder);
    List<Book> getBooksByTitle(String title, String sortBy, String sortOrder);
    List<Book> getAvailableBooksByTitle(String title, String sortBy, String sortOrder);
    void deleteBook(int bookId);
    void addBook(BookAddEditDTO bookAddEditDTO);
    void editBook(int bookId, BookAddEditDTO bookAddEditDTO);
    Optional<Book> getBookById(int bookId);
}