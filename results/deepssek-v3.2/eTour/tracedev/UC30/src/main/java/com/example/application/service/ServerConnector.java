package com.example.application.service;

/**
 * Connector for ETOUR server communication.
 */
public class ServerConnector {
    private String serverUrl;

    public ServerConnector(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public boolean isConnected() {
        // Simulate connection check
        return checkConnection();
    }

    public boolean checkConnection() {
        // Simulate connection status
        // For this example, assume connection is always OK
        return true;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}