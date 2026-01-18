import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
 * DOCSTRING:
 * This class acts as a Data Access Object (DAO) for Turista objects.
 * It simulates interaction with a data source (e.g., a database) by
 * storing Turista objects in an in-memory map. It provides methods
 * for retrieving all tourists, retrieving a tourist by ID, and updating
 * a tourist's information. This class is designed to be a singleton
 * or to be injected as a dependency.
 *
 * In a real application, this would interact with a database, web service, etc.
 *
 * MODIFICATION: Added a flag to simulate ETOUR server connection status
 *               and integrated it into the update operation.
 */
class TuristaDao {
    // In-memory data store for demonstration purposes
    private Map<String, Turista> turistiDB;
    // New field to simulate ETOUR connection status, true by default
    private boolean etourConnected = true;
    /**
     * Constructs a TuristaDao and initializes it with some dummy data.
     * Simulates fetching initial data from a database.
     */
    public TuristaDao() {
        turistiDB = new HashMap<>();
        // Populate with some dummy tourist data
        turistiDB.put("T001", new Turista("T001", "Mario", "Rossi", "mario.rossi@example.com", "3331234567"));
        turistiDB.put("T002", new Turista("T002", "Luigi", "Verdi", "luigi.verdi@example.com", "3337654321"));
        turistiDB.put("T003", new Turista("T003", "Anna", "Bianchi", "anna.bianchi@example.com", "3339876543"));
    }
    /**
     * Sets the simulated connection status to the ETOUR server.
     * This method allows manually toggling the connection status for testing.
     * @param status true if connected, false if disconnected.
     */
    public void setEtourConnectionStatus(boolean status) {
        this.etourConnected = status;
        System.out.println("ETOUR Connection Status: " + (status ? "Connected" : "Disconnected")); // Logging for simulation
    }
    /**
     * Retrieves a list of all available Turista objects.
     *
     * @return A List of Turista objects.
     */
    public List<Turista> getTuristi() {
        return new ArrayList<>(turistiDB.values());
    }
    /**
     * Retrieves a specific Turista object by its ID.
     *
     * @param id The ID of the tourist to retrieve.
     * @return The Turista object if found, otherwise null.
     */
    public Turista getTuristaById(String id) {
        return turistiDB.get(id);
    }
    /**
     * Updates an existing Turista object in the data store.
     * If a tourist with the given ID does not exist, it will not be updated.
     * Throws a RuntimeException to simulate a connection interruption to the ETOUR server.
     *
     * @param turista The Turista object containing the updated information.
     * @return true if the update was successful, false otherwise (e.g., tourist not found).
     * @throws RuntimeException if the simulated ETOUR connection is interrupted.
     */
    public boolean updateTurista(Turista turista) {
        if (!etourConnected) {
            // Simulate connection loss by throwing a runtime exception.
            // In a production system, a custom checked exception would be preferred for better handling.
            throw new RuntimeException("Connessione al server ETOUR interrotta.");
        }
        if (turistiDB.containsKey(turista.getId())) {
            turistiDB.put(turista.getId(), turista); // Overwrites the old entry with the new one
            System.out.println("Turista updated: " + turista.getId()); // Logging for simulation
            return true;
        }
        System.out.println("Turista with ID " + turista.getId() + " not found for update."); // Logging
        return false;
    }
}