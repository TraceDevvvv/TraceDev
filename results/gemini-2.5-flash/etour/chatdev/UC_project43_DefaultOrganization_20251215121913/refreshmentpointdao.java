'''
Data Access Object (DAO) for RefreshmentPoint.
This class simulates data storage and retrieval operations for refreshment points.
In a real application, this would interact with a database. Here, it uses an in-memory map.
'''
package dao;
import model.RefreshmentPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class RefreshmentPointDAO {
    // In-memory storage for refreshment points, mapping ID to RefreshmentPoint object.
    private final Map<String, RefreshmentPoint> refreshmentPoints = new HashMap<>();
    /**
     * Saves or updates a RefreshmentPoint in the data store.
     * If a point with the same ID already exists, it will be updated.
     *
     * @param point The RefreshmentPoint object to save.
     * @throws IllegalArgumentException if the point or its ID is null or empty.
     */
    public void save(RefreshmentPoint point) {
        if (point == null || point.getId() == null || point.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("RefreshmentPoint and its ID cannot be null or empty.");
        }
        System.out.println("DAO: Saving/Updating refreshment point: " + point.getId());
        refreshmentPoints.put(point.getId(), point);
    }
    /**
     * Finds a RefreshmentPoint by its unique ID.
     *
     * @param id The ID of the refreshment point to find.
     * @return The RefreshmentPoint object if found, otherwise null.
     */
    public RefreshmentPoint findById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null; // Or throw IllegalArgumentException, depending on desired behavior
        }
        System.out.println("DAO: Finding refreshment point by ID: " + id);
        // Simulate network delay for a real-world scenario (can be removed for faster testing)
        // try { Thread.sleep(200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        return refreshmentPoints.get(id);
    }
    /**
     * Retrieves a list of all available refreshment point IDs.
     *
     * @return A list of strings representing all known refreshment point IDs.
     */
    public List<String> getAllIds() {
        return new ArrayList<>(refreshmentPoints.keySet());
    }
    /**
     * Initializes the DAO with some sample data for demonstration purposes.
     */
    public void initSampleData() {
        System.out.println("DAO: Initializing sample data.");
        save(new RefreshmentPoint("RP001", "Cafe Central", "123 Main St", "555-1234", "A cozy cafe with free Wi-Fi."));
        save(new RefreshmentPoint("RP002", "Diner Deluxe", "456 Oak Ave", "555-5678", "Classic American diner food."));
        save(new RefreshmentPoint("RP003", "Pizzeria Pronto", "789 Pine Ln", "555-9012", "Fast and fresh pizza sl."));
    }
}