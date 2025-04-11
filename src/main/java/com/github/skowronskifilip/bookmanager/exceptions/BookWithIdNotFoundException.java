package com.github.skowronskifilip.bookmanager.exceptions;

public class BookWithIdNotFoundException extends RuntimeException {
    public BookWithIdNotFoundException(String message) {
        super(message);
    }
}
