package com.ms.ordermicroservice.infrastructure.web.exception;

public class ValidationException extends RuntimeException{
    public ValidationException(String message) {
        super(message);
    }
}

