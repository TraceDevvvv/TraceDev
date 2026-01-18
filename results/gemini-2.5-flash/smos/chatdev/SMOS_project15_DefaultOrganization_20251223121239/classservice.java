'''
ClassService.java
A simulated service layer responsible for fetching class details.
In a real application, this would interact with a database or a remote server (e.g., SMOS server).
For this exercise, it provides simulated class details to allow selection and retrieval based on ID.
It also includes logic to simulate the "SMOS server interrupted" postcondition.
'''
package com.chatdev.smos.service;
import com.chatdev.smos.model.ClassDetails;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
public class ClassService {
    /**
     * A simulated in-memory database or repository storing various class details.
     * Keys are unique class identifiers (class IDs), and values are ClassDetails objects.
     */
    private Map<String, ClassDetails> classData;
    /**
     * A flag to simulate the connection status to the SMOS server.
     * {@code true} if connected, {@code false} if interrupted.
     */
    private boolean isSMOSConnected;
    /**
     * Constructs a new ClassService instance and initializes it with
     * some hardcoded dummy class data for simulation purposes.
     * Initializes the simulated SMOS connection as active.
     */
    public ClassService() {
        // Initialize with some dummy data to simulate available classes
        classData = new HashMap<>();
        classData.put("CLASS_5B_2023", new ClassDetails("Class 5B", "Room 205, Main Campus", "2023-2024"));
        classData.put("CLASS_6A_2023", new ClassDetails("Class 6A", "Room 301, Annex Building", "2023-2024"));
        classData.put("CLASS_4C_2024", new ClassDetails("Class 4C", "Library Hall", "2024-2025"));
        // Initialize the simulated connection state
        isSMOSConnected = true;
    }
    /**
     * Retrieves a set of all available class identifiers (keys) from the simulated data store.
     * This method simulates fetching a list of classes that could be displayed for user selection.
     * This operation is assumed to be local or not dependent on the SMOS connection state for simplicity.
     * @return A {@code Set} of {@code String} representing the available class IDs.
     */
    public Set<String> getAllClassIds() {
        return classData.keySet();
    }
    /**
     * Simulates fetching details for a specific class from a backend system.
     * It uses the provided {@code classId} to look up and return details
     * from a simulated data store.
     * If the SMOS connection is simulated as interrupted, it will not attempt to fetch data.
     *
     * @param classId The identifier of the class to retrieve details for.
     * @return A {@code ClassDetails} object if a class with the given ID is found and connection is active,
     *         or {@code null} if no class with that ID exists or the SMOS connection is interrupted.
     */
    public ClassDetails getClassDetails(String classId) {
        // Check if the SMOS connection is active.
        if (!isSMOSConnected) {
            System.err.println("ClassService: Cannot retrieve details. SMOS server connection is interrupted.");
            return null; // Return null as connection is interrupted
        }
        // Simulate a delay for network request, mimicking real-world latency
        try {
            Thread.sleep(500); // Simulate 0.5 second network latency
        } catch (InterruptedException e) {
            // Restore the interrupted status
            Thread.currentThread().interrupt();
            System.err.println("ClassService: Interrupted during simulation of network delay.");
            return null; // Return null if interrupted unexpectedly
        }
        // Retrieve from simulated data based on classId
        ClassDetails details = classData.get(classId);
        if (details != null) {
            System.out.println("ClassService: Successfully retrieved details for class ID: " + classId);
        } else {
            System.out.println("ClassService: No details found for class ID: " + classId);
        }
        return details;
    }
    /**
     * Simulates the interruption of connection to the SMOS server.
     * This method fulfills the postcondition of the use case by setting an internal flag
     * to indicate that the SMOS connection is no longer active.
     * In a real application, this might involve closing a database connection pool,
     * invalidating a session token, or disconnecting from a messaging queue.
     */
    public void interruptSMOSConnection() {
        isSMOSConnected = false; // Set the connection status to interrupted
        System.out.println("ClassService: Connection to the SMOS server interrupted.");
    }
    /**
     * Simulates re-establishing the connection to the SMOS server.
     * (Not part of the original use case, but useful for a complete simulation)
     */
    public void reconnectSMOS() {
        isSMOSConnected = true;
        System.out.println("ClassService: Connection to the SMOS server re-established.");
    }
}