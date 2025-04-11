package com.github.skowronskifilip.bookmanager.models.database;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true, length = 64)
    @NotBlank(message = "Username cannot be empty")
    @Size(min = 3, max = 64, message = "Username must be between 3 and 64 characters")
    private String username;

    @Column(nullable = false, length = 128)
    @NotBlank(message = "Password cannot be empty")
    @Size(min = 6, max = 128, message = "Password must be between 6 and 128 characters")
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    @NotNull(message = "Creation timestamp cannot be null")
    private Timestamp created;

    @Column(nullable = false)
    private boolean admin;

    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<Loan> loans;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}