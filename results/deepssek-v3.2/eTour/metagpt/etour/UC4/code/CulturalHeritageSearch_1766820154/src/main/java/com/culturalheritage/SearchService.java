package com.culturalheritage;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

/**
 * Service class for searching cultural heritage objects.
 * Implements search functionality with timeout handling and error management
 * to meet quality requirements (return results within set time).
 */
public class SearchService {
    
    // Database/repository simulation - in a real system, this would be a database connection
    private List<CulturalObject> culturalObjectsDatabase;
    
    // Default timeout in milliseconds (5 seconds)
    private static final long DEFAULT_TIMEOUT_MS = 5000;
    
    // Executor service for handling search with timeout
    private final ExecutorService executorService;
    
    /**
     * Constructor that initializes with a sample database.
     * In a real application, this would connect to an actual database.
     */
    public SearchService() {
        this.culturalObjectsDatabase = new ArrayList<>();
        this.executorService = Executors.newSingleThreadExecutor();
        initializeSampleData();
    }
    
    /**
     * Constructor that accepts an existing database.
     * Useful for testing with custom data.
     * 
     * @param culturalObjectsDatabase list of cultural objects to search through
     */
    public SearchService(List<CulturalObject> culturalObjectsDatabase) {
        this.culturalObjectsDatabase = culturalObjectsDatabase;
        this.executorService = Executors.newSingleThreadExecutor();
    }
    
    /**
     * Initializes the service with sample cultural objects for demonstration.
     * In a real system, this would be replaced with database initialization.
     */
    private void initializeSampleData() {
        // Add sample cultural objects
        culturalObjectsDatabase.add(new CulturalObject(
            "CO-001", "Mona Lisa", "Painting", "Renaissance", "Louvre Museum, Paris",
            "World-famous portrait painting by Leonardo da Vinci", 
            Year.of(1503), "Italy", true
        ));
        
        culturalObjectsDatabase.add(new CulturalObject(
            "CO-002", "Statue of Liberty", "Monument", "Modern", "New York Harbor",
            "Giant neoclassical sculpture gifted by France to the United States",
            Year.of(1886), "France", true
        ));
        
        culturalObjectsDatabase.add(new CulturalObject(
            "CO-003", "Terracotta Army", "Archaeological Artifact", "Ancient China", 
            "Xi'an, Shaanxi Province", "Collection of terracotta sculptures depicting armies of Qin Shi Huang",
            Year.of(-210), "China", true
        ));
        
        culturalObjectsDatabase.add(new CulturalObject(
            "CO-004", "Colosseum", "Architecture", "Roman Empire", "Rome, Italy",
            "Ancient amphitheater built during the Flavian dynasty",
            Year.of(80), "Italy", true
        ));
        
        culturalObjectsDatabase.add(new CulturalObject(
            "CO-005", "Great Wall of China", "Architecture", "Various Dynasties", 
            "Northern China", "Series of fortifications made of stone, brick, and other materials",
            Year.of(-700), "China", true
        ));
        
        culturalObjectsDatabase.add(new CulturalObject(
            "CO-006", "Venus de Milo", "Sculpture", "Ancient Greece", "Louvre Museum, Paris",
            "Ancient Greek statue depicting Aphrodite, the Greek goddess of love and beauty",
            Year.of(-130), "Greece", true
        ));
        
        culturalObjectsDatabase.add(new CulturalObject(
            "CO-007", "Local Folk Painting", "Painting", "Modern", "Community Art Center",
            "Traditional folk painting from local artists",
            Year.of(1995), "Unknown", false
        ));
        
        culturalObjectsDatabase.add(new CulturalObject(
            "CO-008", "Ancient Vase", "Artifact", "Bronze Age", "National Museum",
            "Ceramic vase from the Bronze Age period",
            Year.of(-1500), "Unknown", true
        ));
    }
    
    /**
     * Main search method that processes a search form and returns matching cultural objects.
     * Implements timeout handling to ensure results are returned within a set time.
     * 
     * @param searchForm the search criteria form
     * @return list of cultural objects matching the search criteria
     * @throws SearchException if the search fails or times out
     */
    public List<CulturalObject> search(SearchForm searchForm) throws SearchException {
        return search(searchForm, DEFAULT_TIMEOUT_MS);
    }
    
