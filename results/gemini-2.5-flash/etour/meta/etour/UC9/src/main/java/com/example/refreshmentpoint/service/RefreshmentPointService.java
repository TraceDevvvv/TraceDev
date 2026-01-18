package com.example.refreshmentpoint.service;

import com.example.refreshmentpoint.model.RefreshmentPoint;
import com.example.refreshmentpoint.repository.RefreshmentPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RefreshmentPointService {

    @Autowired
    private RefreshmentPointRepository repository;

    /**
     * Searches for refreshment points based on provided criteria.
     * Simulates a delay to represent processing time and adheres to the 15-second quality requirement.
     *
     * @param type The type of refreshment point (e.g., 'cafe', 'restaurant'). Can be null for no filter.
     * @param amenities A list of amenities (e.g., 'wifi', 'restroom'). Can be null or empty for no filter.
     * @return A list of RefreshmentPoint objects matching the criteria.
     * @throws RuntimeException if the operation exceeds 15 seconds (simulated).
     */
    public List<RefreshmentPoint> searchRefreshmentPoints(String type, List<String> amenities) {
        long startTime = System.nanoTime();

        // Simulate processing time
        try {
            // This delay can be adjusted to test the timeout
            TimeUnit.SECONDS.sleep(2); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Search interrupted", e);
        }

        List<RefreshmentPoint> results = repository.findByCriteria(type, amenities);

        long endTime = System.nanoTime();
        long duration = TimeUnit.NANOSECONDS.toSeconds(endTime - startTime);

        if (duration > 15) {
            throw new RuntimeException("Operation exceeded 15 seconds maximum.");
        }

        return results;
    }

    public RefreshmentPoint getRefreshmentPointById(String id) {
        return repository.findById(id);
    }
}
