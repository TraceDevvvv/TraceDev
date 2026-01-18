'''
This service class simulates fetching convention data from a backend system.
It's responsible for retrieving convention history for a given point of rest.
In a real application, this would interact with databases, REST APIs, or other data sources.
'''
package com.chatdev.viscon; // Using a package for better organization
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * A service class to simulate fetching historical convention data
 * for various points of rest. It also simulates potential server connection errors.
 */
public class ConventionService {
    // --- Mock Data ---
    private final List<Convention> mockConventions = new ArrayList<>();
    private final Random random = new Random();
    /**
     * Initializes the mock convention data for demonstration purposes.
     * Conventions are now explicitly linked to a PointOfRest using `pointOfRestId`.
     */
    public ConventionService() {
        // Conventions for "Restaurant A" (POR_A)
        mockConventions.add(new Convention("CON001", "Summer Deal 2022",
                LocalDate.of(2022, 6, 1), LocalDate.of(2022, 8, 31),
                "10% off for group bookings over 10 people.", "POR_A"));
        mockConventions.add(new Convention("CON002", "Winter Promotion 2021",
                LocalDate.of(2021, 12, 1), LocalDate.of(2022, 2, 28),
                "Fixed price menu for holiday events.", "POR_A"));
        // Conventions for "Hotel B" (POR_B)
        mockConventions.add(new Convention("CON003", "Corporate Package Q3",
                LocalDate.of(2023, 7, 1), LocalDate.of(2023, 9, 30),
                "Special rates for corporate meetings and stays.", "POR_B"));
        mockConventions.add(new Convention("CON004", "New Year's Eve Gala",
                LocalDate.of(2022, 12, 31), LocalDate.of(2023, 1, 1),
                "Gala dinner and accommodation for two.", "POR_B"));
        mockConventions.add(new Convention("CON005", "Spring Break Family Offer",
                LocalDate.of(2023, 3, 15), LocalDate.of(2023, 4, 15),
                "Kids stay and eat free with paying adults.", "POR_B"));
        // Conventions for "Conference Center C" (POR_C)
        mockConventions.add(new Convention("CON006", "Tech Summit 2023 Agreement",
                LocalDate.of(2023, 10, 10), LocalDate.of(2023, 10, 12),
                "Venue rental, catering, and AV equipment.", "POR_C"));
        mockConventions.add(new Convention("CON007", "Annual General Meeting",
                LocalDate.of(2023, 5, 20), LocalDate.of(2023, 5, 20),
                "Main hall booking with plenary setup.", "POR_C"));
    }
    /**
     * Simulates fetching a list of conventions associated with a specific
     * PointOfRest. This method now dynamically filters conventions based on
     * the pointOfRestId stored in each Convention object, making it more robust.
     *
     * @param pointOfRest The point of rest for which to retrieve conventions.
     * @return A list of {@link Convention} objects.
     * @throws ConnectionInterruptionException If a simulated server connection
     *         error occurs.
     */
    public List<Convention> getConventionsForPointOfRest(PointOfRest pointOfRest)
            throws ConnectionInterruptionException {
        // Simulate network latency
        try {
            Thread.sleep(random.nextInt(500) + 200); // 200-700ms delay
        } catch (InterruptedException e) {
            // Restore the interrupted status
            Thread.currentThread().interrupt();
            // Re-throw as a runtime exception or handle specifically if needed
            throw new RuntimeException("Thread interrupted during simulated latency.", e);
        }
        // Simulate ETOUR server connection interruption 10% of the time
        if (random.nextInt(10) == 0) { // 1 in 10 chance
            throw new ConnectionInterruptionException("Connection to ETOUR server interrupted.");
        }
        // Dynamically filter mock data based on the selected point of rest ID
        String targetPointOfRestId = pointOfRest.getId();
        List<Convention> result = new ArrayList<>();
        for (Convention convention : mockConventions) {
            if (convention.getPointOfRestId().equals(targetPointOfRestId)) {
                result.add(convention);
            }
        }
        return result;
    }
}