package com.example.serv;

/**
 * Monitor for connection quality (REQ-012, REQ-013).
 */
public interface IConnectionMonitor {
    boolean checkConnection();
    void logConnectionIssue();
}