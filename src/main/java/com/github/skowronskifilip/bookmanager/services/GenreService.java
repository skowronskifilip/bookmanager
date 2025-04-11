package com.github.skowronskifilip.bookmanager.services;

import com.github.skowronskifilip.bookmanager.models.database.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenres();
    void addGenre(String name, String description);
}
