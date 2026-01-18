package com.example.app;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of IClassRepository for data access.
 * This class corresponds to the 'ClassRepositoryImpl' class in the UML Class Diagram.
 * It simulates database interaction and checks connection status using SMOSConnectionManager (R12).
 */
public class ClassRepositoryImpl implements IClassRepository {

    private SMOSConnectionManager connectionManager;
    // Simulating a database with a Map for demonstration purposes
    private Map<String, Class> database;

    /**
     * Constructs a new ClassRepositoryImpl.
     *
     * @param connectionManager The SMOSConnectionManager to check connectivity (R12).
     */
    public ClassRepositoryImpl(SMOSConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        this.database = new HashMap<>();
        // Populate with some sample data
        database.put("CLASS001", new Class("CLASS001", "Math 101", "Room A", "2023-2024"));
        database.put("CLASS002", new Class("CLASS002", "Science 202", "Lab B", "2023-2024"));
        database.put("CLASS003", new Class("CLASS003", "History 303", "Auditorium", "2022-2023"));
    }

    /**
     * Finds a Class by its unique identifier.
     * This method simulates querying a database and handles connection errors.
     *
     * @param classId The ID of the class to find.
     * @return The Class object if found.
     * @throws ConnectionException If there is a problem connecting to the data source (R12).
     */
    @Override
    public Class findById(String classId) throws ConnectionException {
        System.out.println("Repository: findById(" + classId + ") - Checking connection via SMOSConnectionManager.");
        // Sequence Diagram: Repository -> SMOSConnectionManager: isConnected()
        if (!connectionManager.isConnected()) {
            // Sequence Diagram: DB --> Repository: ConnectionError (simulated)
            System.out.println("Repository: SMOS connection interrupted. Throwing ConnectionException.");
            throw new ConnectionException("Database connection to SMOS server interrupted.");
        }

        // Simulate database query
        System.out.println("Repository: Querying database for Class with ID: " + classId);
        // Sequence Diagram: Repository -> DB: SELECT * FROM Class WHERE id = classId
        Class classObject = database.get(classId);

        if (classObject == null) {
            System.out.println("Repository: Class with ID " + classId + " not found.");
            // Assuming the system should return null if not found, or throw a specific 'NotFoundException'
            // For now, it will return null, and the service layer might handle it.
        } else {
            System.out.println("Repository: Found classObject: " + classObject.getName());
        }
        // Sequence Diagram: DB --> Repository: classObject (or null if not found)
        return classObject;
    }
}