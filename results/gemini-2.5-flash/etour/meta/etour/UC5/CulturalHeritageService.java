// CulturalHeritageService.java
package com.example.culturalheritage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Service class responsible for handling operations related to CulturalHeritage items.
 * This includes simulating data loading and handling potential connection interruptions.
 */
public class CulturalHeritageService {

    // A simulated database or data source for cultural heritage items.
    // In a real application, this would interact with a database or external API.
    private Map<String, CulturalHeritage> culturalHeritageDatabase;

    // Simulates the connection status to the ETOUR server.
    private boolean etourServerConnected;

    /**
     * Constructs a new CulturalHeritageService.
     * Initializes the simulated database with some sample data and sets the server connection status.
     */
    public CulturalHeritageService() {
        this.culturalHeritageDatabase = new HashMap<>();
        // Populate with some sample cultural heritage data
        culturalHeritageDatabase.put("CH001", new CulturalHeritage("CH001", "Colosseum", "An ancient amphitheatre in the center of Rome, Italy.", "Rome, Italy"));
        culturalHeritageDatabase.put("CH002", new CulturalHeritage("CH002", "Eiffel Tower", "A wrought-iron lattice tower on the Champ de Mars in Paris, France.", "Paris, France"));
        culturalHeritageDatabase.put("CH003", new CulturalHeritage("CH003", "Great Wall of China", "A series of fortifications made of stone, brick, tamped earth, wood, and other materials.", "Northern China"));
        
        // Assume ETOUR server is connected by default
        this.etourServerConnected = true; 
    }

    /**
     * Simulates the connection status to the ETOUR server.
     * This method can be used to simulate connection interruptions.
     * @param connected true to simulate a connected server, false for a disconnected server.
     */
    public void setEtourServerConnected(boolean connected) {
        this.etourServerConnected = connected;
        if (!connected) {
            System.err.println("WARNING: Connection to ETOUR server interrupted.");
        } else {
            System.out.println("ETOUR server connection restored.");
        }
    }

    /**
     * Loads the details of a selected cultural heritage item.
     * This method checks for operator login status and server connection before attempting to load data.
     *
     * @param operator The AgencyOperator attempting to view the cultural heritage card.
     * @param culturalHeritageId The ID of the cultural heritage item to view.
     * @return An Optional containing the CulturalHeritage object if found and conditions are met,
     *         otherwise an empty Optional.
     */
    public Optional<CulturalHeritage> viewCulturalHeritageCard(AgencyOperator operator, String culturalHeritageId) {
        // Entry condition: The agency has logged in.
        if (!operator.isLoggedIn()) {
            System.err.println("Error: Operator " + operator.getUsername() + " is not logged in. Cannot view cultural heritage card.");
            return Optional.empty();
        }

        // Quality requirement: Interruption of the connection to the server ETOUR.
        if (!etourServerConnected) {
            System.err.println("Error: Cannot view cultural heritage card. Connection to ETOUR server is interrupted.");
            return Optional.empty();
        }

        // Simulate loading data for the selected cultural heritage.
        // In a real scenario, this would involve a database query or an API call.
        CulturalHeritage selectedHeritage = culturalHeritageDatabase.get(culturalHeritageId);

        if (selectedHeritage == null) {
            System.err.println("Error: Cultural Heritage with ID '" + culturalHeritageId + "' not found.");
            return Optional.empty();
        }

        System.out.println("Successfully loaded details for Cultural Heritage ID: " + culturalHeritageId);
        return Optional.of(selectedHeritage);
    }

    /**
     * Simulates the "SearchCulturalHeritage" use case by returning a list of available cultural heritage IDs.
     * This is a prerequisite for selecting a cultural heritage item to view.
     * @return A map of cultural heritage IDs to their names.
     */
    public Map<String, String> searchCulturalHeritage() {
        Map<String, String> searchResults = new HashMap<>();
        culturalHeritageDatabase.forEach((id, heritage) -> searchResults.put(id, heritage.getName()));
        System.out.println("Simulating 'SearchCulturalHeritage' use case. Available items:");
        searchResults.forEach((id, name) -> System.out.println("  ID: " + id + ", Name: " + name));
        return searchResults;
    }
}