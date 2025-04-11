package com.github.skowronskifilip.bookmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.skowronskifilip.bookmanager.models.database.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}