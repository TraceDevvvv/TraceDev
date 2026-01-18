'''
Provides methods to simulate backend operations related to convention requests
and activation. This includes loading convention data, activating conventions,
and notifying activation. It also simulates potential ETOUR server connection issues.
'''
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * Provides methods to simulate backend operations related to convention requests
 * and activation. This includes loading convention data, activating conventions,
 * and notifying activation. It also simulates potential ETOUR server connection issues.
 */
public class ConventionService {
    // Simulates a database or external storage for conventions
    private Map<String, Convention> conventionDatabase;
    private Random random;
    /**
     * Initializes the ConventionService, populating a mock database with sample conventions.
     */
    public ConventionService() {
        conventionDatabase = new HashMap<>();
        random = new Random();
        // Sample data:
        // A convention pending activation for refreshment point "RP001"
        conventionDatabase.put("RP001", new Convention("C001", "Summer Deal 2023", "RP001", false, "PENDING_ACTIVATION"));
        // Another convention, already active
        conventionDatabase.put("RP002", new Convention("C002", "Winter Promo 2024", "RP002", true, "ACTIVE"));
        // An inactive convention
        conventionDatabase.put("RP003", new Convention("C003", "Spring Fest 2023", "RP003", false, "INACTIVE"));
    }
    /**
     * Simulates loading a convention request from a refreshment point.
     * This corresponds to step 2 of the use case.
     *
     * @param refreshmentPointId The ID of the refreshment point.
     * @return The Convention object if found and pending activation, otherwise null.
     * @throws InterruptedException If the simulated delay is interrupted.
     */
    public Convention loadConventionRequest(String refreshmentPointId) throws InterruptedException {
        System.out.println("Processing: Loading convention request for " + refreshmentPointId + "...");
        Thread.sleep(1500); // Simulate network/database delay
        Convention convention = conventionDatabase.get(refreshmentPointId);
        if (convention != null) {
            System.out.println("Convention found: " + convention.getName() + " with status: " + convention.getStatus());
            return convention;
        } else {
            System.out.println("No pending convention request found for " + refreshmentPointId);
            return null;
        }
    }
    /**
     * Simulates processing the activation of a convention.
     * This corresponds to step 6 of the use case.
     * It includes a random chance of simulating an ETOUR connection failure.
     *
     * @param convention The Convention object to activate.
     * @return The updated Convention object if activation is successful.
     * @throws ETOURConnectionException If there's an interruption in connection to the ETOUR server.
     * @throws InterruptedException If the simulated delay is interrupted.
     */
    public Convention activateConvention(Convention convention) throws ETOURConnectionException, InterruptedException {
        System.out.println("Processing: Activating convention " + convention.getName() + "...");
        Thread.sleep(2000); // Simulate external system processing delay
        // Simulate ETOUR server connection failure ~20% of the time
        if (random.nextInt(100) < 20) {
            System.err.println("Error: ETOUR server connection interrupted!");
            // Simulates ETOUR server interruption exit condition
            throw new ETOURConnectionException("Interruption of the connection to the server ETOUR.");
        }
        // If no ETOUR error, proceed with activation
        convention.setActive(true);
        convention.setStatus("ACTIVE");
        conventionDatabase.put(convention.getRefreshmentPointId(), convention); // Update in mock database
        System.out.println("Convention " + convention.getName() + " successfully activated.");
        return convention;
    }
    /**
     * Simulates notifying about the activation of a convention.
     * This corresponds to the exit condition: "The system shall notify the activation of the convention."
     *
     * @param convention The activated Convention object.
     */
    public void notifyActivation(Convention convention) {
        System.out.println("Notification: Convention '" + convention.getName() + "' (ID: " + convention.getId() + ") has been officially activated.");
        // In a real system, this would involve sending emails, SMS, or updating another system.
    }
}