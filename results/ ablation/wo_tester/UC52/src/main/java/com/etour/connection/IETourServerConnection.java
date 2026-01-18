package com.etour.connection;

import com.etour.connection.ConnectionListener;

/**
 * Interface for ETour server connection.
 */
public interface IETourServerConnection {
    /**
     * Checks if the connection is active.
     * @return true if connected, false otherwise.
     */
    boolean isConnected();

    /**
     * Disconnects from the server.
     */
    void disconnect();

    /**
     * Adds a connection listener.
     * @param listener the listener to add.
     */
    void addConnectionListener(ConnectionListener listener);
}