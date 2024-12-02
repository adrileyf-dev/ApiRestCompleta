package com.restdev.apirestcompleta.exception;

public class NewPasswordMismatchException extends RuntimeException {
    public NewPasswordMismatchException(String msg) {
        super(msg);
    }
}
