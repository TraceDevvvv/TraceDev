package com.example.infrastructure;

import com.example.domain.DailyMenu;
import com.example.domain.DayOfWeek;
import com.example.domain.MenuItem;
import com.example.domain.NetworkConnectionException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Concrete implementation of IMenuRepository.
 * Simulates database interactions using an in-memory map and introduces artificial network errors (R19).
 */
public class MenuRepositoryImpl implements IMenuRepository {
    private Map<DayOfWeek, DailyMenu> database = new HashMap<>();
    private boolean simulateNetworkError = false; // Flag to control network error simulation

    public MenuRepositoryImpl() {
        // Initialize with some dummy data
        database.put(DayOfWeek.MONDAY, new DailyMenu(DayOfWeek.MONDAY,
                Arrays.asList(new MenuItem("Spaghetti", "Pasta with rich tomato sauce", 12.50, true),
                              new MenuItem("Ceasar Salad", "Classic salad with grilled chicken", 9.00, true))));
        database.put(DayOfWeek.TUESDAY, new DailyMenu(DayOfWeek.TUESDAY,
                Arrays.asList(new MenuItem("Taco Tuesday", "Two delicious tacos with beef or chicken", 10.00, true))));
    }

    /**
     * Sets whether the repository should simulate network errors.
     * @param simulateNetworkError true to simulate errors, false otherwise.
     */
    public void setSimulateNetworkError(boolean simulateNetworkError) {
        this.simulateNetworkError = simulateNetworkError;
    }

    /**
     * Helper method to simulate network delay and potential error.
     * @throws NetworkConnectionException if simulateNetworkError is true or random condition met.
     */
    private void simulateNetworkDelayAndError() throws NetworkConnectionException {
        try {
            // Simulate network delay
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // R19: Simulate NetworkConnectionException
        if (simulateNetworkError || ThreadLocalRandom.current().nextInt(100) < 5) { // 5% chance of random error
            throw new NetworkConnectionException("Simulated network issue during database operation.");
        }
    }

    @Override
    public DailyMenu findByDay(DayOfWeek day) throws NetworkConnectionException {
        System.out.println("[IMenuRepository] findByDay called for: " + day);
        simulateNetworkDelayAndError(); // R19

        DailyMenu foundMenu = database.get(day);
        if (foundMenu != null) {
            // Return a deep copy to prevent external modification of the stored object
            return new DailyMenu(foundMenu.getDay(), foundMenu.getMenuItems());
        }
        return null;
    }

    @Override
    public boolean save(DailyMenu dailyMenu) throws NetworkConnectionException {
        System.out.println("[IMenuRepository] save called for: " + dailyMenu.getDay());
        simulateNetworkDelayAndError(); // R19

        if (dailyMenu == null || dailyMenu.getDay() == null) {
            System.err.println("[IMenuRepository] Cannot save null menu or menu without a day.");
            return false;
        }

        // Store a deep copy to prevent external modification of the stored object
        database.put(dailyMenu.getDay(), new DailyMenu(dailyMenu.getDay(), dailyMenu.getMenuItems()));
        System.out.println("[IMenuRepository] Successfully saved menu for: " + dailyMenu.getDay());
        return true;
    }
}