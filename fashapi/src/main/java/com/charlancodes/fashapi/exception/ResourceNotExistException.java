package com.charlancodes.fashapi.exception;

public class ResourceNotExistException extends RuntimeException{
    public ResourceNotExistException(String message) {
        super(message);
    }
}
