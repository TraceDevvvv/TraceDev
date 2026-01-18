package com.etour.service;

/**
 * <<trace(R-FR-3)>> User feedback mechanisms
 * Provides notification serv for the application.
 */
public class NotificationService {
    /**
     * Shows insertion success.
     */
    public void showInsertionSuccess() {
        System.out.println("Insertion successful.");
    }

    /**
     * Shows insertion failure.
     */
    public void showInsertionFailure() {
        System.out.println("Insertion failed.");
    }

    /**
     * Shows connection error.
     * Added to satisfy requirement Exit Conditions.
     */
    public void showConnectionError() {
        System.out.println("Connection error.");
    }
}