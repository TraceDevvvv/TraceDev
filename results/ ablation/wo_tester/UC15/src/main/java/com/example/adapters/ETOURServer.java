package com.example.adapters;

import com.example.domain.Tourist;

/**
 * External system server for ETOUR.
 */
public class ETOURServer {
    private boolean connectionStatus = true; // default true for simulation

    public boolean isConnected() {
        return connectionStatus;
    }

    public void setConnectionStatus(boolean status) {
        this.connectionStatus = status;
    }

    public boolean sendData(Tourist data) {
        // Simulate sending data to external server
        return true;
    }
}