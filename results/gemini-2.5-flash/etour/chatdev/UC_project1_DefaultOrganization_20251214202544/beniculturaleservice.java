/*
This class simulates a backend service for managing Beni Culturali (cultural objects).
It provides methods to retrieve a list of cultural objects and delete a specific one.
It also includes logic to simulate network delays and connection interruptions,
emulating real-world scenarios.
*/
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
public class BeniCulturaleService {
    // In-memory list to simulate a database of cultural objects
    private static List<BeniCulturale> culturalObjects = new ArrayList<>();
    // Random object for simulating failures and delays
    private static final Random random = new Random();
    // Probability of a connection interruption (e.g., 20% chance)
    private static final double FAILURE_RATE = 0.20;
    // Simulated network delay in milliseconds
    private static final long MOCK_DELAY_MS = 1000;
    // Static block to initialize some sample cultural objects
    static {
        culturalObjects.add(new BeniCulturale("BC001", "Colosseo", "An ancient amphitheater in Rome."));
        culturalObjects.add(new BeniCulturale("BC002", "Fontana di Trevi", "A Baroque fountain in the Trevi district in Rome."));
        culturalObjects.add(new BeniCulturale("BC003", "Galleria degli Uffizi", "A prominent art museum located adjacent to the Piazza della Signoria in Florence."));
        culturalObjects.add(new BeniCulturale("BC004", "Duomo di Milano", "The Milan Cathedral, a magnificent Gothic cathedral."));
    }
    /**
     * Simulates fetching all cultural objects from the "database".
     * Introduces a mock delay and potential connection interruption.
     *
     * @return A list of all BeniCulturale objects.
     * @throws ConnectionInterruptionException If a simulated connection failure occurs.
     */
    public List<BeniCulturale> getAllBeniCulturali() throws ConnectionInterruptionException {
        simulateNetworkDelay();
        if (simulateConnectionFailure()) {
            throw new ConnectionInterruptionException("Failed to retrieve cultural objects: Connection to ETOUR server interrupted.");
        }
        // Return a copy to prevent external modifications of the internal list
        return new ArrayList<>(culturalObjects);
    }
    /**
     * Simulates deleting a cultural object by its ID from the "database".
     * Introduces a mock delay and potential connection interruption.
     *
     * @param id The ID of the BeniCulturale to delete.
     * @return true if the object was found and successfully deleted, false otherwise.
     * @throws ConnectionInterruptionException If a simulated connection failure occurs.
     */
    public boolean deleteBeniCulturale(String id) throws ConnectionInterruptionException {
        simulateNetworkDelay();
        if (simulateConnectionFailure()) {
            throw new ConnectionInterruptionException("Failed to delete cultural object " + id + ": Connection to ETOUR server interrupted.");
        }
        Iterator<BeniCulturale> iterator = culturalObjects.iterator();
        while (iterator.hasNext()) {
            BeniCulturale bene = iterator.next();
            if (bene.getId().equals(id)) {
                iterator.remove();
                return true; // Object found and removed
            }
        }
        return false; // Object not found
    }
    /**
     * Simulates a network delay to mimic real-world latency.
     */
    private void simulateNetworkDelay() {
        try {
            Thread.sleep(MOCK_DELAY_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
    }
    /**
     * Determines if a simulated connection failure should occur based on FAILURE_RATE.
     *
     * @return true if a failure is simulated, false otherwise.
     */
    private boolean simulateConnectionFailure() {
        return random.nextDouble() < FAILURE_RATE;
    }
}