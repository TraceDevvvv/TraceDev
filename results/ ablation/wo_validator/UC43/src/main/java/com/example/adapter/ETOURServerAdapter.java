package com.example.adapter;

import com.example.dto.RefreshmentDTO;

/**
 * Adapter to communicate with ETOUR server.
 */
public class ETOURServerAdapter {
    private String serverEndpoint;

    public ETOURServerAdapter() {
        // Assumption: default endpoint
        this.serverEndpoint = "http://etour-server.example.com/api";
    }

    public ETOURServerAdapter(String serverEndpoint) {
        this.serverEndpoint = serverEndpoint;
    }

    /**
     * Checks connection to the server.
     * Mock implementation: returns true for simulation.
     */
    public boolean checkConnection() {
        // In real scenario, would make a network request to serverEndpoint
        System.out.println("Checking connection to ETOUR server at: " + serverEndpoint);
        // Simulate connection check
        return true; // Assume connection is available
    }

    /**
     * Sends update confirmation to the server.
     * Returns true if confirmation received, false otherwise.
     */
    public boolean sendUpdateConfirmation(RefreshmentDTO dto) {
        // Mock implementation: simulate server response
        System.out.println("Sending update confirmation to ETOUR server for refreshment: " + dto.getName());
        // Assume server responds with confirmation
        return true;
    }
}