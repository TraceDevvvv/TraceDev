package com.school.smos;

/**
 * Represents a connection to SMOS server.
 */
public class SMOSServer {
    private String host;
    private int port;

    public SMOSServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public SMOSConnection connect() {
        return new SMOSConnection(this);
    }

    public void disconnect() {
        System.out.println("Disconnected from SMOS server.");
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}