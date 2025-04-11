package com.github.skowronskifilip.bookmanager.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.skowronskifilip.bookmanager.models.database.Author;
import com.github.skowronskifilip.bookmanager.repositories.AuthorRepository;
import com.github.skowronskifilip.bookmanager.services.AuthorService;

import java.sql.Date;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public void addAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstname(firstName);
        author.setSecondname(lastName);
        author.setBorn(new Date(System.currentTimeMillis()));
        authorRepository.save(author);
    }
}
