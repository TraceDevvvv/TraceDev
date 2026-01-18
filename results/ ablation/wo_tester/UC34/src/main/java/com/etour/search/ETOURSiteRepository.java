package com.etour.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Concrete implementation of ISiteRepository that connects to the ETOUR server.
 */
public class ETOURSiteRepository implements ISiteRepository {
    private String connection; // Simulated database connection
    
    public ETOURSiteRepository() {
        this.connection = "ETOUR_DB_CONNECTION";
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }

    @Override
    public List<Site> findByCriteria(SearchCriteria criteria, Location location) {
        System.out.println("Querying ETOUR server with criteria: " + criteria.getSearchString());
        
        // Check connection first
        if (!testConnection()) {
            throw new ConnectionError("Connection to ETOUR server failed", 1001);
        }
        
        // Simulate fetching data from server
        String serverData = fetchFromServer();
        System.out.println("Received data from server: " + serverData);
        
        // Simulate processing and returning results
        List<Site> sites = new ArrayList<>();
        Random rand = new Random();
        
        // Generate some mock sites based on criteria
        int resultCount = Math.min(5, 3 + rand.nextInt(7)); // 3-10 results
        for (int i = 1; i <= resultCount; i++) {
            Site site = new Site();
            site.setId(i);
            site.setName("Historical Site " + i + " matching '" + criteria.getKeywords() + "'");
            site.setDescription("A fascinating historical site from the " + criteria.getCategory() + " period.");
            site.setLatitude(location.getLatitude() + (rand.nextDouble() - 0.5) * 0.1);
            site.setLongitude(location.getLongitude() + (rand.nextDouble() - 0.5) * 0.1);
            site.setCategory(SiteCategory.valueOf(criteria.getCategory().toUpperCase()));
            site.setHistoricalPeriod("Ancient");
            sites.add(site);
        }
        
        return sites;
    }

    @Override
    public List<Site> findAll() {
        // Not implemented for this use case
        throw new UnsupportedOperationException("findAll not implemented");
    }

    @Override
    public boolean testConnection() {
        // Simulate connection test
        // In real implementation, this would ping the server
        System.out.println("Testing connection to ETOUR server...");
        return connection != null && !connection.isEmpty();
    }

    /**
     * Fetches data from the ETOUR server.
     * @return String containing raw site data.
     */
    protected String fetchFromServer() {
        // Simulate server interaction
        System.out.println("Fetching data from ETOUR server...");
        // Simulate processing query
        try {
            Thread.sleep(100); // Simulate network delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return "SITE_DATA_FROM_ETOUR_SERVER";
    }

    /**
     * Fetches site data from the database based on criteria.
     * @param criteria Search criteria.
     * @return siteData:List<Site>
     */
    public List<Site> fetchSiteData(SearchCriteria criteria) {
        System.out.println("Fetching site data for criteria: " + criteria.getSearchString());
        try {
            // Get current location for distance calculations
            GeolocationService geoService = new GeolocationService();
            Location location = geoService.getUserLocation();
            return findByCriteria(criteria, location);
        } catch (Exception e) {
            System.err.println("Error fetching site data: " + e.getMessage());
            throw new ConnectionError("Failed to fetch site data: " + e.getMessage(), 1002);
        }
    }

    /**
     * Processes query internally.
     */
    public void processQuery() {
        System.out.println("Processing database query internally...");
        // Simulate query processing
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Simulates connection failure scenario.
     */
    public void connectionFailed() {
        System.out.println("Connection failed simulation triggered.");
        // This method simulates the connection failure return message
    }

    /**
     * Returns connection status.
     * @return connectionStatus:boolean
     */
    public boolean getConnectionStatus() {
        System.out.println("Getting connection status...");
        boolean status = testConnection();
        System.out.println("Connection status: " + status);
        return status;
    }
}