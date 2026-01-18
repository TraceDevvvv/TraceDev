package com.example.search;

/**
 * Custom runtime exception to indicate that an operation exceeded its time limit.
 * This directly maps to the 'TimeLimitExceededException' class in the UML diagram.
 * Added to satisfy requirement R11.
 */
public class TimeLimitExceededException extends RuntimeException {
    // Public field as specified in the UML diagram
    public String message;

    /**
     * Constructor for TimeLimitExceededException.
     * @param message A descriptive message about the time limit being exceeded.
     */
    public TimeLimitExceededException(String message) {
        super(message); // Call to super constructor of RuntimeException
        this.message = message;
    }
}