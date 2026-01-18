package com.example.application;

/**
 * Concrete error handler that logs errors to the console.
 */
public class ErrorDatiUseCase implements ErrorHandlerUseCase {
    @Override
    public void execute(String errorMessage) {
        System.err.println("Error handled: " + errorMessage);
    }
}