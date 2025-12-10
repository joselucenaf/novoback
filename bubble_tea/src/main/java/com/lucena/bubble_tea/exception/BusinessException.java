package com.lucena.bubble_tea.exception;
            //exceçoes - regras de negócios
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}