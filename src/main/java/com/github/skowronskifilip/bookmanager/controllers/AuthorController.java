package com.github.skowronskifilip.bookmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import com.github.skowronskifilip.bookmanager.models.database.Author;
import com.github.skowronskifilip.bookmanager.services.AuthorService;

import java.util.List;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/")
    public ResponseEntity<List<Author>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/add")
    public ResponseEntity<String> addAuthor(@RequestParam String firstName, @RequestParam String lastName) {
        authorService.addAuthor(firstName, lastName);
        return ResponseEntity.ok("Success");
    }

}
