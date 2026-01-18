package com.example;

// Interface representing the contract for class data access operations.
public interface IClassRepository {

    /**
     * Saves a Class object to the repository.
     *
     * @param clazz The Class object to save.
     * @throws SMOSConnectionException if there's an issue connecting to SMOS (as per R15).
     */
    void save(Class clazz) throws SMOSConnectionException;

    /**
     * Finds a Class object by its ID.
     *
     * @param id The ID of the class to find.
     * @return The Class object if found, otherwise null.
     */
    Class findById(String id);
}