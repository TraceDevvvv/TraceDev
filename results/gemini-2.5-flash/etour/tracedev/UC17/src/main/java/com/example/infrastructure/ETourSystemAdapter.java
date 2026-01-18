package com.example.infrastructure;

import com.example.domain.Banner;

import java.util.Random;

/**
 * Adapter for interacting with the external ETOUR system.
 * Part of the Infrastructure Layer.
 */
public class ETourSystemAdapter implements IETourService {

    private final Random random = new Random();

    /**
     * Notifies the ETOUR system about a change in a banner.
     * (Mock implementation)
     * @param banner The Banner object that has been changed.
     * @return True if the notification was successful, false otherwise.
     */
    @Override
    public boolean notifyBannerChange(Banner banner) {
        System.out.println("ETS: Notifying ETOUR system about banner change for ID: " + banner.getId());
        // Simulate network call and success/failure
        boolean success = random.nextBoolean(); // 50/50 chance of success
        if (success) {
            System.out.println("ETS: ETOUR notification for banner " + banner.getId() + " successful.");
        } else {
            System.err.println("ETS: ETOUR notification for banner " + banner.getId() + " FAILED.");
        }
        return success;
    }

    /**
     * Checks the connection status to the ETOUR system.
     * (Mock implementation)
     * @return True if the connection is active, false otherwise.
     */
    @Override
    public boolean checkConnection() {
        System.out.println("ETS: Checking connection to ETOUR system.");
        // Simulate connection status (e.g., sometimes it fails)
        boolean connected = random.nextBoolean(); // 50/50 chance of connection
        if (connected) {
            System.out.println("ETS: Connection to ETOUR successful.");
        } else {
            System.err.println("ETS: Connection to ETOUR FAILED.");
        }
        return connected;
    }
}