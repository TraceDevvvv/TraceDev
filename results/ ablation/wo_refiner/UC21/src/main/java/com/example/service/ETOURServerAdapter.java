package com.example.service;

import com.example.entity.NewsEntity;

/**
 * Adapter for communicating with the external ETOUR server.
 * Stereotype <<reliable>> - must maintain 99.9% availability and max response time < 2s.
 */
public class ETOURServerAdapter {

    /**
     * Checks if the adapter is connected to the ETOUR server.
     */
    public boolean isConnected() {
        // Simulate connection check.
        // For this example, we assume it's always connected.
        return true;
    }

    /**
     * Sends the news entity to the ETOUR server.
     */
    public boolean sendNews(NewsEntity news) {
        // Simulate sending to external server.
        System.out.println("Sending news to ETOUR server: " + news.getTitle());
        // Assume success.
        return true;
    }

    /**
     * Handles disconnection from the ETOUR server.
     * @param reason Reason for disconnection.
     * @return true if handled successfully.
     */
    public boolean handleDisconnection(String reason) {
        System.out.println("ETOUR server disconnected: " + reason);
        // In a real system, might attempt reconnection or queue requests.
        return true;
    }
}