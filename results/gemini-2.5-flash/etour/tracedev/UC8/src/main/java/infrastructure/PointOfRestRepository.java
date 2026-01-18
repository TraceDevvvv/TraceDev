package infrastructure;

import domain.IPointOfRestRepository;
import domain.PointOfRest;

import java.util.HashMap;
import java.util.Map;

/**
 * Infrastructure Layer: Concrete implementation of IPointOfRestRepository.
 * Handles actual data persistence using a simulated database connection.
 */
public class PointOfRestRepository implements IPointOfRestRepository {
    private DatabaseConnection databaseConnection;
    // Simulate a database table in-memory
    private static final Map<String, PointOfRest> inMemoryDB = new HashMap<>();

    // Flag to simulate connection interruptions for sequence diagram scenario
    private boolean simulateConnectionInterruption = false;

    // Static initializer block to populate some initial data
    static {
        inMemoryDB.put("POR001", new PointOfRest("POR001", "Cafe Central", "101 Main St", "A popular coffee shop.", "Active"));
        inMemoryDB.put("POR002", new PointOfRest("POR002", "Art Gallery XYZ", "202 Art Alley", "Contemporary art space.", "Active"));
        inMemoryDB.put("POR003", new PointOfRest("POR003", "Parkview Hotel", "303 Park Rd", "Luxury hotel with a view.", "Inactive"));
        inMemoryDB.put("POR004", new PointOfRest("POR004", "Riverside Restaurant", "404 Riverfront", "Fine dining with river view.", "Active"));
    }

    // Constructor for dependency injection
    public PointOfRestRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    /**
     * Setter for simulating connection interruptions.
     * @param simulateConnectionInterruption true to simulate, false otherwise.
     */
    public void setSimulateConnectionInterruption(boolean simulateConnectionInterruption) {
        this.simulateConnectionInterruption = simulateConnectionInterruption;
    }

    @Override
    public PointOfRest findById(String id) throws ConnectionException {
        System.out.println("[Repository] Finding PointOfRest by ID: " + id);
        if (simulateConnectionInterruption) {
            System.err.println("[Repository] Simulating DB connection interruption during findById.");
            throw new ConnectionException("Simulated ETOUR server connection lost during find operation.");
        }

        // Interaction: Repository -> DB: SELECT * FROM PointOfRest WHERE id = pointId
        // Simulate database query by calling executeQuery, even if ResultSet is dummy
        System.out.println("[Repository] Sending SELECT query to DB for ID: " + id);
        databaseConnection.executeQuery("SELECT * FROM PointOfRest WHERE id = '" + id + "'");
        
        // For actual data retrieval in this simulation, we use the in-memory map.
        PointOfRest result = inMemoryDB.get(id);

        if (result != null) {
            System.out.println("[Repository] Found PointOfRest: " + result.getName());
            // In a real scenario, map rowData from ResultSet to PointOfRest entity
            // Interaction: DB --> Repository : rowData
        } else {
            System.out.println("[Repository] PointOfRest with ID " + id + " not found in DB simulation.");
        }
        // Interaction: Repository --> IRepository : PointOfRest entity
        return result;
    }

    @Override
    public void save(PointOfRest point) throws ConnectionException {
        System.out.println("[Repository] Saving PointOfRest ID: " + point.getId());
        if (simulateConnectionInterruption) {
            System.err.println("[Repository] Simulating DB connection interruption during save.");
            throw new ConnectionException("Simulated ETOUR server connection lost during save operation.");
        }

        // Interaction: Repository -> DB: UPDATE PointOfRest SET ... WHERE id = point.getId()
        // Simulate database update/insert
        String updateQuery = String.format(
            "UPDATE PointOfRest SET name='%s', address='%s', description='%s', status='%s' WHERE id='%s'",
            point.getName(), point.getAddress(), point.getDescription(), point.getStatus(), point.getId()
        );
        
        int affectedRows = databaseConnection.executeUpdate(updateQuery); // Fix: executeUpdate returns int

        if (affectedRows > 0) { // Fix: Check if any rows were affected
            inMemoryDB.put(point.getId(), point); // Update/insert into in-memory map
            System.out.println("[Repository] PointOfRest ID " + point.getId() + " saved/updated in DB simulation.");
            // Interaction: DB --> Repository : success
        } else {
            System.err.println("[Repository] Failed to save PointOfRest ID " + point.getId() + " in DB simulation (affected rows: 0).");
            throw new RuntimeException("Database update failed for ID " + point.getId() + "."); // General DB error
        }
        // Interaction: Repository --> IRepository : success (implicit via no exception)
    }
}