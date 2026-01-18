package com.etour.connection;

/**
 * Listener for connection events.
 */
public interface ConnectionListener {
    /**
     * Called when the server disconnects.
     */
    void onDisconnect();
}