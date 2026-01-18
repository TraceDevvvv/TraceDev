package com.example.adapters;

import com.example.ports.SmosServerClient;

public class SmosServerAdapter implements SmosServerClient {
    // No longer requires an external client, uses internal simulation

    public SmosServerAdapter() {
        // Empty constructor
    }

    @Override
    public boolean archiveNote(String noteId) {
        System.out.println("Archiving note with ID: " + noteId + " in SMOS server.");
        // Simulate success
        return true;
    }

    @Override
    public boolean deleteFromArchive(String noteId) {
        System.out.println("Deleting note with ID: " + noteId + " from SMOS archive.");
        // Simulate connection interruption occasionally (for testing)
        if (Math.random() > 0.8) { // 20% chance of failure
            handleConnectionInterruption();
            return false;
        }
        // Simulate success
        return true;
    }

    // Handle connection interruption
    public void handleConnectionInterruption() {
        System.out.println("Handling SMOS server connection interruption.");
        throw new RuntimeException("Connection Failed");
    }
}