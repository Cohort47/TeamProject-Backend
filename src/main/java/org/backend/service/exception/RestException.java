package org.backend.service.exception;

public class RestException extends RuntimeException {
    public RestException(String message) {
        super(message);
    }
}
