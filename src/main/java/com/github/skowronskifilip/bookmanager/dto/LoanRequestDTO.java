package com.github.skowronskifilip.bookmanager.dto;

public class LoanRequestDTO {

    private int bookId;

    public LoanRequestDTO(int bookId) {
        this.bookId = bookId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
