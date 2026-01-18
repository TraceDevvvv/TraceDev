package com.example.strategy;

/**
 * Validates comment length.
 */
public class CommentLengthStrategy implements ValidationStrategy {
    public static final int MAX_LENGTH = 500;

    @Override
    public boolean isValid(String data) {
        return data != null && data.length() <= MAX_LENGTH;
    }

    @Override
    public String getErrorMessage() {
        return "Comment must not exceed " + MAX_LENGTH + " characters.";
    }
}