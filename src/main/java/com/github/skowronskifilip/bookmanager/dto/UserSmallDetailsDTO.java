package com.github.skowronskifilip.bookmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserSmallDetailsDTO {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 64, message = "Username must be between 3 and 64 characters")
    private String username;

    @NotBlank(message = "Date created cannot be empty")
    private String dateCreated;

    @NotBlank(message = "Mastermind cannot be empty")
    private boolean mastermind;

    public UserSmallDetailsDTO(String username, String dateCreated, boolean mastermind) {
        setUsername(username);
        setDateCreated(dateCreated);
        setMastermind(mastermind);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isMastermind() {
        return mastermind;
    }

    public void setMastermind(boolean mastermind) {
        this.mastermind = mastermind;
    }
}