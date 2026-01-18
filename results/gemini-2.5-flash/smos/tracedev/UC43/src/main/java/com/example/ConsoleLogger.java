package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Concrete implementation of the Logger interface that outputs messages to the console.
 */
public class ConsoleLogger implements Logger {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private String formatMessage(String level, String message) {
        return String.format("[%s] %s - %s", LocalDateTime.now().format(FORMATTER), level, message);
    }

    @Override
    public void info(String message) {
        System.out.println(formatMessage("INFO", message));
    }

    @Override
    public void warn(String message) {
        System.out.println(formatMessage("WARN", message));
    }

    @Override
    public void error(String message) {
        System.err.println(formatMessage("ERROR", message));
    }
}