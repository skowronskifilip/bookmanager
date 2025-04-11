package com.github.skowronskifilip.bookmanager.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.skowronskifilip.bookmanager.models.database.Genre;
import com.github.skowronskifilip.bookmanager.repositories.GenreRepository;
import com.github.skowronskifilip.bookmanager.services.GenreService;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public void addGenre(String name, String description) {
        Genre genre = new Genre();
        genre.setName(name);
        genre.setDescription(description);
        genre.setBooks(null);
        genreRepository.save(genre);
    }
}
