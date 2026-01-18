package com.example.client;

import com.example.web.BannerDTO;
import com.example.exceptions.ConnectionException;

/**
 * Client for communication with ETOUR system.
 * Added to satisfy requirement REQ-15.
 */
public class ETOURClient {
    private String serverUrl;

    public ETOURClient(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    /**
     * Sends banner data to external system.
     * In a real implementation, this would make a network call.
     */
    public boolean sendBannerData(BannerDTO bannerDTO) {
        // Simulate connection check
        if (!isConnected()) {
            throw new ConnectionException("No connection to ETOUR server");
        }
        // Simulate sending data
        System.out.println("Sending banner data to " + serverUrl);
        return true;
    }

    public boolean isConnected() {
        // For simplicity, always return true. In reality, would check network.
        return true;
    }
}