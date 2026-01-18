package com.example.adapter.in.web;

import java.util.List;
import java.util.logging.Logger;

public class ErrorHandler {
    private final Logger logger;

    public ErrorHandler(Logger logger) {
        this.logger = logger;
    }

    public void handleConnectionError(ConnectionException exception) {
        logger.severe("Connection error: " + exception.getMessage());
    }

    public void handleValidationError(List<String> errors) {
        for (String error : errors) {
            logger.warning("Validation error: " + error);
        }
    }

    public void logError(String context, Exception exception) {
        logger.severe(context + ": " + exception.getMessage());
    }
}