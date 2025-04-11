package com.github.skowronskifilip.bookmanager.services;

import com.github.skowronskifilip.bookmanager.models.database.Author;

import java.util.Date;
import java.util.List;

public interface AuthorService {
    List<Author> getAllAuthors();
    void addAuthor(String firstName, String lastName);
}
