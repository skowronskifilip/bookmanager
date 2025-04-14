package com.github.skowronskifilip.bookmanager.dto;

public class UserLoginPasswordDTO {

    private String username;
    private String password;

    public UserLoginPasswordDTO(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}