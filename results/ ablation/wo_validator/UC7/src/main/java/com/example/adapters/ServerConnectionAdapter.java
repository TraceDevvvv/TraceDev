package com.example.adapters;

import com.example.ports.ServerConnectionPort;

/**
 * Adapter implementing ServerConnectionPort using a session manager.
 */
public class ServerConnectionAdapter implements ServerConnectionPort {

    private final SessionManager etourServerSessionManager;

    public ServerConnectionAdapter(SessionManager etourServerSessionManager) {
        this.etourServerSessionManager = etourServerSessionManager;
    }

    @Override
    public void interruptConnection() {
        etourServerSessionManager.disconnect();
    }
}

// Assumed external session manager class
class SessionManager {
    public void disconnect() {
        System.out.println("Session disconnected from ETOUR server.");
    }
}