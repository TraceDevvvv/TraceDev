'''
Simulates a backend service for managing search tags.
It provides functionalities to retrieve and delete tags,
and includes a mechanism to simulate server connection failures
for testing purposes.
'''
package com.chatdev.tagmanager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;
public class TagManager {
    // A Set to store unique available tags. Using Set ensures no duplicate tags.
    private Set<String> availableTags;
    // A flag to control whether to simulate a connection failure during tag deletion.
    private boolean simulateConnectionFailure = false;
    /**
     * Constructor for TagManager. Initializes with some sample tags.
     */
    public TagManager() {
        this.availableTags = new HashSet<>();
        initializeTags(); // Populate with initial tags
    }
    /**
     * Initializes the manager with a set of predefined tags.
     * This simulates loading initial data into the system.
     */
    private void initializeTags() {
        availableTags.add("Java");
        availableTags.add("Programming");
        availableTags.add("Software Development");
        availableTags.add("AI");
        availableTags.add("Machine Learning");
        availableTags.add("Cloud Computing");
        availableTags.add("Database");
        availableTags.add("Web Development");
        availableTags.add("Mobile Development");
        availableTags.add("DevOps");
    }
    /**
     * Retrieves all currently available tags in a sorted list.
     * This simulates fetching tags from a database or remote server.
     *
     * @return A sorted list of all available tags.
     */
    public List<String> getAllTags() {
        List<String> tags = new ArrayList<>(availableTags);
        Collections.sort(tags); // Sort for consistent display in the UI
        return tags;
    }
    /**
     * Deletes a list of specified tags from the system.
     * This method simulates a potential server connection failure or a successful deletion
     * with a simulated network delay.
     *
     * @param tagsToDelete A list of tags to be deleted.
     * @return true if deletion was successful, false if a simulated connection failure occurred.
     */
    public boolean deleteTags(List<String> tagsToDelete) {
        // Check if simulation of connection failure is active
        if (simulateConnectionFailure) {
            // Simulate a delay before "failing" to mimic network latency prior to an error.
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1500)); // Delay between 0.5 to 1.5 seconds
            } catch (InterruptedException e) {
                // Restore the interrupted status
                Thread.currentThread().interrupt();
            }
            return false; // Indicate simulated connection failure (ETOUR)
        }
        // If no tags are provided for deletion, consider it a successful no-op.
        if (tagsToDelete == null || tagsToDelete.isEmpty()) {
            return true;
        }
        // Simulate a successful deletion with a slight delay to mimic network latency.
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 500)); // Delay between 0.1 to 0.5 seconds
        } catch (InterruptedException e) {
            // Restore the interrupted status
            Thread.currentThread().interrupt();
        }
        // Perform the actual deletion from the in-memory set.
        availableTags.removeAll(tagsToDelete);
        return true; // Indicate successful deletion
    }
    /**
     * Sets the simulation state for connection failure.
     * This allows the client to toggle the "ETOUR" scenario for testing.
     *
     * @param simulate true to simulate failures, false otherwise.
     */
    public void setSimulateConnectionFailure(boolean simulate) {
        this.simulateConnectionFailure = simulate;
    }
    /**
     * Checks if connection failure simulation is currently active.
     *
     * @return true if simulating connection failure, false otherwise.
     */
    public boolean isSimulateConnectionFailure() {
        return simulateConnectionFailure;
    }
}