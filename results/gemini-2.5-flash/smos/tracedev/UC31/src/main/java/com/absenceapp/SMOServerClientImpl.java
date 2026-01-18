package com.absenceapp;

/**
 * Concrete implementation of ISMOServerClient.
 * Simulates communication with an external SMO Server.
 */
public class SMOServerClientImpl implements ISMOServerClient {

    private boolean shouldThrowException = false;

    /**
     * Sends modified data to the SMO Server.
     *
     * @param data The data object to send (e.g., Absence or an ID).
     * @throws ConnectionException if there's a problem connecting to the SMO Server.
     */
    @Override
    public void sendModifiedData(Object data) throws ConnectionException {
        System.out.println("ISMOServerClient: Sending data to SMO Server: " + data);
        if (shouldThrowException) {
            System.err.println("ISMOServerClient: Simulating connection error!");
            throw new ConnectionException("Simulated connection failure to SMO Server.");
        }
        // Simulate network delay
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("ISMOServerClient: Data sent successfully.");
    }

    /**
     * Sets a flag to control whether `sendModifiedData` should throw a ConnectionException.
     *
     * @param shouldThrowException If true, the next call to sendModifiedData will throw an exception.
     */
    public void setShouldThrowException(boolean shouldThrowException) {
        this.shouldThrowException = shouldThrowException;
    }
}