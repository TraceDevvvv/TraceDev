package com.example.localization;

/**
 * Represents a tourist who initiates a search for their location.
 * In this simplified model, the Tourist primarily serves as the actor
 * who triggers the localization process.
 */
public class Tourist {
    private final String name;

    /**
     * Constructs a new Tourist with a given name.
     *
     * @param name The name of the tourist.
     */
    public Tourist(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the tourist.
     *
     * @return The tourist's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Simulates the tourist initiating a search for their current position.
     * This method would typically interact with the LocalizationSystem.
     */
    public void startSearch() {
        System.out.println(name + " has started a search for their current position.");
        // In a real application, this would trigger the LocalizationSystem.
    }

    /**
     * Simulates the tourist initiating an advanced search for their current position.
     * This method would typically interact with the LocalizationSystem.
     */
    public void startAdvancedSearch() {
        System.out.println(name + " has started an advanced search for their current position.");
        // In a real application, this would trigger the LocalizationSystem.
    }
}