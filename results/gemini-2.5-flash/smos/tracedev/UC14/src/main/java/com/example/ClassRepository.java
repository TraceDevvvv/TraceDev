package com.example;

import java.util.HashMap;
import java.util.Map;

// Concrete implementation of IClassRepository.
public class ClassRepository implements IClassRepository {
    // Simulates a database connection. In a real app, this would be a proper connection pool or ORM session.
    private Object databaseConnection;
    // Dependency on SMOSGateway for external system interaction (R15).
    private SMOSGateway smosGateway;

    // In-memory store for demonstration purposes.
    private Map<String, Class> classStore = new HashMap<>();

    /**
     * Constructor for ClassRepository.
     * @param databaseConnection A dummy object representing a database connection.
     * @param smosGateway The gateway to SMOS.
     */
    public ClassRepository(Object databaseConnection, SMOSGateway smosGateway) {
        this.databaseConnection = databaseConnection;
        this.smosGateway = smosGateway;
        System.out.println("ClassRepository initialized with database connection and SMOSGateway.");
    }

    /**
     * Saves a Class object to the repository and sends data to SMOS.
     * May throw SMOSConnectionException if SMOS communication fails (R15).
     *
     * @param clazz The Class object to save.
     * @throws SMOSConnectionException if the SMOS gateway encounters an error.
     */
    @Override
    public void save(Class clazz) throws SMOSConnectionException {
        System.out.println("ClassRepository: Saving Class " + clazz.getId() + " to database simulation.");
        // Simulate saving to a database
        classStore.put(clazz.getId(), clazz);
        System.out.println("ClassRepository: Class " + clazz.getId() + " saved internally.");

        // Note bottom of ClassRepository::save: May throw SMOSConnectionException (R15)
        // Delegate sending data to SMOSGateway
        smosGateway.sendDataToSMOS(clazz); // This call can throw SMOSConnectionException
        System.out.println("ClassRepository: Successfully communicated with SMOS Gateway.");
    }

    /**
     * Finds a Class object by its ID from the repository.
     *
     * @param id The ID of the class to find.
     * @return The Class object if found, otherwise null.
     */
    @Override
    public Class findById(String id) {
        System.out.println("ClassRepository: Searching for Class with ID: " + id);
        return classStore.get(id);
    }
}