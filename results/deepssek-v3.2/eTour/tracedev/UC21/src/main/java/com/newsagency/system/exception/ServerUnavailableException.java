package com.newsagency.system.exception;

/**
 * Exception indicating that the server is unavailable.
 */
public class ServerUnavailableException extends Exception {
    public ServerUnavailableException() {
        super();
    }

    public ServerUnavailableException(String message) {
        super(message);
    }

    public ServerUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}