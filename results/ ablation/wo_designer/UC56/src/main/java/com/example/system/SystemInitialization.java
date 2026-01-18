package com.example.system;

import com.example.gps.GPSException;
import com.example.gps.GPSSystem;
import com.example.gps.GPSPosition;
import com.example.tourist.Tourist;
import com.example.tourist.TouristSearch;

/**
 * The system initialization component that requests the tourist's position.
 */
public class SystemInitialization {
    private final GPSSystem gpsSystem;

    public SystemInitialization(GPSSystem gpsSystem) {
        this.gpsSystem = gpsSystem;
    }

    /**
     * Main method to get the current position of the tourist.
     * This blocks until the position is received or GPS fails.
     *
     * @param tourist the tourist whose position is needed
     * @param searchType the type of search the tourist began
     * @return the GPS position of the tourist, or null if not detectable
     */
    public GPSPosition getTouristPosition(Tourist tourist, TouristSearch searchType) {
        System.out.println("System initialization requires the data for the position.");
        
        GPSPosition position = null;
        try {
            // Step 2: GPS System calculates the position of the tourist.
            System.out.println("GPS System calculates the position of the tourist...");
            // Step 3: System initialization is on hold until the data of the position is received.
            position = gpsSystem.calculateCurrentPosition();
            System.out.println("System initialization receives the position of the tourist.");
        } catch (GPSException e) {
            System.out.println("Exit condition: " + e.getMessage());
        }
        return position;
    }

    /**
     * Alternative method with a timeout based on quality requirement:
     * "The GPS requirements into the transaction in more than 5 seconds."
     * Here we interpret that as: if GPS takes more than 5 seconds, we treat it as a failure.
     *
     * @param tourist the tourist
     * @param searchType the search type
     * @return the GPS position, or null on failure/timeout
     */
    public GPSPosition getTouristPositionWithTimeout(Tourist tourist, TouristSearch searchType) {
        System.out.println("System initialization requires the data for the position (with timeout).");
        
        // Use a separate thread to fetch GPS with timeout
        GPSPositionHolder holder = new GPSPositionHolder();
        Thread gpsThread = new Thread(() -> {
            try {
                holder.position = gpsSystem.calculateCurrentPosition();
            } catch (GPSException e) {
                holder.exception = e;
            }
        });
        gpsThread.start();
        
        try {
            // Wait for the GPS thread with a timeout of 5 seconds
            gpsThread.join(5000);
            if (gpsThread.isAlive()) {
                // Timeout occurred
                gpsThread.interrupt();
                System.out.println("GPS calculation took more than 5 seconds. Quality requirement violated.");
                return null;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        }
        
        if (holder.exception != null) {
            System.out.println("Exit condition: " + holder.exception.getMessage());
            return null;
        }
        System.out.println("System initialization receives the position of the tourist.");
        return holder.position;
    }
    
    private static class GPSPositionHolder {
        GPSPosition position;
        GPSException exception;
    }
}