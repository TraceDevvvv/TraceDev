package com.example.service;

import com.example.model.CulturalHeritage;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Service layer that handles business logic for cultural heritage operations.
 * Simulates interaction with a server (e.g., ETOUR).
 */
public class CulturalHeritageService {
    // Simulated database of cultural heritage items
    private List<CulturalHeritage> heritageDatabase;
    
    public CulturalHeritageService() {
        initializeDatabase();
    }
    
    private void initializeDatabase() {
        heritageDatabase = new ArrayList<>();
        heritageDatabase.add(new CulturalHeritage(1, "Colosseum", 
            "An ancient amphitheater in Rome, Italy, built of concrete and sand.", 
            "Rome, Italy", "Roman Empire", "Monument", 
            "http://example.com/colosseum.jpg", true));
        heritageDatabase.add(new CulturalHeritage(2, "Mona Lisa", 
            "A half-length portrait painting by Italian artist Leonardo da Vinci.", 
            "Paris, France", "Renaissance", "Painting", 
            "http://example.com/monalisa.jpg", true));
        heritageDatabase.add(new CulturalHeritage(3, "Great Wall of China", 
            "Series of fortifications made of stone, brick, tamped earth, wood, and other materials.", 
            "Northern China", "Various Dynasties", "Fortification", 
            "http://example.com/greatwall.jpg", true));
        heritageDatabase.add(new CulturalHeritage(4, "Taj Mahal", 
            "An ivory-white marble mausoleum on the right bank of the river Yamuna in Agra, India.", 
            "Agra, India", "Mughal Empire", "Mausoleum", 
            "http://example.com/tajmahal.jpg", true));
    }
    
    /**
     * Simulates fetching all cultural heritage items (from SearchCulturalHeritage use case).
     * @return List of all cultural heritage items.
     */
    public List<CulturalHeritage> getAllCulturalHeritage() {
        return new ArrayList<>(heritageDatabase);
    }
    
    /**
     * Retrieves a cultural heritage item by ID.
     * Simulates loading data from server with potential timeout.
     * @param id The ID of the cultural heritage.
     * @return Optional containing the cultural heritage if found.
     * @throws InterruptedException if the thread is interrupted.
     * @throws ExecutionException if computation threw an exception.
     * @throws TimeoutException if wait timed out (simulating server interruption).
     */
    public CompletableFuture<Optional<CulturalHeritage>> getCulturalHeritageById(int id) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate network delay for realistic response times
            try {
                Thread.sleep(200); // Simulate network latency
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Search for the cultural heritage by ID
            return heritageDatabase.stream()
                    .filter(ch -> ch.getId() == id)
                    .findFirst();
        });
    }
    
    /**
     * Simulates a server interruption by timing out the future.
     * This mimics the "connection to the server ETOUR is interrupted" exit condition.
     * @param id The ID of the cultural heritage.
     * @return The cultural heritage if retrieved within timeout.
     * @throws TimeoutException if the operation times out (server interruption).
     */
    public CulturalHeritage getCulturalHeritageWithTimeout(int id) 
            throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<Optional<CulturalHeritage>> future = getCulturalHeritageById(id);
        // Set a short timeout to simulate quick response requirement
        Optional<CulturalHeritage> result = future.get(1, TimeUnit.SECONDS);
        return result.orElse(null);
    }
}