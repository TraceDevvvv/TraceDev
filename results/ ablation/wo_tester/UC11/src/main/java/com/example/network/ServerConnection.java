package com.example.network;

import java.util.Map;

/**
 * Simulates a connection to an external server.
 * For demonstration purposes, methods return dummy data.
 */
public class ServerConnection {
    private String serverUrl;
    private boolean connected;

    public ServerConnection(String serverUrl) {
        this.serverUrl = serverUrl;
        this.connected = false;
    }

    public boolean connect() {
        // Simulate connection success/failure randomly
        connected = Math.random() > 0.3; // 70% success rate
        return connected;
    }

    public ServerData fetchData(String endpoint, Map<String, String> params) {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected to server");
        }
        // Simulate fetching data; return dummy data
        ServerData data = new ServerData();
        data.setField("conventionId", "CONV" + System.currentTimeMillis());
        data.setDateField("conventionDate", new java.util.Date());
        data.setField("details", "Convention details for point " + params.get("pointOfRestId"));
        return data;
    }

    public void disconnect() {
        connected = false;
    }

    private boolean isConnected() {
        return connected;
    }
}