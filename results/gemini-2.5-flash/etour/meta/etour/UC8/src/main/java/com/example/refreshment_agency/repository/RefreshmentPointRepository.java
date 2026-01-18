package com.example.refreshment_agency.repository;

import com.example.refreshment_agency.model.RefreshmentPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repository class to simulate data access for RefreshmentPoint objects.
 * This class acts as an in-memory database for demonstration purposes.
 * In a real application, this would interact with a persistent storage like a database.
 */
public class RefreshmentPointRepository {
    // Using ConcurrentHashMap to simulate a thread-safe data store
    private final Map<Long, RefreshmentPoint> refreshmentPoints = new ConcurrentHashMap<>();
    // AtomicLong for generating unique IDs, simulating auto-increment primary keys
    private final AtomicLong idCounter = new AtomicLong();

    /**
     * Initializes the repository with some sample data.
     */
    public RefreshmentPointRepository() {
        // Add some initial data
        save(new RefreshmentPoint(generateId(), "Oasis Cafe", "123 Main St", "contact@oasis.com", 50, "Coffee, Snacks, Wi-Fi", RefreshmentPoint.Status.ACTIVE));
        save(new RefreshmentPoint(generateId(), "Traveler's Rest", "456 Highway Rd", "info@travelersrest.net", 100, "Restaurant, Fuel, Restrooms", RefreshmentPoint.Status.ACTIVE));
        save(new RefreshmentPoint(generateId(), "Mountain View Stop", "789 Mountain Pass", "support@mtnview.org", 30, "Vending Machines, Scenic View", RefreshmentPoint.Status.INACTIVE));
        save(new RefreshmentPoint(generateId(), "Riverside Diner", "101 Riverfront Ave", "dine@riverside.com", 75, "Diner, Outdoor Seating", RefreshmentPoint.Status.ACTIVE));
    }

    /**
     * Generates a new unique ID for a RefreshmentPoint.
     *
     * @return A unique long ID.
     */
    private long generateId() {
        return idCounter.incrementAndGet();
    }

    /**
     * Finds all refreshment points stored in the repository.
     *
     * @return A list of all RefreshmentPoint objects.
     */
    public List<RefreshmentPoint> findAll() {
        return new ArrayList<>(refreshmentPoints.values());
    }

    /**
     * Finds a refreshment point by its ID.
     *
     * @param id The ID of the refreshment point to find.
     * @return An Optional containing the RefreshmentPoint if found, otherwise empty.
     */
    public Optional<RefreshmentPoint> findById(long id) {
        return Optional.ofNullable(refreshmentPoints.get(id));
    }

    /**
     * Saves a refreshment point. If the refreshment point's ID is 0 (new entity),
     * a new ID is generated. Otherwise, it updates an existing refreshment point.
     *
     * @param refreshmentPoint The RefreshmentPoint object to save or update.
     * @return The saved or updated RefreshmentPoint object.
     */
    public RefreshmentPoint save(RefreshmentPoint refreshmentPoint) {
        // In a real scenario, if ID is 0, it would be an insert.
        // For this simulation, we assume the ID is already set for updates.
        // If it's a new point being added, generate an ID.
        if (refreshmentPoint.getId() == 0) {
            refreshmentPoint.setId(generateId());
        }
        refreshmentPoints.put(refreshmentPoint.getId(), refreshmentPoint);
        return refreshmentPoint;
    }

    /**
     * Deletes a refreshment point by its ID.
     *
     * @param id The ID of the refreshment point to delete.
     */
    public void deleteById(long id) {
        refreshmentPoints.remove(id);
    }
}