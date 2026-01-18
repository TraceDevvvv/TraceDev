import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Service class responsible for handling business logic related to Justifications.
 * This includes operations like retrieving and updating justification details.
 * It simulates interaction with a data store.
 *
 * This service fulfills the core system actions for the "EditJustification" use case:
 * - Retrieving a justification (implied by "viewdetalticaustifica" precondition).
 * - Changing the justification (event sequence: "System changes the justification").
 */
public class JustificationService {

    // A simple in-memory map to simulate a database or repository for Justification objects.
    // In a real-world application, this would be replaced by a proper data access layer
    // (e.g., using JDBC, JPA, or an ORM framework) to interact with a persistent database.
    private final Map<String, Justification> justificationStore;

    /**
     * Constructs a new JustificationService and initializes the in-memory store
     * with some sample justification data. This data serves as initial state
     * for demonstration purposes.
     */
    public JustificationService() {
        this.justificationStore = new HashMap<>();
        // Adding some initial data to simulate existing justifications.
        // These would typically be loaded from a persistent data store upon application startup.
        justificationStore.put("J001", new Justification("J001", LocalDate.of(2023, 1, 15)));
        justificationStore.put("J002", new Justification("J002", LocalDate.of(2023, 2, 20)));
        justificationStore.put("J003", new Justification("J003", LocalDate.of(2023, 3, 10)));
    }

    /**
     * Retrieves a justification by its unique identifier.
     * This method supports the "viewdetalticaustifica" precondition, allowing the system
     * to display the details of a justification before an administrator can edit it.
     *
     * @param id The unique identifier of the justification to retrieve.
     * @return An {@link Optional} containing the {@link Justification} if a justification
     *         with the given ID is found. Returns an empty {@link Optional} if no such
     *         justification exists or if the provided ID is invalid.
     */
    public Optional<Justification> getJustificationById(String id) {
        // Validate the input ID to prevent issues with map lookups.
        if (id == null || id.trim().isEmpty()) {
            System.err.println("Attempted to retrieve justification with null or empty ID.");
            return Optional.empty(); // An invalid ID effectively means no justification found.
        }
        // Retrieve the justification from the simulated store.
        // Optional.ofNullable handles cases where the key might not exist, returning an empty Optional.
        return Optional.ofNullable(justificationStore.get(id));
    }

    /**
     * Updates an existing justification in the system with new details.
     * This method implements the "System changes the justification" event
     * after an administrator has modified the form and clicked "Save".
     *
     * @param updatedJustification The {@link Justification} object containing the updated information.
     *                             The ID of this object must correspond to an existing justification
     *                             for the update to be successful.
     * @return The successfully updated {@link Justification} object.
     * @throws IllegalArgumentException If the provided {@code updatedJustification} object is null,
     *                                  or if its ID is null or empty, indicating invalid input.
     * @throws JustificationNotFoundException If no justification with the ID specified in
     *                                        {@code updatedJustification} is found in the system,
     *                                        meaning there's nothing to update.
     */
    public Justification updateJustification(Justification updatedJustification) throws JustificationNotFoundException {
        // --- Input Validation ---
        if (updatedJustification == null) {
            throw new IllegalArgumentException("Updated justification object cannot be null.");
        }
        if (updatedJustification.getId() == null || updatedJustification.getId().trim().isEmpty()) {
            throw new IllegalArgumentException("ID of the updated justification cannot be null or empty.");
        }

        String id = updatedJustification.getId();

        // --- Business Logic Validation ---
        // Check if the justification to be updated actually exists in the store.
        // This prevents updating non-existent entities and ensures data integrity.
        if (!justificationStore.containsKey(id)) {
            throw new JustificationNotFoundException("Justification with ID '" + id + "' not found for update. " +
                                                     "Cannot update a non-existent justification.");
        }

        // --- Perform Update ---
        // In a HashMap, 'put' operation replaces the value if the key already exists,
        // effectively performing an update.
        justificationStore.put(id, updatedJustification);
        System.out.println("Justification with ID '" + id + "' successfully updated to: " + updatedJustification);

        // Return the updated object, confirming the operation.
        return updatedJustification;
    }
}