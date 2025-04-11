package com.github.skowronskifilip.bookmanager.models.database;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 200, message = "Title must be between 1 and 200 characters")
    private String title;

    @ManyToOne
    @JoinColumn(name = "authorId", nullable = false)
    @NotNull(message = "Author cannot be null")
    private Author author;

    @Column(nullable = false)
    @Min(value = 1, message = "Genre must be a valid genre ID")
    private int genre;

    @Column(nullable = false)
    @NotBlank(message = "Language cannot be empty")
    private String language;

    @ManyToOne
    @JoinColumn(name = "publisherId", nullable = false)
    @NotNull(message = "Publisher cannot be null")
    private Publisher publisher;

    @Column(nullable = false)
    @Min(value = 1000, message = "Publication year must be at least 1000")
    @Max(value = 9999, message = "Publication year cannot exceed 9999")
    private int publication;

    @Column(nullable = false)
    @NotBlank(message = "Description cannot be empty")
    @Size(min = 10, max = 500, message = "Description must be between 10 and 500 characters")
    private String description;

    @Column(nullable = false)
    private boolean available;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getGenre() {
        return genre;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getPublication() {
        return publication;
    }

    public void setPublication(int publication) {
        this.publication = publication;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}