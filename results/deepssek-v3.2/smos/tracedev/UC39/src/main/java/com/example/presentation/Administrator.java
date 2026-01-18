package com.example.presentation;

import com.example.auth.AuthService;

/**
 * Represents an Administrator actor in the system.
 * In a real application, this might extend a User base class.
 * For simplicity, we treat it as a simple class with authentication state.
 */
public class Administrator {
    private String username;
    private boolean isAuthenticated;

    public Administrator(String username) {
        this.username = username;
        this.isAuthenticated = false;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    /**
     * Administrator selects a note from the list.
     * This triggers the selection logic in the UI layer.
     * @param noteId the ID of the note to select
     */
    public void selectNote(String noteId) {
        System.out.println("Administrator selects note with ID: " + noteId);
    }

    /**
     * Administrator disconnects or logs out.
     * This triggers cleanup and exit condition.
     */
    public void disconnect() {
        this.isAuthenticated = false;
        System.out.println("Administrator disconnected.");
    }
}