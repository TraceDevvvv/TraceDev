package com.example.menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Concrete implementation of DailyMenuRepository for database operations.
 * For this example, it simulates a database by using an in-memory map.
 * It can also simulate NetworkConnectionException for testing error scenarios.
 */
public class DailyMenuDatabaseRepository implements DailyMenuRepository {
    private final Map<DayOfWeek, DailyMenu> database = new EnumMap<>(DayOfWeek.class);
    private final Random random = new Random();

    // Static flag to simulate network failure for testing specific scenarios
    public static boolean SIMULATE_NETWORK_FAILURE = false;

    /**
     * Initializes the repository with some mock daily menu data.
     */
    public DailyMenuDatabaseRepository() {
        // Populate with some initial data
        database.put(DayOfWeek.MONDAY, new DailyMenu(UUID.randomUUID().toString(), DayOfWeek.MONDAY, Arrays.asList("Pasta", "Salad")));
        database.put(DayOfWeek.TUESDAY, new DailyMenu(UUID.randomUUID().toString(), DayOfWeek.TUESDAY, Arrays.asList("Tacos", "Rice")));
        database.put(DayOfWeek.WEDNESDAY, new DailyMenu(UUID.randomUUID().toString(), DayOfWeek.WEDNESDAY, Arrays.asList("Pizza", "Fries")));
        database.put(DayOfWeek.THURSDAY, new DailyMenu(UUID.randomUUID().toString(), DayOfWeek.THURSDAY, Arrays.asList("Steak", "Potatoes")));
        database.put(DayOfWeek.FRIDAY, new DailyMenu(UUID.randomUUID().toString(), DayOfWeek.FRIDAY, Arrays.asList("Fish", "Vegetables")));
    }

    /**
     * Finds a DailyMenu entity by its associated DayOfWeek.
     *
     * @param dayOfWeek The DayOfWeek to search for.
     * @return The DailyMenu object if found, null otherwise.
     * @throws NetworkConnectionException if a network connection issue is simulated.
     */
    @Override
    public DailyMenu findByDayOfWeek(DayOfWeek dayOfWeek) throws NetworkConnectionException {
        simulateNetworkLatency(); // Simulate network delay
        if (SIMULATE_NETWORK_FAILURE && random.nextDouble() < 0.5) { // 50% chance of failure if flag is on
            throw new NetworkConnectionException("Simulated network connection interrupted during find operation for " + dayOfWeek);
        }
        System.out.println("[DailyMenuDatabaseRepository] Finding menu for " + dayOfWeek + "...");
        return database.get(dayOfWeek);
    }

    /**
     * Deletes a DailyMenu entity based on its associated DayOfWeek.
     *
     * @param dayOfWeek The DayOfWeek of the menu to be deleted.
     * @throws NetworkConnectionException if a network connection issue is simulated.
     */
    @Override
    public void delete(DayOfWeek dayOfWeek) throws NetworkConnectionException {
        simulateNetworkLatency(); // Simulate network delay
        // REQ-R13: Simulate connection interrupted during deletion.
        // For demonstration, we'll make it throw NetworkConnectionException if SIMULATE_NETWORK_FAILURE is true.
        if (SIMULATE_NETWORK_FAILURE) {
            throw new NetworkConnectionException("Simulated network connection interrupted during deletion for " + dayOfWeek);
        }

        if (database.containsKey(dayOfWeek)) {
            database.remove(dayOfWeek);
            System.out.println("[DailyMenuDatabaseRepository] Successfully deleted menu for " + dayOfWeek);
        } else {
            System.out.println("[DailyMenuDatabaseRepository] No menu found for " + dayOfWeek + " to delete.");
        }
    }

    /**
     * Simulates network latency.
     */
    private void simulateNetworkLatency() {
        try {
            Thread.sleep(random.nextInt(100) + 50); // 50-150 ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}