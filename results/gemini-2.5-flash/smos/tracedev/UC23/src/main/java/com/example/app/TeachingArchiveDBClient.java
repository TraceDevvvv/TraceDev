package com.example.app;

/**
 * TeachingArchiveDBClient simulates interaction with a database for teaching archive operations.
 * It handles low-level insertion and querying of teaching records.
 */
public class TeachingArchiveDBClient {

    // A flag to simulate a database connection error for testing purposes.
    // Set to true to simulate a connection error during insert.
    private boolean simulateConnectionErrorOnInsert = false;

    // A simple in-memory store to simulate database records.
    // In a real application, this would interact with a persistent database.
    private java.util.Map<String, TeachingData> teachingRecords = new java.util.HashMap<>();

    /**
     * Sets a flag to simulate a database connection error during the next insert operation.
     *
     * @param simulate true to simulate an error, false otherwise.
     */
    public void setSimulateConnectionErrorOnInsert(boolean simulate) {
        this.simulateConnectionErrorOnInsert = simulate;
        if (simulate) {
            System.out.println("[DBClient] Simulating connection error for next insert operation.");
        } else {
            System.out.println("[DBClient] Not simulating connection error for next insert operation.");
        }
    }

    /**
     * Inserts a new teaching record into the archive.
     *
     * @param id   The unique ID of the teaching.
     * @param name The name of the teaching.
     * @throws ConnectionException if a simulated database connection error occurs.
     */
    public void insertTeachingRecord(String id, String name) {
        System.out.println("[DBClient] Attempting to insert record: ID='" + id + "', Name='" + name + "'");

        // Sequence Diagram: alt Database connection interrupted (SMOS server error)
        if (simulateConnectionErrorOnInsert) {
            // Reset the flag after simulating the error, so subsequent operations might succeed.
            simulateConnectionErrorOnInsert = false;
            throw new ConnectionException("Simulated database connection interruption to SMOS server.");
        }

        // Simulate actual database insertion
        teachingRecords.put(id, new TeachingData(name));
        System.out.println("[DBClient] Record inserted successfully for ID: " + id);
        // Sequence Diagram: DBClient -> DB: SQL INSERT INTO Teachings (id, name) VALUES (...)
    }

    /**
     * Queries the teaching archive by name.
     *
     * @param name The name of the teaching to query.
     * @return TeachingData if found, null otherwise.
     * @throws ConnectionException if a simulated database connection error occurs.
     */
    public TeachingData queryTeachingByName(String name) {
        System.out.println("[DBClient] Querying teaching by name: '" + name + "'");
        // Simulate database query.
        // In a real DB, this would involve SQL 'SELECT * FROM Teachings WHERE name = ?'
        // For our in-memory map, we iterate to find by name (assuming name is not the key).
        for (TeachingData data : teachingRecords.values()) {
            if (data.name.equalsIgnoreCase(name)) {
                System.out.println("[DBClient] Found existing teaching: " + data.name);
                return data;
            }
        }
        System.out.println("[DBClient] No teaching found with name: " + name);
        return null;
    }
}