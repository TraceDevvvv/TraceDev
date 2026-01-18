package com.example.serv;

/**
 * Custom exception to represent a server connection interruption.
 */
public class ServerConnectionException extends Exception {
    public ServerConnectionException(String message) {
        super(message);
    }
}