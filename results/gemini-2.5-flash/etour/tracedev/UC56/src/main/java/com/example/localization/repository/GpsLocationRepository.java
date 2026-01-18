package com.example.localization.repository;

import com.example.localization.model.Location;
import com.example.localization.module.GpsSensorException;
import com.example.localization.module.IGpsModule;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * An implementation of ILocationRepository that uses a GPS module to get location data.
 * It abstracts GPS access and provides an asynchronous interface using Future.
 */
public class GpsLocationRepository implements ILocationRepository {
    private final IGpsModule _gpsModule;
    // An executor service to run GPS queries in a separate thread,
    // ensuring the getPosition() call is non-blocking.
    private final ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * Constructs a GpsLocationRepository with a specific GPS module.
     *
     * @param gpsModule The GPS module to interact with.
     */
    public GpsLocationRepository(IGpsModule gpsModule) {
        this._gpsModule = gpsModule;
    }

    /**
     * Asynchronously retrieves the current geographical position by querying the GPS module.
     * The actual GPS query runs on a separate thread, and a CompletableFuture is returned immediately.
     *
     * @return A CompletableFuture that will be completed with the Location data or an exception.
     */
    @Override
    public Future<Location> getPosition() {
        System.out.println("[GPS Location Repository] getPosition() called. Beginning asynchronous data acquisition.");
        // Create a CompletableFuture that will be completed when the GPS module returns data.
        CompletableFuture<Location> future = new CompletableFuture<>();

        // Submit the GPS query task to the executor service to run asynchronously.
        executor.submit(() -> {
            try {
                // GLR -> GPS: queryGpsSensor()
                Location location = _gpsModule.queryGpsSensor();
                // GPS --> GLR: <Location data>
                System.out.println("[GPS Location Repository] Completing future with acquired location.");
                future.complete(location); // Completes the future with success
            } catch (GpsSensorException e) {
                // GPS --> GLR: <Error signal / No data>
                System.err.println("[GPS Location Repository] Completing future with error: " + e.getMessage());
                future.completeExceptionally(e); // Completes the future with an exception
            } catch (Exception e) {
                // Catch any other unexpected exceptions during the async task
                System.err.println("[GPS Location Repository] Unexpected error during GPS query: " + e.getMessage());
                future.completeExceptionally(new GpsSensorException("Unexpected error during GPS query.", e));
            }
        });

        // Immediately return the Future, allowing the caller (LocalizationService) to continue
        // without blocking, while the GPS query runs in the background.
        System.out.println("[GPS Location Repository] Returning future for Location.");
        return future;
    }

    /**
     * Shuts down the internal executor service.
     * Should be called when the repository is no longer needed to release resources.
     */
    public void shutdown() {
        executor.shutdown();
        System.out.println("[GPS Location Repository] Executor service shut down.");
    }
}