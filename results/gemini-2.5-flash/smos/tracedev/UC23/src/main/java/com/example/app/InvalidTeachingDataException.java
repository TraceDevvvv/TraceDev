package com.example.app;

import java.util.Collections;
import java.util.List;

/**
 * InvalidTeachingDataException is a custom runtime exception thrown when
 * the provided teaching data fails validation checks.
 * It carries a list of specific error messages.
 */
public class InvalidTeachingDataException extends RuntimeException {
    private final List<String> errors; // List of specific validation errors

    /**
     * Constructs a new InvalidTeachingDataException with a list of errors.
     *
     * @param errors A list of strings, each describing a validation error.
     */
    public InvalidTeachingDataException(List<String> errors) {
        // Concatenate errors into a single message for the base exception.
        super("Invalid teaching data: " + String.join(", ", errors));
        this.errors = Collections.unmodifiableList(errors); // Make the list immutable
    }

    /**
     * Returns the list of validation errors.
     *
     * @return An unmodifiable list of error messages.
     */
    public List<String> getErrors() {
        return errors;
    }
}