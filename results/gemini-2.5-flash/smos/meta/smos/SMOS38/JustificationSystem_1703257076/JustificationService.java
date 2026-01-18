import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Service class responsible for managing Justification entities.
 * This class handles business logic such as retrieving, updating, and deleting justifications.
 * It simulates a data store using an in-memory HashMap for demonstration purposes.
 */
public class JustificationService {

    // In-memory data store for justifications, mapping ID to Justification object.
    private final Map<String, Justification> justificationStore;

    /**
     * Constructor for JustificationService.
     * Initializes the in-memory data store and populates it with some sample data.
     */
    public JustificationService() {
        this.justificationStore = new HashMap<>();
        // Populate with some sample justifications for demonstration
        // Simulating "green absences" that an administrator might click on.
        addJustification(new Justification("J001", "A001", "Medical appointment", Justification.JustificationStatus.PENDING, LocalDateTime.now().minusDays(2)));
        addJustification(new Justification("J002", "A002", "Family emergency", Justification.JustificationStatus.APPROVED, LocalDateTime.now().minusDays(5)));
        addJustification(new Justification("J003", "A003", "Car breakdown", Justification.JustificationStatus.REJECTED, LocalDateTime.now().minusDays(1)));
        addJustification(new Justification("J004", "A004", "Sick leave", Justification.JustificationStatus.PENDING, LocalDateTime.now()));
    }

    /**
     * Retrieves a justification by its unique identifier.
     *
     * @param justificationId The ID of the justification to retrieve.
     * @return An Optional containing the Justification object if found, otherwise an empty Optional.
     */
    public Optional<Justification> getJustificationById(String justificationId) {
        // Return the justification from the store if it exists.
        return Optional.ofNullable(justificationStore.get(justificationId));
    }

    /**
     * Adds a new justification to the system.
     * This method is primarily for internal use or initial data population.
     *
     * @param justification The Justification object to add.
     * @return true if the justification was added successfully, false if an entry with the same ID already exists.
     */
    public boolean addJustification(Justification justification) {
        if (justification == null || justification.getId() == null || justificationStore.containsKey(justification.getId())) {
            return false; // Cannot add null or duplicate ID justification
        }
        justificationStore.put(justification.getId(), justification);
        System.out.println("Added justification: " + justification.getId());
        return true;
    }

    /**
     * Updates an existing justification in the system.
     *
     * @param updatedJustification The Justification object with updated details.
     * @return An Optional containing the updated Justification object if successful, otherwise an empty Optional.
     */
    public Optional<Justification> updateJustification(Justification updatedJustification) {
        if (updatedJustification == null || updatedJustification.getId() == null) {
            System.err.println("Error: Cannot update with a null justification or null ID.");
            return Optional.empty();
        }

        // Check if the justification exists before attempting to update.
        if (justificationStore.containsKey(updatedJustification.getId())) {
            justificationStore.put(updatedJustification.getId(), updatedJustification);
            System.out.println("Justification " + updatedJustification.getId() + " updated successfully.");
            return Optional.of(updatedJustification);
        } else {
            System.err.println("Error: Justification with ID " + updatedJustification.getId() + " not found for update.");
            return Optional.empty();
        }
    }

    /**
     * Deletes a justification from the system by its unique identifier.
     *
     * @param justificationId The ID of the justification to delete.
     * @return An Optional containing the deleted Justification object if successful, otherwise an empty Optional.
     */
    public Optional<Justification> deleteJustification(String justificationId) {
        if (justificationId == null || justificationId.trim().isEmpty()) {
            System.err.println("Error: Justification ID cannot be null or empty for deletion.");
            return Optional.empty();
        }

        // Remove the justification from the store if it exists.
        Justification removedJustification = justificationStore.remove(justificationId);
        if (removedJustification != null) {
            System.out.println("Justification " + justificationId + " deleted successfully.");
            return Optional.of(removedJustification);
        } else {
            System.err.println("Error: Justification with ID " + justificationId + " not found for deletion.");
            return Optional.empty();
        }
    }

    /**
     * Simulates checking if an absence ID corresponds to a "green absence"
     * that an administrator can click on. In a real system, this would involve
     * more complex logic, possibly checking absence status or type.
     * For this simulation, we'll just check if a justification exists for it.
     *
     * @param absenceId The ID of the absence to check.
     * @return true if a justification exists for the given absence ID, false otherwise.
     */
    public boolean isGreenAbsence(String absenceId) {
        // In a real system, "green absence" might mean an absence that is pending justification
        // or has a justification that needs review. For this example, we'll simply check
        // if any justification is associated with this absence ID.
        return justificationStore.values().stream()
                .anyMatch(j -> j.getAbsenceId().equals(absenceId));
    }
}