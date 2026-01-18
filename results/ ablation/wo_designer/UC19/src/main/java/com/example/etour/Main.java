package com.example.etour;

import com.example.etour.ui.BannerDeletionUI;

/**
 * Entry point of the application.
 * Simulates the Agency Operator logged in and starting the banner deletion use case.
 */
public class Main {
    public static void main(String[] args) {
        // Simulating entry condition: Agency Operator HAS logged.
        System.out.println("Operator logged in successfully.\n");

        // Start the banner deletion UI flow
        BannerDeletionUI ui = new BannerDeletionUI();
        ui.start();
    }
}