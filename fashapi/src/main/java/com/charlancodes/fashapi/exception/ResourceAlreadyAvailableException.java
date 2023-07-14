package com.charlancodes.fashapi.exception;

public class ResourceAlreadyAvailableException extends RuntimeException{
    public ResourceAlreadyAvailableException(String message) {
        super(message);
    }
}
