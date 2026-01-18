package com.restaurant.dailymenu;

public class ServerConnectionException extends Exception {
    public ServerConnectionException(String message) {
        super(message);
    }
    
    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}