package com.example.justification.controller;

/**
 * A simple class to represent the result of a controller action.
 * Useful for indicating success or failure and providing a message.
 * This simulates a return type common in web frameworks.
 */
public class ActionResult {
    private final boolean success;
    private final String message;

    /**
     * Constructor for ActionResult.
     *
     * @param success True if the action was successful, false otherwise.
     * @param message A descriptive message about the action's outcome.
     */
    public ActionResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Static factory method for a successful result.
     * @param message The success message.
     * @return An ActionResult indicating success.
     */
    public static ActionResult success(String message) {
        return new ActionResult(true, message);
    }

    /**
     * Static factory method for a failed result.
     * @param message The error message.
     * @return An ActionResult indicating failure.
     */
    public static ActionResult failure(String message) {
        return new ActionResult(false, message);
    }

    @Override
    public String toString() {
        return "ActionResult{" +
               "success=" + success +
               ", message='" + message + '\'' +
               '}';
    }
}