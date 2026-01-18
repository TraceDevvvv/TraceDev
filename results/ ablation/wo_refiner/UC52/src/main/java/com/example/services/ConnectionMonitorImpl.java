package com.example.serv;

/**
 * Simple connection monitor implementation.
 */
public class ConnectionMonitorImpl implements IConnectionMonitor {
    @Override
    public boolean checkConnection() {
        // Simulate checking network/database connectivity.
        return true;
    }

    @Override
    public void logConnectionIssue() {
        System.err.println("Connection issue logged.");
    }
}