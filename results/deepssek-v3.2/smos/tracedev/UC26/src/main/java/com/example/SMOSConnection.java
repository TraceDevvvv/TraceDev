package com.example;

/**
 * Interface for SMOS server connection.
 */
public interface SMOSConnection {
    boolean isConnected();
    void checkConnection();
}