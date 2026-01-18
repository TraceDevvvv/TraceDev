package com.example.localization.service;

import com.example.localization.model.Location;
import com.example.localization.repository.ILocationRepository;
import com.example.localization.module.GpsSensorException; // Import for specific exception handling

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;

/**
 * Orchestrates localization logic, using a location repository to find the current position.
 * R3: Assumes 'A search HAS begun' as an entry condition for system interaction.
 */
public class LocalizationService {
    private final ILocationRepository _locationRepository;

    /**
     * Constructs a LocalizationService with a specific location repository.
     *
     * @param repository The repository to use for fetching location data.
     */
    public LocalizationService(ILocationRepository repository) {
        this._locationRepository = repository;
    }

    /**
     * Requests the current location from the underlying location repository.
     * This method immediately returns a Future, allowing the caller to await the result asynchronously.
     *
     * @return A Future that will eventually contain the Location data.
     */
    public Future<Location> requestCurrentLocation() {
        System.out.println("[Localization Service] requestCurrentLocation() called. Orchestrates localization logic.");
        // LS -> GLR: getPosition()
        Future<Location> futureLocation = _locationRepository.getPosition();
        // LS --> TouristApp: futureLocation : Future<Location>
        System.out.println("[Localization Service] Returning futureLocation to the caller.");
        return futureLocation;
    }

    /**
     * Processes a completed Future containing a Location. This method simulates
     * the Localization Service waiting for and handling the result of the asynchronous
     * location request.
     *
     * R9: GPS localization transaction must complete within 5 seconds.
     * This method enforces the timeout and handles potential errors.
     *
     * @param future The Future<Location> to process.
     */
    public void processCompletedFuture(Future<Location> future) {
        System.out.println("[Localization Service] processCompletedFuture() called. Waiting for position data (non-blockingly via Future).");
        try {
            // Simulate waiting for the future to complete with a timeout of 5 seconds (R9).
            // In a real application, this would typically be handled non-blockingly using callbacks
            // or reactive patterns, but for a synchronous demonstration of result handling, get() is used.
            Location location = future.get(5, TimeUnit.SECONDS); // Blocks for up to 5 seconds
            // Alt: System has received the tourist's position
            System.out.println("[Localization Service] System has received the tourist's position: " + location);
            // LS --> TouristApp: Location(lat, lon, timestamp) - represented by this print
        } catch (TimeoutException e) {
            // Alt: Tourist's position is not detectable by GPS (due to timeout)
            System.err.println("[Localization Service] Error: Localization timed out after 5 seconds. Position not detectable.");
            // LS --> TouristApp: Error: Position not detectable - represented by this print
            future.cancel(true); // Attempt to interrupt the underlying task if it's still running
        } catch (InterruptedException e) {
            System.err.println("[Localization Service] Error: Location request interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore interrupt status
            // LS --> TouristApp: Error: Position not detectable - represented by this print
        } catch (ExecutionException e) {
            // This catches exceptions thrown by the underlying task (e.g., GpsSensorException)
            if (e.getCause() instanceof GpsSensorException) {
                // Alt: Tourist's position is not detectable by GPS
                System.err.println("[Localization Service] Error: Position not detectable by GPS: " + e.getCause().getMessage());
            } else {
                System.err.println("[Localization Service] Error: An unexpected issue occurred during localization: " + e.getCause().getMessage());
            }
            // LS --> TouristApp: Error: Position not detectable - represented by this print
        }
    }
}