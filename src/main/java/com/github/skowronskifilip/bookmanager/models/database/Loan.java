package com.github.skowronskifilip.bookmanager.models.database;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @NotNull(message = "User cannot be null")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bookId", nullable = false)
    @NotNull(message = "Book cannot be null")
    private Book book;

    @Column(nullable = true)
    @NotNull(message = "Loan date cannot be null")
    private Timestamp loan;

    @Column(nullable = true)
    @NotNull(message = "Return date cannot be null")
    private Timestamp returnDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Timestamp getLoan() {
        return loan;
    }

    public void setLoan(Timestamp loan) {
        this.loan = loan;
    }

    public Timestamp getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Timestamp returnDate) {
        this.returnDate = returnDate;
    }
}