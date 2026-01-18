package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Concrete implementation of ITagRepository.
 * This class simulates a data store using an in-memory map.
 * It can also simulate connection errors for testing purposes.
 */
public class TagRepository implements ITagRepository {

    // Simulate a data store using a Map for quick lookup and storage
    private final Map<String, Tag> tags;

    // Flag to simulate a connection error to the database
    private boolean simulateConnectionError = false;

    /**
     * Constructs a TagRepository and initializes the simulated data store with some sample tags.
     */
    public TagRepository() {
        this.tags = new ConcurrentHashMap<>();
        // Populate with some initial data
        tags.put("1", new Tag("1", "Java"));
        tags.put("2", new Tag("2", "Spring Boot"));
        tags.put("3", new Tag("3", "Microserv"));
        tags.put("4", new Tag("4", "UML"));
        tags.put("5", new Tag("5", "Design Patterns"));
    }

    /**
     * Sets the flag to simulate a connection error.
     * If true, subsequent method calls might throw ConnectionException.
     * @param simulateConnectionError True to simulate an error, false otherwise.
     */
    public void setSimulateConnectionError(boolean simulateConnectionError) {
        this.simulateConnectionError = simulateConnectionError;
        System.out.println("TagRepository: Connection error simulation set to " + simulateConnectionError);
    }

    /**
     * Retrieves all existing tags from the simulated data store.
     * @return A list of all tags.
     * @throws ConnectionException if connection to the database fails (simulated).
     */
    @Override
    public List<Tag> findAll() throws ConnectionException {
        if (simulateConnectionError) {
            System.err.println("TagRepository: Simulating ConnectionException during findAll().");
            throw new ConnectionException("Failed to connect to Tag Database during findAll.");
        }
        System.out.println("TagRepository: Fetching all tags. Found " + tags.size() + " tags.");
        return new ArrayList<>(tags.values());
    }

    /**
     * Deletes a list of tags identified by their IDs from the simulated data store.
     * @param tagIds A list of tag IDs to be deleted.
     * @throws ConnectionException if connection to the database fails (simulated).
     */
    @Override
    public void deleteByIds(List<String> tagIds) throws ConnectionException {
        if (simulateConnectionError) {
            System.err.println("TagRepository: Simulating ConnectionException during deleteByIds().");
            throw new ConnectionException("Failed to connect to Tag Database during deleteByIds.");
        }
        System.out.println("TagRepository: Attempting to delete tags with IDs: " + tagIds);
        tagIds.forEach(tags::remove);
        System.out.println("TagRepository: Deleted " + tagIds.size() + " tags. Remaining tags: " + tags.size());
    }
}