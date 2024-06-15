package com.stock.stock.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException{
    private final int errorCode;
    private final String errorClass;

    public ResourceNotFoundException(String message, int errorCode, String errorClass) {
        super(message);
        this.errorCode = errorCode;
        this.errorClass = errorClass;
    }
}
