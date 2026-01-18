import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Service class responsible for handling the business logic related to
 * justification insertion. This includes checking preconditions,
 * saving justification data, and simulating interactions with other system components.
 */
public class JustificationService {

    // Simulates a database or persistent storage for justifications
    private final Map<String, Justification> justificationStore;
    // Simulates a database or persistent storage for absences
    private final Map<String, String> absenceStore; // absenceId -> absenceDetails (simplified)

    // Reference to the currently logged-in administrator
    private Administrator currentAdministrator;

    /**
     * Constructs a new JustificationService.
     *
     * @param currentAdministrator The administrator currently interacting with the system.
     */
    public JustificationService(Administrator currentAdministrator) {
        this.justificationStore = new HashMap<>();
        this.absenceStore = new HashMap<>();
        this.currentAdministrator = currentAdministrator;
        // Populate some dummy absences for demonstration
        absenceStore.put("ABS001", "Absence for John Doe on 2023-10-26");
        absenceStore.put("ABS002", "Absence for Jane Smith on 2023-11-15");
        absenceStore.put("ABS003", "Absence for Bob Johnson on 2023-12-01 (in red)"); // Simulate an absence in red
    }

    /**
     * Sets the current administrator. This might be used if the service
     * instance is long-lived but the user changes.
     *
     * @param administrator The administrator to set as current.
     */
    public void setCurrentAdministrator(Administrator administrator) {
        this.currentAdministrator = administrator;
    }

    /**
     * Checks all necessary preconditions before allowing a justification to be inserted.
     *
     * @param absenceId The ID of the absence for which justification is being inserted.
     * @return true if all preconditions are met, false otherwise.
     */
    public boolean checkPreconditions(String absenceId) {
        // Precondition 1: The user must be logged in to the system as an administrator.
        if (currentAdministrator == null || !currentAdministrator.isLoggedIn()) {
            System.out.println("Precondition Failed: Administrator not logged in.");
            return false;
        }
        System.out.println("Precondition Met: Administrator '" + currentAdministrator.getUsername() + "' is logged in.");

        // Precondition 2: The user has carried out the case of use "SviewTetTingloregister".
        // This is simulated as a successful prior action.
        boolean sviewTetTingloregisterCompleted = simulateSviewTetTingloregister();
        if (!sviewTetTingloregisterCompleted) {
            System.out.println("Precondition Failed: 'SviewTetTingloregister' use case not completed.");
            return false;
        }
        System.out.println("Precondition Met: 'SviewTetTingloregister' use case completed.");

        // Precondition 3: The user has held the case of use "viewellacogiustifies".
        // This is simulated as a successful prior action.
        boolean viewellacogiustifiesCompleted = simulateViewellacogiustifies();
        if (!viewellacogiustifiesCompleted) {
            System.out.println("Precondition Failed: 'viewellacogiustifies' use case not completed.");
            return false;
        }
        System.out.println("Precondition Met: 'viewellacogiustifies' use case completed.");

        // Precondition 4: The user clicks on one of the absences in red.
        // This is simulated by checking if the provided absenceId exists and is marked as "in red".
        if (!absenceStore.containsKey(absenceId)) {
            System.out.println("Precondition Failed: Absence with ID '" + absenceId + "' not found.");
            return false;
        }
        // For simulation, we assume "ABS003" is the absence in red.
        if (!absenceId.equals("ABS003")) {
            System.out.println("Precondition Failed: Absence with ID '" + absenceId + "' is not an absence 'in red'.");
            return false;
        }
        System.out.println("Precondition Met: Absence '" + absenceId + "' (in red) selected.");

        return true;
    }

    /**
     * Simulates the completion of the "SviewTetTingloregister" use case.
     * In a real system, this would involve checking session flags or database entries.
     *
     * @return true to indicate successful completion.
     */
    private boolean simulateSviewTetTingloregister() {
        // For demonstration, always return true.
        // In a real system, this would involve actual logic.
        return true;
    }

    /**
     * Simulates the completion of the "viewellacogiustifies" use case.
     * In a real system, this would involve checking session flags or database entries.
     *
     * @return true to indicate successful completion.
     */
    private boolean simulateViewellacogiustifies() {
        // For demonstration, always return true.
        // In a real system, this would involve actual logic.
        return true;
    }

    /**
     * Inserts a new justification into the system.
     * This method assumes all preconditions have already been checked and passed.
     *
     * @param absenceId The ID of the absence for which the justification is being provided.
     * @param justificationDate The date of the justification.
     * @return An Optional containing the newly created Justification object if successful,
     *         or an empty Optional if the insertion fails (e.g., due to SMOS server issues).
     */
    public Optional<Justification> insertJustification(String absenceId, LocalDate justificationDate) {
        System.out.println("\nAttempting to insert justification for absence ID: " + absenceId);

        // Generate a unique ID for the new justification
        String newJustificationId = "JUS" + (justificationStore.size() + 1);
        Justification newJustification = new Justification(newJustificationId, absenceId, justificationDate);

        // Simulate connection to SMOS server
        if (!simulateSMOSServerConnection()) {
            System.out.println("Postcondition Failed: Connection to SMOS server interrupted. Justification not saved.");
            return Optional.empty(); // Administrator interrupts operation / SMOS server interrupted
        }

        // Save the justification
        justificationStore.put(newJustificationId, newJustification);
        System.out.println("Postcondition Met: Justification data has been entered into the system.");
        System.out.println("Saved: " + newJustification);

        // Simulate returning to the registry screen
        simulateReturnToRegistryScreen();

        return Optional.of(newJustification);
    }

    /**
     * Simulates the connection and interaction with the SMOS server.
     *
     * @return true if the connection is successful, false if interrupted.
     */
    private boolean simulateSMOSServerConnection() {
        // For demonstration, randomly simulate a connection interruption.
        // In a real system, this would involve actual network calls and error handling.
        boolean connectionSuccessful = Math.random() > 0.1; // 10% chance of interruption
        if (connectionSuccessful) {
            System.out.println("Simulating SMOS server connection: Successful.");
        } else {
            System.out.println("Simulating SMOS server connection: Interrupted!");
        }
        return connectionSuccessful;
    }

    /**
     * Simulates the system returning to the registry screen.
     * In a real UI, this would involve navigating to a different view.
     */
    private void simulateReturnToRegistryScreen() {
        System.out.println("Postcondition Met: System returns to the registry screen.");
        // In a real application, this would trigger a UI navigation event.
    }

    /**
     * Retrieves a justification by its ID.
     *
     * @param justificationId The ID of the justification to retrieve.
     * @return An Optional containing the Justification if found, otherwise empty.
     */
    public Optional<Justification> getJustification(String justificationId) {
        return Optional.ofNullable(justificationStore.get(justificationId));
    }

    /**
     * Gets the current size of the justification store.
     *
     * @return The number of justifications currently stored.
     */
    public int getJustificationCount() {
        return justificationStore.size();
    }
}