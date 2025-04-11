package com.github.skowronskifilip.bookmanager.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.skowronskifilip.bookmanager.models.database.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAvailable(boolean available, Sort sort);
    List<Book> findByTitleContainingIgnoreCase(String title, Sort sort);
    List<Book> findByAvailableAndTitleContainingIgnoreCase(boolean available, String title, Sort sort);
}