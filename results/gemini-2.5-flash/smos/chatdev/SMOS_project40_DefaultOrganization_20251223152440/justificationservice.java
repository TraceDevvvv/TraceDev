'''
Provides business logic for managing Justification objects.
Simulates data storage and retrieval operations.
'''
package service;
import model.Justification;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * A singleton service class that manages Justification objects.
 * It simulates a database by storing justifications in a HashMap in memory.
 * Provides methods for retrieving, updating, and adding justifications.
 */
public class JustificationService {
    private static JustificationService instance;
    private final Map<Integer, Justification> justifications;
    private final AtomicInteger nextId; // Used to generate unique IDs for new justifications
    /**
     * Private constructor to ensure singleton pattern.
     * Initializes the internal storage and ID counter.
     */
    private JustificationService() {
        justifications = new HashMap<>(); // Stores justifications by their ID
        nextId = new AtomicInteger(1);   // Start IDs from 1
    }
    /**
     * Provides the singleton instance of JustificationService.
     * @return The single instance of JustificationService.
     */
    public static synchronized JustificationService getInstance() {
        if (instance == null) {
            instance = new JustificationService();
        }
        return instance;
    }
    /**
     * Retrieves a Justification by its ID.
     * @param id The ID of the justification to retrieve.
     * @return The Justification object if found, otherwise null.
     */
    public Justification getJustificationById(int id) {
        return justifications.get(id);
    }
    /**
     * Updates an existing Justification.
     * If a justification with the given ID exists, its date and description are updated.
     * @param updatedJustification The Justification object with updated fields.
     * @return true if the justification was found and updated, false otherwise.
     */
    public boolean updateJustification(Justification updatedJustification) {
        // Check if the justification to be updated exists
        if (justifications.containsKey(updatedJustification.getId())) {
            Justification existingJustification = justifications.get(updatedJustification.getId());
            // Update the fields from the provided object
            existingJustification.setJustificationDate(updatedJustification.getJustificationDate());
            existingJustification.setDescription(updatedJustification.getDescription());
            System.out.println("Justification updated: " + existingJustification); // Log the update
            return true; // Successfully updated
        }
        return false; // Justification not found
    }
    /**
     * Adds a new Justification to the service.
     * If the justification's ID is not set or is already used, a new unique ID is assigned.
     * This method is useful for populating initial data or adding new records.
     * @param justification The Justification object to add.
     */
    public void addJustification(Justification justification) {
        // Assign a new ID if the provided ID is 0 or is already in use
        if (justification.getId() == 0 || justifications.containsKey(justification.getId())) {
            justification.setId(nextId.getAndIncrement()); // Assign a new auto-incremented ID
        } else {
            // Ensure nextId is greater than any manually assigned ID
            nextId.set(Math.max(nextId.get(), justification.getId() + 1));
        }
        justifications.put(justification.getId(), justification);
        System.out.println("Justification added: " + justification); // Log the addition
    }
    /**
     * Retrieves all justifications currently stored in the service.
     * @return A List of all Justification objects.
     */
    public List<Justification> getAllJustifications() {
        return new ArrayList<>(justifications.values()); // Return a new list to prevent direct modification of internal map
    }
}