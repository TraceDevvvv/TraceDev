package com.example.serv;

import com.example.exceptions.ConnectionException;

/**
 * Gateway for ETour server communication.
 */
public class ETourServerGateway {
    public boolean validateConnection() {
        // Simulate connection validation
        if (Math.random() < 0.1) { // 10% chance for simulation
            throw new ConnectionException("Connection to ETour server failed");
        }
        return true; // Connection is valid
    }
}