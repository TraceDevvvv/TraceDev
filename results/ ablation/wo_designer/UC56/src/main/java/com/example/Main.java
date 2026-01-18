package com.example;

import com.example.gps.GPSException;
import com.example.gps.GPSSystem;
import com.example.gps.GPSPosition;
import com.example.system.SystemInitialization;
import com.example.tourist.Tourist;
import com.example.tourist.TouristSearch;

/**
 * Main class to demonstrate the use case.
 */
public class Main {
    public static void main(String[] args) {
        // Create a tourist
        Tourist tourist = new Tourist("Alice");
        
        // Simulate the entry condition: Tourist HAS begun a search (basic or advanced)
        TouristSearch searchType = Math.random() > 0.5 ? TouristSearch.BASIC_SEARCH : TouristSearch.ADVANCED_SEARCH;
        tourist.beginSearch(searchType);
        
        // Create GPS system and system initialization
        GPSSystem gps = new GPSSystem();
        SystemInitialization sysInit = new SystemInitialization(gps);
        
        System.out.println("\n--- Method 1: Blocking call ---");
        GPSPosition pos1 = sysInit.getTouristPosition(tourist, searchType);
        if (pos1 != null) {
            System.out.println("Tourist position: " + pos1);
        } else {
            System.out.println("Could not determine tourist position.");
        }
        
        System.out.println("\n--- Method 2: With timeout (5 seconds) ---");
        GPSPosition pos2 = sysInit.getTouristPositionWithTimeout(tourist, searchType);
        if (pos2 != null) {
            System.out.println("Tourist position: " + pos2);
        } else {
            System.out.println("Could not determine tourist position.");
        }
    }
}