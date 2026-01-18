package com.example.controller;

import com.example.model.Tourist;
import com.example.model.Position;
import com.example.model.PositionResult;
import com.example.model.PositionResultType;
import com.example.repository.GPSRepository;

/**
 * Use case controller for tourist localization.
 */
public class UseCaseController {
    private GPSRepository gpsRepository;
    private long timeoutDuration;
    private int maxRetries;

    public UseCaseController(GPSRepository repository) {
        this.gpsRepository = repository;
        this.timeoutDuration = 5000; // default 5 seconds
        this.maxRetries = 3; // default retries
    }

    /**
     * Initializes localization for a tourist.
     * Returns a PositionResult indicating success or failure.
     */
    public PositionResult initializeTouristLocalization(Tourist tourist) {
        System.out.println("Initializing localization for tourist: " + tourist.getName());
        Position position = requestGPSPosition();
        if (position != null && position.isValid()) {
            System.out.println("Localization successful for tourist: " + tourist.getName());
            return createPositionResult(PositionResultType.SUCCESS, position);
        } else if (position == null) {
            // null indicates either timeout or not detectable after retries
            if (gpsRepository.isAvailable()) {
                System.out.println("GPS position not detectable after retries.");
                return createPositionResult(PositionResultType.NOT_DETECTABLE, null, "GPS unavailable");
            } else {
                System.out.println("GPS transaction timeout.");
                return createPositionResult(PositionResultType.GPS_TIMEOUT, null, "Timeout exceeded");
            }
        } else {
            // position exists but invalid
            System.out.println("GPS position invalid.");
            return new PositionResult(PositionResultType.ERROR, null, "Invalid position data");
        }
    }

    /**
     * Requests GPS position with timeout and retry logic as per sequence diagram.
     */
    protected Position requestGPSPosition() {
        // Configure repository with timeout and retries
        gpsRepository.setTimeout(timeoutDuration);
        gpsRepository.setMaxRetries(maxRetries);
        Position position = gpsRepository.getCurrentPosition();
        if (position != null && position.isValid()) {
            System.out.println("Valid Position returned from GPS Repository.");
        }
        return position;
    }
    
    /**
     * Handles unavailable GPS scenario.
     */
    public void handleUnavailableGPS() {
        System.out.println("Controller handling unavailable GPS.");
    }
    
    /**
     * Creates a PositionResult with SUCCESS type and position.
     */
    public PositionResult createPositionResult(PositionResultType resultType, Position position) {
        return new PositionResult(resultType, position);
    }
    
    /**
     * Creates a PositionResult with NOT_DETECTABLE type, null position, and error message.
     */
    public PositionResult createPositionResult(PositionResultType resultType, Position position, String errorMessage) {
        return new PositionResult(resultType, position, errorMessage);
    }

    public void setTimeoutDuration(long duration) {
        this.timeoutDuration = duration;
    }

    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }
}