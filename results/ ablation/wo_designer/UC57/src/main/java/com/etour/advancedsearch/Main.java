package com.etour.advancedsearch;

/**
 * Main class to run the advanced search use case.
 * Simulates a tourist who has already authenticated.
 */
public class Main {
    public static void main(String[] args) {
        // Entry Condition: Tourist HAS successfully authenticated to the system.
        Tourist authenticatedTourist = new Tourist("john_doe", "john@example.com");
        System.out.println("Tourist authenticated: " + authenticatedTourist.getUsername());

        PersonalArea personalArea = new PersonalArea(authenticatedTourist);
        try {
            personalArea.startAdvancedSearch();
        } finally {
            personalArea.close();
        }

        System.out.println("\n=== Use Case Completed ===");
    }
}