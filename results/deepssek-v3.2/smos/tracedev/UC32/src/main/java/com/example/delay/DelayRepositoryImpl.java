package com.example.delay;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of DelayRepository with an in-memory store and server gateway integration.
 */
public class DelayRepositoryImpl implements DelayRepository {
    private SMOSServerGateway serverGateway;
    // In-memory store for simplicity; in real system would be a database.
    private Map<Date, Delay> delayStore = new HashMap<>();

    /**
     * Constructor.
     * @param serverGateway The gateway to SMOS server.
     */
    public DelayRepositoryImpl(SMOSServerGateway serverGateway) {
        this.serverGateway = serverGateway;
    }

    @Override
    public Delay findByDate(Date date) {
        // Implement return of Delay data (sequence diagram message m3)
        Delay delay = delayStore.get(date);
        if (delay == null) {
            // In a real system, might return null or default
            return null;
        }
        return delay;
    }

    @Override
    public void save(Delay delay) {
        delayStore.put(delay.getDate(), delay);
        // In a real system, we would also persist to server if connected.
        syncWithServer(delay);
    }

    @Override
    public void updateDelay(Delay delay) {
        delayStore.put(delay.getDate(), delay);
        syncWithServer(delay);
        // Implement update delay data (sequence diagram message m12)
        System.out.println("Repository updating delay data for date: " + delay.getDate());
    }

    /**
     * Syncs delay data with SMOS server if connected.
     * Implements sequence diagram interactions: isConnected() and sendDelayData().
     * @param delay The delay to sync.
     */
    private void syncWithServer(Delay delay) {
        if (serverGateway.isConnected()) {
            try {
                boolean success = serverGateway.sendDelayData(delay);
                if (!success) {
                    System.err.println("Failed to send delay data to server.");
                } else {
                    // Handle success response (sequence diagram message m16)
                    System.out.println("SMOS server returned success response for delay update.");
                }
            } catch (ConnectionException e) {
                System.err.println("Connection error while sending delay: " + e.getMessage());
                // Handle connection interrupted (sequence diagram message m27)
                System.out.println("SMOS connection interrupted.");
                // The exception is propagated (sequence diagram message m28)
                throw new RuntimeException("Repository exception due to connection loss", e);
            }
        } else {
            System.out.println("Server not connected; delay stored locally.");
        }
    }

    /**
     * Checks connection status (added for explicit check per sequence diagram).
     * @return true if server is connected.
     */
    public boolean checkConnection() {
        return serverGateway.isConnected();
    }
}