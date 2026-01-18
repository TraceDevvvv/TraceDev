package com.example.etour.service;

import com.example.etour.model.Site;
import com.example.etour.model.Tourist;

/**
 * Service simulating the ETOUR server connection and bookmark operations.
 */
public class EtourService {
    private boolean connected = false;

    public void connect() {
        connected = true;
        System.out.println("Connected to ETOUR server.");
    }

    public void disconnect() {
        connected = false;
        System.out.println("Disconnected from ETOUR server.");
    }

    public boolean isConnected() {
        return connected;
    }

    /**
     * Simulates sending a notification about bookmark insertion.
     */
    public void notifyBookmarkAdded(Tourist tourist, Site site) {
        if (connected) {
            System.out.println("Notification sent to tourist " + tourist + " for site " + site + " added to favorites.");
        } else {
            System.out.println("Cannot notify: not connected.");
        }
    }
}