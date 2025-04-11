package com.github.skowronskifilip.bookmanager.services.implementations;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.github.skowronskifilip.bookmanager.dto.BookAddEditDTO;
import com.github.skowronskifilip.bookmanager.exceptions.BookWithIdNotFoundException;
import com.github.skowronskifilip.bookmanager.models.database.Book;
import com.github.skowronskifilip.bookmanager.repositories.AuthorRepository;
import com.github.skowronskifilip.bookmanager.repositories.BookRepository;
import com.github.skowronskifilip.bookmanager.repositories.LoanRepository;
import com.github.skowronskifilip.bookmanager.repositories.PublisherRepository;
import com.github.skowronskifilip.bookmanager.services.BookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public List<Book> getAllBooks(String sortBy, String sortOrder) {
        Sort sort = Sort.by(Sort.Order.asc(sortBy));
        if ("desc".equalsIgnoreCase(sortOrder)) {
            sort = Sort.by(Sort.Order.desc(sortBy));
        }
        return bookRepository.findAll(sort);
    }

    @Override
    public List<Book> getAvailableBooks(String sortBy, String sortOrder) {
        Sort sort = Sort.by(Sort.Order.asc(sortBy));
        if ("desc".equalsIgnoreCase(sortOrder)) {
            sort = Sort.by(Sort.Order.desc(sortBy));
        }
        return bookRepository.findByAvailable(true, sort);
    }

    @Override
    public List<Book> getBooksByTitle(String title, String sortBy, String sortOrder) {
        Sort sort = Sort.by(Sort.Order.asc(sortBy));
        if ("desc".equalsIgnoreCase(sortOrder)) {
            sort = Sort.by(Sort.Order.desc(sortBy));
        }
        return bookRepository.findByTitleContainingIgnoreCase(title, sort);
    }

    @Override
    public List<Book> getAvailableBooksByTitle(String title, String sortBy, String sortOrder) {
        Sort sort = Sort.by(Sort.Order.asc(sortBy));
        if ("desc".equalsIgnoreCase(sortOrder)) {
            sort = Sort.by(Sort.Order.desc(sortBy));
        }
        return bookRepository.findByAvailableAndTitleContainingIgnoreCase(true, title, sort);
    }

    @Override
    @Transactional
    public void deleteBook(int bookId) {
        loanRepository.deleteByBookId(bookId);
        bookRepository.deleteById(bookId);
    }

    @Override
    public void addBook(BookAddEditDTO bookAddEditDTO) {
        Book book = new Book();
        book.setTitle(bookAddEditDTO.getTitle());
        book.setAuthor(authorRepository.getReferenceById(bookAddEditDTO.getAuthorId()));
        book.setGenre(bookAddEditDTO.getGenre());
        book.setLanguage(bookAddEditDTO.getLanguage());
        book.setPublisher(publisherRepository.getReferenceById(bookAddEditDTO.getPublisherId()));
        book.setPublication(bookAddEditDTO.getPublication());
        book.setDescription(bookAddEditDTO.getDescription());
        book.setAvailable(true);
        bookRepository.save(book);
    }

    @Override
    public void editBook(int bookId, BookAddEditDTO bookAddEditDTO) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookAddEditDTO.getTitle());
            book.setAuthor(authorRepository.getReferenceById(bookAddEditDTO.getAuthorId()));
            book.setGenre(bookAddEditDTO.getGenre());
            book.setLanguage(bookAddEditDTO.getLanguage());
            book.setPublisher(publisherRepository.getReferenceById(bookAddEditDTO.getPublisherId()));
            book.setPublication(bookAddEditDTO.getPublication());
            book.setDescription(bookAddEditDTO.getDescription());
            book.setAvailable(true);
            bookRepository.save(book);
        }
        else {
            throw new BookWithIdNotFoundException("Book not found with id");
        }
    }

    @Override
    public Optional<Book> getBookById(int bookId) {
        return bookRepository.findById(bookId);
    }
}