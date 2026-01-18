package com.example.error;

import com.example.utils.Logger;
import java.time.LocalDateTime;

/**
 * Handles errors, especially connection interruptions.
 * Added to satisfy requirement REQ-011
 */
public class ErrorHandler {
    public ErrorMessage handleConnectionLoss() {
        Exception exc = new Exception("Connection interrupted");
        Logger.getInstance().logError(exc);
        return new ErrorMessage("CONN_LOST", "Network connection lost. Please try again.");
    }

    public void logError(Exception error) {
        Logger.getInstance().logError(error);
    }
}