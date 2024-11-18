package org.backend.service.exception;

public class TourNotFoundException extends RuntimeException {
    public TourNotFoundException(String message) {
        super(message);
    }
}
