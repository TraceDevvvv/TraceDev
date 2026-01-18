package com.example.service;

/**
 * Implementation of LoggingService.
 */
public class LoggingServiceImpl implements LoggingService {
    private boolean enabled = false;

    @Override
    public void log(String message) {
        if (enabled) {
            System.out.println("[LOG] " + message);
        }
    }

    @Override
    public void enable() {
        enabled = true;
        System.out.println("Logging enabled.");
    }
}