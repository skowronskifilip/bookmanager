package com.github.skowronskifilip.bookmanager.models.database;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 64)
    @NotBlank(message = "Genre name cannot be empty")
    @Size(min = 1, max = 64, message = "Genre name must be between 1 and 64 characters")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Description cannot be empty")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @OneToMany(mappedBy = "genre")
    private List<Book> books;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
