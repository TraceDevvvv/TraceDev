// ETOURServer.java
package com.etour.insertpreferencesite;

/**
 * Simulates the ETOUR server connection status.
 * This class is used to model the "Interruption of connection to the server ETOUR" quality requirement.
 */
public class ETOURServer {
    private boolean connected;

    /**
     * Constructs a new ETOURServer instance.
     * By default, the server is connected.
     */
    public ETOURServer() {
        this.connected = true; // Server is connected by default
    }

    /**
     * Checks if the ETOUR server is currently connected.
     *
     * @return true if the server is connected, false otherwise.
     */
    public boolean isServerConnected() {
        return connected;
    }

    /**
     * Sets the connection status of the ETOUR server.
     *
     * @param connected true to set the server as connected, false to set it as disconnected.
     */
    public void setServerConnected(boolean connected) {
        this.connected = connected;
        if (connected) {
            System.out.println("ETOUR Server: Connection established.");
        } else {
            System.out.println("ETOUR Server: Connection interrupted.");
        }
    }
}