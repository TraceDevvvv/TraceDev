package com.example;

/**
 * Adapter for communicating with the ETOUR server.
 * Simulates notification of banner change events.
 */
public class ETOURServerAdapter {
    public boolean notifyBannerChanged(int bannerId) {
        // Simulate server communication.
        // Assumption: Returns true if notification was successful, false otherwise.
        System.out.println("Notifying ETOUR server about change for banner ID: " + bannerId);
        // For simplicity, we assume it always succeeds unless a simulated interruption occurs.
        // In a real scenario, this would involve network calls.
        return true;
    }
}