/**
 * This class simulates a service layer for managing Justification objects.
 * It contains an in-memory list to store justifications, mimicking a database
 * interaction for fetching, updating, and deleting justifications.
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class JustificationService {
    // In-memory storage for justifications, simulating a database.
    private Map<String, Justification> justifications;
    /**
     * Constructor for JustificationService.
     * Initializes the in-memory storage with sample data.
     */
    public JustificationService() {
        this.justifications = new HashMap<>();
        // Populate with some sample data to simulate existing justifications.
        populateSampleJustifications();
    }
    /**
     * Populates the service with initial sample justification data.
     */
    private void populateSampleJustifications() {
        justifications.put("J001", new Justification("J001", "Alice Smith", LocalDate.of(2023, 10, 26), "Flu symptoms, doctor's note provided.", "Pending"));
        justifications.put("J002", new Justification("J002", "Bob Johnson", LocalDate.of(2023, 10, 27), "Family emergency, unexpected travel.", "Approved"));
        // Example of potentially incomplete data for 'J003' where absenceDate is null
        // This is safe now due to the fix in Justification.toString()
        justifications.put("J003", new Justification("J003", "Charlie Brown", null, "Meeting not relevant.", "Rejected"));
    }
    /**
     * Retrieves a Justification by its ID.
     * @param id The ID of the justification to retrieve.
     * @return The Justification object if found, otherwise null.
     */
    public Justification getJustificationById(String id) {
        // Simulates fetching from a database.
        System.out.println("Service: Attempting to retrieve justification with ID: " + id);
        return justifications.get(id);
    }
    /**
     * Retrieves all Justification objects currently in the service.
     * @return A List of all Justification objects.
     */
    public List<Justification> getAllJustifications() {
        // Return a new ArrayList containing all justification values to prevent external modification of the internal map.
        return new ArrayList<>(justifications.values());
    }
    /**
     * Updates an existing Justification in the system.
     * @param updatedJustification The Justification object with updated details.
     * @return True if the justification was successfully updated, false otherwise (e.g., ID not found).
     */
    public boolean updateJustification(Justification updatedJustification) {
        // Simulates updating a record in a database.
        if (updatedJustification != null && justifications.containsKey(updatedJustification.getId())) {
            justifications.put(updatedJustification.getId(), updatedJustification);
            System.out.println("Service: Justification " + updatedJustification.getId() + " updated successfully.");
            return true;
        }
        System.out.println("Service: Failed to update justification " + (updatedJustification != null ? updatedJustification.getId() : "null") + ". ID not found or justification is null.");
        return false;
    }
    /**
     * Deletes a Justification from the system by its ID.
     * @param id The ID of the justification to delete.
     * @return True if the justification was successfully deleted, false otherwise (e.g., ID not found).
     */
    public boolean deleteJustification(String id) {
        // Simulates deleting a record from a database.
        if (justifications.containsKey(id)) {
            justifications.remove(id);
            System.out.println("Service: Justification " + id + " deleted successfully.");
            return true;
        }
        System.out.println("Service: Failed to delete justification " + id + ". ID not found.");
        return false;
    }
}