    /**
     * Search method with customizable timeout.
     * 
     * @param searchForm the search criteria form
     * @param timeoutMs timeout in milliseconds
     * @return list of cultural objects matching the search criteria
     * @throws SearchException if the search fails or times out
     */
    public List<CulturalObject> search(SearchForm searchForm, long timeoutMs) throws SearchException {
        // Validate the search form
        if (!searchForm.isValid()) {
            throw new SearchException("Invalid search criteria. Please check your input.");
        }
        
        // Create a callable task for the search operation
        Callable<List<CulturalObject>> searchTask = () -> {
            // Simulate potential server connection delay (ETOUR interruption scenario)
            simulatePotentialServerDelay();
            
            // Perform the actual search
            return performSearch(searchForm);
        };
        
        // Submit the task to the executor with timeout
        Future<List<CulturalObject>> future = executorService.submit(searchTask);
        
        try {
            // Wait for the result with timeout
            return future.get(timeoutMs, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            // Cancel the task if it times out
            future.cancel(true);
            throw new SearchException("Search timed out after " + timeoutMs + "ms. Please try again.");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupt status
            throw new SearchException("Search was interrupted.", e);
        } catch (ExecutionException e) {
            // Handle exceptions thrown during search execution
            Throwable cause = e.getCause();
            if (cause instanceof SearchException) {
                throw (SearchException) cause;
            } else {
                throw new SearchException("Search failed due to an unexpected error.", cause);
            }
        }
    }
    
    /**
     * Performs the actual search against the cultural objects database.
     * This method contains the core search logic.
     * 
     * @param searchForm the search criteria form
     * @return list of matching cultural objects
     */
    private List<CulturalObject> performSearch(SearchForm searchForm) {
        // If form is empty, return all objects (or an empty list based on requirements)
        if (searchForm.isEmpty()) {
            // Return a copy to prevent modification of the internal list
            return new ArrayList<>(culturalObjectsDatabase);
        }
        
        // Use Java Streams for efficient filtering (meets efficiency requirement)
        return culturalObjectsDatabase.stream()
            .filter(obj -> matchesAllCriteria(obj, searchForm))
            .collect(Collectors.toList());
    }
    
    /**
     * Checks if a cultural object matches all criteria in the search form.
     * 
     * @param obj the cultural object to check
     * @param form the search form with criteria
     * @return true if the object matches all criteria, false otherwise
     */
    private boolean matchesAllCriteria(CulturalObject obj, SearchForm form) {
        // Check basic criteria using the object's helper method
        if (!obj.matchesCriteria(form.getName(), form.getType(), form.getEra(), form.getLocation())) {
            return false;
        }
        
        // Check country of origin
        if (form.getCountryOfOrigin() != null && !form.getCountryOfOrigin().isEmpty()) {
            if (obj.getCountryOfOrigin() == null || 
                !obj.getCountryOfOrigin().equalsIgnoreCase(form.getCountryOfOrigin())) {
                return false;
            }
        }
        
        // Check year range
        if (form.getMinYear() != null && obj.getYearCreated() != null) {
            if (obj.getYearCreated().isBefore(form.getMinYear())) {
                return false;
            }
        }
        
        if (form.getMaxYear() != null && obj.getYearCreated() != null) {
            if (obj.getYearCreated().isAfter(form.getMaxYear())) {
                return false;
            }
        }
        
        // Check protected status
        if (form.getIsProtected() != null) {
            if (obj.isProtected() != form.getIsProtected()) {
                return false;
            }
        }
        
        // All criteria matched
        return true;
    }
    
    /**
     * Simulates potential server connection delay or interruption.
     * In a real system, this would be actual network/database operations.
     * This method simulates the "Interruption of the connection to the server ETOUR" scenario.
     */
    private void simulatePotentialServerDelay() {
        // Simulate a 10% chance of a delay (for demonstration purposes)
        if (Math.random() < 0.1) {
            try {
                // Simulate a network delay of 1-3 seconds
                long delay = 1000 + (long)(Math.random() * 2000);
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Server connection interrupted (ETOUR scenario)", e);
            }
        }
    }
    
    /**
     * Gets all cultural objects in the database (for testing and demonstration).
     * 
     * @return all cultural objects
     */
    public List<CulturalObject> getAllCulturalObjects() {
        return new ArrayList<>(culturalObjectsDatabase);
    }
    
    /**
     * Adds a cultural object to the database (for testing and extension).
     * 
     * @param obj the cultural object to add
     */
    public void addCulturalObject(CulturalObject obj) {
        culturalObjectsDatabase.add(obj);
    }
    
    /**
     * Shuts down the executor service to release resources.
     * Should be called when the service is no longer needed.
     */
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Custom exception for search-related errors.
     * Provides specific error information for different failure scenarios.
     */
    public static class SearchException extends Exception {
        public SearchException(String message) {
            super(message);
        }
        
        public SearchException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}