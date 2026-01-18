package dataaccess;

import domain.RegistrationRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Concrete implementation of IRegistrationRepository for data access operations.
 * This implementation simulates database interaction with in-memory data and
 * can simulate network connection exceptions.
 */
public class RegistrationRepositoryImpl implements IRegistrationRepository {

    // A flag to simulate network connection issues for testing purposes.
    // Set to true to trigger NetworkConnectionException.
    public static boolean simulateNetworkError = false;

    /**
     * Finds and returns a list of pending registration requests.
     * Simulates fetching data from a database.
     *
     * @return A list of RegistrationRequest objects with 'pending' status.
     * @throws NetworkConnectionException if simulateNetworkError is true, mimicking a connection failure.
     */
    @Override
    public List<RegistrationRequest> findPendingRequests() throws NetworkConnectionException {
        System.out.println("[Repo] Attempting to find pending requests from DB.");

        if (simulateNetworkError) {
            // Simulate a network failure
            throw new NetworkConnectionException("Failed to connect to the database (simulated network error).");
        }

        // Simulate database query delay
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Simulate data from the database
        // Ensures up-to-date data is fetched directly. (Quality Requirement)
        return new ArrayList<>(Arrays.asList(
            new RegistrationRequest("REQ001", "Alice Smith", "pending", new Date(System.currentTimeMillis() - 86400000)), // 1 day ago
            new RegistrationRequest("REQ002", "Bob Johnson", "pending", new Date(System.currentTimeMillis() - 172800000)), // 2 days ago
            new RegistrationRequest("REQ003", "Charlie Brown", "approved", new Date(System.currentTimeMillis() - 259200000)) // 3 days ago, not pending
        ));
    }
}