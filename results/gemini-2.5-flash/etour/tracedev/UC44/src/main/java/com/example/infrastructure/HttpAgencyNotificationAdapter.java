package com.example.infrastructure;

import com.example.domain.AgencyNotificationPort;
import com.example.domain.Convention;
import com.example.domain.EtoURConnectionException;

import java.util.Random;

/**
 * Adapter for the AgencyNotificationPort, simulating HTTP communication with an external agency.
 * (May throw EtoURConnectionException - REQ-003)
 */
public class HttpAgencyNotificationAdapter implements AgencyNotificationPort {

    private final Random random = new Random();
    private boolean simulateConnectionError = false; // Configuration for testing error paths

    /**
     * Simulates sending a notification about a convention to an external agency via HTTP.
     * This implementation randomly simulates success, failure, or a connection error.
     *
     * @param convention The Convention entity to notify about.
     * @return true if the notification is logically successful, false otherwise (if no exception is thrown).
     * @throws EtoURConnectionException if a simulated connection error occurs (REQ-003).
     */
    @Override
    public boolean notifyConvention(Convention convention) throws EtoURConnectionException {
        System.out.println("Adapter (HTTP Agency): Notifying Agency for Convention ID: " + convention.getId().getValue());

        // Simulate network delay or external system processing
        try {
            Thread.sleep(random.nextInt(500) + 100); // 100-600 ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (simulateConnectionError) {
            System.err.println("Adapter (HTTP Agency): SIMULATED CONNECTION ERROR for Convention ID: " + convention.getId().getValue());
            throw new EtoURConnectionException("Simulated EtoUR connection failure while notifying for " + convention.getId().getValue());
        }

        // Simulate success or other failures (e.g., agency rejects data, but not a connection issue)
        // For simplicity, 80% chance of success, 20% of logical failure (which the service treats as error)
        if (random.nextDouble() < 0.8) {
            System.out.println("Adapter (HTTP Agency): Convention notification successful for Convention ID: " + convention.getId().getValue());
            return true;
        } else {
            System.err.println("Adapter (HTTP Agency): Convention notification failed (logical error, not connection) for Convention ID: " + convention.getId().getValue());
            // The sequence diagram implies throws EtoURConnectionException for system errors.
            // If the external system responds with a business error (not a connection error),
            // this adapter might return false or throw a different custom exception.
            // For now, if it doesn't throw EtoURConnectionException, it's a non-critical failure that 'notifyConvention' returns false for.
            return false;
        }
    }

    /**
     * Setter to control the simulation of connection errors for testing purposes.
     * @param simulateConnectionError If true, the next `notifyConvention` call will throw `EtoURConnectionException` and `handleEtoURConnectionError` in service will be called.
     */
    public void setSimulateConnectionError(boolean simulateConnectionError) {
        this.simulateConnectionError = simulateConnectionError;
        System.out.println("Adapter (HTTP Agency): Connection error simulation set to: " + simulateConnectionError);
    }
}