package com.github.skowronskifilip.bookmanager.dto;

public class UserSmallDetailsDTO {

    private String username;
    private String dateCreated;
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