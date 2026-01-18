package com.etour.connection;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of IETourServerConnection.
 */
public class ETourServerConnection implements IETourServerConnection {
    private boolean connected = true; // default to connected for demo
    private List<ConnectionListener> listeners = new ArrayList<>();

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void disconnect() {
        connected = false;
        notifyDisconnect();
    }

    @Override
    public void addConnectionListener(ConnectionListener listener) {
        listeners.add(listener);
    }

    /**
     * Notifies all listeners about disconnection.
     */
    private void notifyDisconnect() {
        for (ConnectionListener listener : listeners) {
            listener.onDisconnect();
        }
    }

    /**
     * For simulation: set connection status.
     * @param connected true to set connected, false to disconnect.
     */
    public void setConnected(boolean connected) {
        this.connected = connected;
        if (!connected) {
            notifyDisconnect();
        }
    }
}