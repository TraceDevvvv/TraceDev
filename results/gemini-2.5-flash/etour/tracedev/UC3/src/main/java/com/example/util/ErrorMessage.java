package com.example.util;

/**
 * Utility class to hold static error messages.
 */
public class ErrorMessage {
    public static final String ERROR_FORMAT_BEAN = "Input data format is incorrect or incomplete.";
    public static final String ERROR_DBMS = "Database operation failed. Please try again later.";
    public static final String ERROR_UNKNOWN = "An unexpected error occurred.";
    public static final String ERROR_RMI_CONNECTION = "Could not connect to the server. Please check your network connection and try again.";
    public static final String ERROR_NOT_LOGGED_IN = "User not logged in. Please log in to perform this action.";
    public static final String ERROR_DATA_LOAD = "Failed to load cultural heritage data.";
    public static final String ERROR_MODIFY_FAILED = "Failed to modify cultural heritage data.";

    // Private constructor to prevent instantiation
    private ErrorMessage() {
        // Utility class
    }
}