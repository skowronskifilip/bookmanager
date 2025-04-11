package com.github.skowronskifilip.bookmanager.dto;

import jakarta.validation.constraints.*;

public class BookAddEditDTO {

    @NotBlank(message = "Title cannot be empty")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    private String title;

    @NotNull(message = "Author ID cannot be null")
    @Min(value = 1, message = "Author ID must be greater than 0")
    private int authorId;

    @NotNull(message = "Genre cannot be null")
    @Min(value = 1, message = "Genre ID must be greater than 0")
    private int genre;

    @NotBlank(message = "Language cannot be empty")
    @Size(min = 2, max = 64, message = "Language must be between 2 and 64 characters")
    private String language;

    @NotNull(message = "Publisher ID cannot be null")
    @Min(value = 1, message = "Publisher ID must be greater than 0")
    private int publisherId;

    @NotNull(message = "Publication year cannot be null")
    @Min(value = 1900, message = "Publication year must be after 1900")
    @Max(value = 2100, message = "Publication year must be before 2100")
    private int publication;

    @NotBlank(message = "Description cannot be empty")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    public BookAddEditDTO(String title, int authorId, int genre, String language, int publisherId, int publication, String description) {
        setTitle(title);
        setAuthor(authorId);
        setGenre(genre);
        setLanguage(language);
        setPublisherId(publisherId);
        setPublication(publication);
        setDescription(description);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthor(int authorId) {
        this.authorId = authorId;
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

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
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

}

