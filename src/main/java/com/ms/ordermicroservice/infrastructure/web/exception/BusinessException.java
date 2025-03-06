package com.ms.ordermicroservice.infrastructure.web.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}

