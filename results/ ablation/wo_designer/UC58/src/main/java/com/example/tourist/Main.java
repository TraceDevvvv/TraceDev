package com.example.tourist;

/**
 * Main class to run the use case.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize components
        SiteDatabase database = new SiteDatabase();
        SiteCardService service = new SiteCardService(database);
        Tourist tourist = new Tourist("tourist1", "John Doe");
        tourist.setCurrentLocation("Research Results"); // Set location as per entry conditions

        // Simulate Tourist viewing site details
        System.out.println("=== Viewing Site Details ===");
        tourist.viewSiteDetails("1", service); // Valid site
        System.out.println();

        tourist.viewSiteDetails("2", service); // Another valid site
        System.out.println();

        tourist.viewSiteDetails("999", service); // Non-existent site
        System.out.println();

        // Simulate invalid location
        tourist.setCurrentLocation("Home");
        tourist.viewSiteDetails("1", service);
        System.out.println();

        // Simulate unauthenticated tourist
        tourist.setAuthenticated(false);
        tourist.setCurrentLocation("Research Results");
        tourist.viewSiteDetails("1", service);
    }
}