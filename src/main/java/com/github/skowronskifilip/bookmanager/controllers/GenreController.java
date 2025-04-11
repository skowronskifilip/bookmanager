package com.github.skowronskifilip.bookmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.github.skowronskifilip.bookmanager.models.database.Genre;
import com.github.skowronskifilip.bookmanager.services.GenreService;

import java.util.List;

@RestController
@RequestMapping("api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/")
    public ResponseEntity<List<Genre>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/add")
    public ResponseEntity<String> addGenre(@RequestParam String name, @RequestParam String description) {
        genreService.addGenre(name, description);
        return ResponseEntity.ok("Success");
    }
}
