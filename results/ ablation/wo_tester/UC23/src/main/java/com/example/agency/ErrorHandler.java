package com.example.agency;

/**
 * Handles errors and displays appropriate messages.
 */
public class ErrorHandler {
    public void handleError(String errorCode) {
        switch (errorCode) {
            case "INVALID_DATA":
                System.out.println("Error: Invalid data provided.");
                break;
            case "CONNECTION_LOST":
                System.out.println("Error: Connection to server lost.");
                showConnectionError();
                break;
            default:
                System.out.println("Error: Unknown error occurred.");
        }
    }

    public void logError(Exception exception) {
        System.err.println("Logged error: " + exception.getMessage());
        exception.printStackTrace();
    }

    public void showConnectionError() {
        System.out.println("Connection to server ETOUR interrupted. Please try again later.");
    }
}