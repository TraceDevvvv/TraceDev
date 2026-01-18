package com.example.reportcard.service;

import com.example.reportcard.exception.NetworkException;

/**
 * Mock implementation of INetworkConnectivityService.
 * Allows simulating network failures for testing.
 */
public class NetworkConnectivityServiceImpl implements INetworkConnectivityService {
    private boolean isOnline = true; // Default to online

    @Override
    public void checkConnection() throws NetworkException {
        if (!isOnline) {
            throw new NetworkException("Network connection interrupted.");
        }
        // In a real scenario, this would perform actual network checks (e.g., ping a server)
        System.out.println("[NetworkConnectivityServiceImpl] Network is online.");
    }

    /**
     * For testing purposes: set the network state.
     * @param online true to set online, false to set offline.
     */
    public void setOnline(boolean online) {
        this.isOnline = online;
        System.out.println("[NetworkConnectivityServiceImpl] Network state set to: " + (online ? "online" : "offline"));
    }
}