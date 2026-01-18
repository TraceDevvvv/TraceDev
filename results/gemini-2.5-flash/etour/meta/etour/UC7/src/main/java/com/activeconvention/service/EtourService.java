package com.activeconvention.service;

import com.activeconvention.model.Convention;
import com.activeconvention.util.EtourConnectionException;
import com.activeconvention.util.Logger;

import java.util.Random;

/**
 * EtourService class for simulating interaction with the ETOUR server.
 * This service is responsible for sending activation requests to an external
 * ETOUR system and handling potential connection interruptions.
 */
public class EtourService {

    private static final double CONNECTION_FAILURE_RATE = 0.2; // 20% chance of connection failure
    private Random random = new Random();

    /**
     * Constructor for EtourService.
     */
    public EtourService() {
        Logger.logInfo("EtourService initialized.");
    }

    /**
     * Simulates sending an activation request for a convention to the ETOUR server.
     * This method can simulate connection interruptions.
     *
     * @param convention The Convention object to be activated.
     * @return true if the activation request is successfully processed by ETOUR, false otherwise.
     * @throws EtourConnectionException if there is a simulated interruption in the connection to the ETOUR server.
     */
    public boolean sendActivationRequest(Convention convention) throws EtourConnectionException {
        Logger.logInfo("Simulating sending activation request for convention " + convention.getId() + " to ETOUR server.");

        // Simulate network latency
        try {
            Thread.sleep(random.nextInt(500) + 200); // Simulate 200-700ms latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logger.logError("ETOUR service simulation interrupted.", e);
            throw new EtourConnectionException("ETOUR service simulation interrupted.", e);
        }

        // Simulate ETOUR server connection interruption
        if (random.nextDouble() < CONNECTION_FAILURE_RATE) {
            Logger.logError("Simulated ETOUR server connection interruption for convention: " + convention.getId(), null);
            throw new EtourConnectionException("Simulated ETOUR server connection lost during activation for convention " + convention.getId());
        }

        // Simulate ETOUR server processing success/failure (beyond connection)
        // For simplicity, we'll assume it always succeeds if connection is established.
        // In a real scenario, ETOUR might return a specific status code.
        Logger.logInfo("ETOUR server successfully processed activation request for convention: " + convention.getId());
        return true;
    }
}