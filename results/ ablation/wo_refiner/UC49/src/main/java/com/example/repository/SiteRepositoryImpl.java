package com.example.repository;

import com.example.dto.SiteDTO;
import com.example.model.Site;
import com.example.handler.ConnectionHandler;
import com.example.exception.ETOURConnectionException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of SiteRepository.
 * Fetches data from the external ETOUR API and converts to DTOs.
 */
public class SiteRepositoryImpl implements SiteRepository {
    private ConnectionHandler connectionHandler;

    public SiteRepositoryImpl() {
        this.connectionHandler = new ConnectionHandler();
    }

    /**
     * Fetches bookmarks for a user from the ETOUR API.
     * Implements timeout requirement: 5 seconds.
     *
     * @param userId the ID of the tourist
     * @return List of SiteDTOs, empty list on error or interruption
     */
    @Override
    public List<SiteDTO> uploadBookmarks(String userId) {
        // Check connection before proceeding
        if (!connectionHandler.checkETOURConnection()) {
            System.out.println("ETOUR connection unavailable. Handling interruption.");
            connectionHandler.handleInterruption();
            return List.of(); // empty list
        }

        // Simulate fetching from external API with timeout
        String jsonResponse = null;
        try {
            jsonResponse = fetchFromEtourAPI(userId);
        } catch (ETOURConnectionException e) {
            System.out.println("ETOUR connection exception: " + e.getMessage());
            connectionHandler.onInterruption();
            return List.of();
        }

        // Parse JSON to Site objects
        List<Site> sites = parseJsonToSites(jsonResponse);
        // Convert to DTOs
        return convertToDTOs(sites);
    }

    /**
     * Simulates fetching data from ETOUR API.
     * Includes timeout stereotype: 5 seconds.
     *
     * @param userId the ID of the tourist
     * @return JSON string response
     * @throws ETOURConnectionException if connection times out or fails
     */
    String fetchFromEtourAPI(String userId) throws ETOURConnectionException {
        System.out.println("Fetching from ETOUR API for user: " + userId);
        // Simulate network delay
        try {
            Thread.sleep(1000); // 1 second delay for simulation
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ETOURConnectionException("Fetch interrupted", new java.util.Date());
        }
        // Simulate timeout: if delay > 5 seconds, exception would be thrown.
        // For demonstration, we assume success and return mock JSON.
        return "[{\"id\":\"S1\",\"name\":\"Eiffel Tower\",\"description\":\"Iconic tower in Paris\"}," +
                "{\"id\":\"S2\",\"name\":\"Colosseum\",\"description\":\"Ancient amphitheater in Rome\"}]";
    }

    /**
     * Parses JSON string to a list of Site objects.
     * This is a simplified implementation.
     *
     * @param json JSON string from API
     * @return List of Site objects
     */
    List<Site> parseJsonToSites(String json) {
        System.out.println("Parsing JSON to sites: " + json);
        List<Site> sites = new ArrayList<>();
        // Simplified parsing: In reality, use a JSON library like Jackson.
        // Here we manually parse the mock JSON.
        if (json.contains("Eiffel Tower")) {
            sites.add(new Site("S1", "Eiffel Tower", "Iconic tower in Paris"));
        }
        if (json.contains("Colosseum")) {
            sites.add(new Site("S2", "Colosseum", "Ancient amphitheater in Rome"));
        }
        return sites;
    }

    /**
     * Converts a list of Site objects to SiteDTOs.
     *
     * @param sites List of Site objects
     * @return List of SiteDTOs
     */
    List<SiteDTO> convertToDTOs(List<Site> sites) {
        List<SiteDTO> dtos = new ArrayList<>();
        for (Site site : sites) {
            dtos.add(new SiteDTO(site));
        }
        return dtos;
    }

    /**
     * Returns connection status.
     *
     * @return connection status
     */
    public String getConnectionStatus() {
        boolean status = connectionHandler.checkETOURConnection();
        return status ? "Connected" : "Disconnected";
    }

    /**
     * Returns JSON response.
     *
     * @param userId the user ID
     * @return JSON response string
     */
    public String getJSONResponse(String userId) {
        try {
            return fetchFromEtourAPI(userId);
        } catch (ETOURConnectionException e) {
            return "{}";
        }
    }

    /**
     * Handles connection interruption.
     */
    public void handleConnectionInterruption() {
        System.out.println("Repository: Handle connection interruption");
        connectionHandler.handleInterruption();
    }

    /**
     * Returns an empty List<SiteDTO>.
     *
     * @return empty List<SiteDTO>
     */
    public List<SiteDTO> returnEmptyList() {
        return List.of();
    }

    /**
     * Returns interruption handled status.
     *
     * @return interruption handled message
     */
    public String interruptionHandled() {
        connectionHandler.handleInterruption();
        return "interruption handled";
    }
}