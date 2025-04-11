package com.github.skowronskifilip.bookmanager.exceptions;

public class LoansNotFoundedException extends RuntimeException {
    public LoansNotFoundedException(String message) {
        super(message);
    }
}
