package com.etour.searchtourist;

/**
 * Main application class to demonstrate the SearchTourist use case.
 * This class simulates the interaction flow described in the use case.
 */
public class SearchTouristApp {

    public static void main(String[] args) {
        System.out.println("--- Starting ETOUR Tourist Search Application ---");

        // 1. Initialize the TouristService (backend system)
        TouristService touristService = new TouristService();

        // 2. Initialize the AgencyOperator (actor)
        AgencyOperator agencyOperator = new AgencyOperator(touristService);

        // Entry conditions: The agency has logged.
        agencyOperator.login();

        // Flow of events:
        // 1. Access the search functionality of a tourist.
        // 2. Show the form.
        // 3. Fill out the form and submit.
        agencyOperator.accessSearchFunctionality();

        // Simulate another search to demonstrate different criteria or error handling
        System.out.println("\n--- Performing another search ---");
        agencyOperator.accessSearchFunctionality();

        // Simulate logging out
        agencyOperator.logout();

        System.out.println("\n--- ETOUR Tourist Search Application Finished ---");
    }
}