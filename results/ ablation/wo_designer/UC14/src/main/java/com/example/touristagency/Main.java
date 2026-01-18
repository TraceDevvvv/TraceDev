/**
 * Entry point of the application.
 * Simulates the Agency Operator logging in and accessing the search functionality.
 */
package com.example.touristagency;

public class Main {
    public static void main(String[] args) {
        // Simulate entry condition: Agency Operator HAS logged in.
        System.out.println("Agency Operator logged in successfully.\n");

        // Initialize serv
        TouristService touristService = new TouristService();
        TouristSearchForm searchForm = new TouristSearchForm(touristService);

        // Agency Operator accesses the search functionality
        searchForm.showForm();

        // In a real application, the UI would loop or remain open.
        // For simplicity, we exit after one search.
        System.out.println("\nSearch completed. Exiting application.");
    }
}