package com.example;

import com.example.model.Tourist;
import com.example.model.Site;
import java.util.List;

/**
 * Main class to run the application and demonstrate the sequence.
 */
public class Main {
    public static void main(String[] args) {
        // Create a tourist
        Tourist tourist = new Tourist(1, "John Doe");
        
        // Authenticate (optional, as per class diagram)
        boolean authenticated = tourist.authenticate();
        System.out.println("Tourist authenticated: " + authenticated);
        
        // Request visited sites (triggers the sequence diagram flow)
        List<Site> visitedSites = tourist.requestVisitedSites();
        
        // Print result
        System.out.println("Tourist visited sites: " + visitedSites);
    }
}