package com.example.localization;

import com.example.localization.module.IGpsModule;
import com.example.localization.module.MockGpsModule;
import com.example.localization.repository.GpsLocationRepository;
import com.example.localization.repository.ILocationRepository;
import com.example.localization.service.LocalizationService;
import com.example.localization.model.Location;

import java.util.concurrent.Future;
import java.util.Random;

/**
 * Main application class simulating the "Tourist App" actor from the sequence diagram.
 * It orchestrates the interaction with the Localization Service.
 * Note: A search HAS begun as an entry condition for system interaction (R3).
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Tourist App Started ===");
        System.out.println("Note: A search HAS begun (R3)");

        // Initialize components
        // Configure MockGpsModule: 1000-6000ms delay.
        // We'll sometimes allow failures, and sometimes force success for demonstration.
        Random random = new Random();
        boolean forceSuccess = random.nextBoolean(); // Randomly choose to force success or allow failure
        int minDelay = 1000; // 1 second
        int maxDelay = 6000; // 6 seconds, to test timeout (R9: 5 seconds)

        System.out.println(String.format("Mock GPS Module configured with delay: %d-%dms, Force Success: %b",
                                        minDelay, maxDelay, forceSuccess));

        // Dependency Injection setup
        IGpsModule gpsModule = new MockGpsModule(minDelay, maxDelay, forceSuccess);
        ILocationRepository locationRepository = new GpsLocationRepository(gpsModule);
        LocalizationService localizationService = new LocalizationService(locationRepository);

        System.out.println("\n[TouristApp] TouristApp -> Localization Service: requestCurrentLocation()");
        // TouristApp -> LS: requestCurrentLocation()
        Future<Location> futureLocation = localizationService.requestCurrentLocation();
        // LS --> TouristApp: futureLocation : Future<Location>

        System.out.println("[TouristApp] Received futureLocation. Application can now perform other tasks while waiting.");

        // Simulate some other work in the TouristApp while the location is being fetched
        try {
            System.out.println("[TouristApp] Simulating other app activity for 2 seconds...");
            Thread.sleep(2000); // Simulate other work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("[TouristApp] Other activity interrupted.");
        }

        // After some time, the TouristApp needs the location.
        // It asks the LocalizationService to process the future.
        System.out.println("\n[TouristApp] Now checking on the location request.");
        // Although the sequence diagram shows LS -> LS: processCompletedFuture(),
        // in a real system, the TouristApp would be the one interested in the result.
        // The LS.processCompletedFuture is an internal handling by LS once it's ready to handle the async result.
        // For demonstration, we'll trigger it from Main, simulating LS's internal handling mechanism.
        localizationService.processCompletedFuture(futureLocation);

        // Ensure the GpsLocationRepository's executor service is shut down
        if (locationRepository instanceof GpsLocationRepository) {
            ((GpsLocationRepository) locationRepository).shutdown();
        }

        System.out.println("\n=== Tourist App Finished ===");
    }
}