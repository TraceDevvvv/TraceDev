package com.example.system;

/**
 * Handles error notifications and logging.
 */
public class ErrorHandler {
    private String errorMessage;

    /**
     * Notifies user about an error.
     * @param message the error message
     */
    public void notifyUser(String message) {
        this.errorMessage = message;
        System.out.println("ErrorHandler: Notifying user - " + message);
        String clearMessage = createClearErrorMessage(message);
        ErrorMessageWindow window = new ErrorMessageWindow();
        window.display(clearMessage);
    }

    /**
     * Logs an exception.
     * @param exception the exception to log
     */
    public void logError(Exception exception) {
        System.err.println("ErrorHandler: Logged error - " + exception.getMessage());
    }

    /**
     * Creates a clear error message for display.
     * @param originalMessage the original error message
     * @return the formatted clear message
     */
    public String createClearErrorMessage(String originalMessage) {
        return "ERROR: " + originalMessage + ". Please try a different tag.";
    }
}