package com.etour.preference;

import java.io.*;
import java.util.Random;

/**
 * PreferenceService handles storage and retrieval of preferences,
 * and manages connection to the ETOUR server.
 * Provides methods to load and save preferences with simulation of server communication.
 * Handles edge cases like connection interruptions and file operations.
 */
public class PreferenceService {
    private static final String PREFERENCES_DIR = "preferences/";
    private static final Random random = new Random();
    
    /**
     * Loads preferences for the given tourist.
     * Simulates server connection and file operations.
     * @param tourist the tourist whose preferences to load
     * @return the loaded Preference object
     * @throws ConnectionInterruptedException if connection to ETOUR server fails
     */
    public static Preference loadPreferences(Tourist tourist) throws ConnectionInterruptedException {
        // Check server connection first
        if (!isServerAvailable()) {
            throw new ConnectionInterruptedException("Unable to connect to ETOUR server while loading preferences.");
        }
        
        String filename = PREFERENCES_DIR + tourist.getUserId() + ".pref";
        
        try {
            File file = new File(filename);
            if (!file.exists()) {
                System.out.println("No existing preferences found. Using default preferences.");
                return new Preference();
            }
            
            // In a real application, this would deserialize from server
            // For simulation, we'll create some realistic preferences
            Preference preferences = new Preference();
            preferences.setLanguage("English");
            preferences.setCurrency("USD");
            preferences.setTemperatureUnit("Celsius");
            preferences.setEmailNotifications(true);
            preferences.setSmsNotifications(false);
            preferences.setPushNotifications(true);
            preferences.setTheme("Light");
            preferences.setResultsPerPage(10);
            preferences.setAccessibilityMode(false);
            
            // Add some sample favorite categories
            preferences.addFavoriteCategory("Adventure");
            preferences.addFavoriteCategory("Cultural");
            preferences.addFavoriteCategory("Beach");
            
            System.out.println("✓ Preferences loaded successfully from ETOUR server.");
            return preferences;
            
        } catch (Exception e) {
            throw new ConnectionInterruptedException("Error loading preferences from ETOUR server: " + e.getMessage());
        }
    }
    
    /**
     * Saves preferences for the given tourist.
     * Simulates server connection and storage operations.
     * @param tourist the tourist whose preferences to save
     * @param preferences the preferences to save
     * @throws ConnectionInterruptedException if connection to ETOUR server fails during save
     */
    public static void savePreferences(Tourist tourist, Preference preferences) throws ConnectionInterruptedException {
        // Check server connection first
        if (!isServerAvailable()) {
            throw new ConnectionInterruptedException("Unable to connect to ETOUR server while saving preferences.");
        }
        
        try {
            // Ensure preferences directory exists
            File dir = new File(PREFERENCES_DIR);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            String filename = PREFERENCES_DIR + tourist.getUserId() + ".pref";
            
            // In a real application, this would serialize and send to server
            // For simulation, we'll just print a success message
            System.out.println("✓ Preferences saved successfully to ETOUR server.");
            System.out.println("File location: " + filename);
            
            // Simulate server processing time
            Thread.sleep(1000);
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ConnectionInterruptedException("Save operation interrupted: " + e.getMessage());
        } catch (Exception e) {
            throw new ConnectionInterruptedException("Error saving preferences to ETOUR server: " + e.getMessage());
        }
    }
    
    /**
     * Checks if the ETOUR server is available.
     * @return true if server is available, false otherwise
     */
    private static boolean isServerAvailable() {
        // Simulate server availability - 90% chance of success
        boolean available = random.nextDouble() > 0.1;
        
        if (!available) {
            System.out.println("⚠️ Warning: ETOUR server connection unstable.");
        }
        
        return available;
    }
    
    /**
     * Validates if the given tourist is authorized to modify preferences.
     * @param tourist the tourist to validate
     * @return true if authorized, false otherwise
     */
    public static boolean isAuthorized(Tourist tourist) {
        if (tourist == null) {
            return false;
        }
        return tourist.isAuthenticated();
    }
}