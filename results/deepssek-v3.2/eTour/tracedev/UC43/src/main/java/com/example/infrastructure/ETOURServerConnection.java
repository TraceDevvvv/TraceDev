package com.example.infrastructure;

/**
 * Interface for server connection, responsible for checking connectivity and sending data.
 */
public interface ETOURServerConnection {
    boolean isConnected();
    boolean checkConnection();
    boolean sendData(Object data);
}