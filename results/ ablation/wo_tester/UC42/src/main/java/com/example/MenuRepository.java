package com.example;

import java.util.Arrays;
import java.util.List;

/**
 * MenuRepository class that implements IMenuRepository.
 * Manages DailyMenu objects and interacts with the database (simulated).
 */
public class MenuRepository implements IMenuRepository {
    private IDatabase database; // Database interface for consistency with sequence diagram.

    /**
     * Constructor that initializes the repository with a database.
     * @param database The database interface for executing queries.
     */
    public MenuRepository(IDatabase database) {
        this.database = database;
    }

    /**
     * Finds a daily menu by day.
     * Simulates a database query to retrieve menu data.
     * @param day The day of the week.
     * @return The DailyMenu object, or null if not found.
     */
    @Override
    public DailyMenu findDailyMenu(String day) {
        System.out.println("Finding daily menu for day: " + day);
        // Simulate database query: SELECT daily_menu WHERE day = :day
        selectDailyMenuWhereDayEquals(day);

        // For demonstration, return a mock DailyMenu if the day is valid.
        if (isValidDay(day)) {
            List<String> items = Arrays.asList("Item1", "Item2", "Item3");
            DailyMenu menu = createDailyMenu(1, day, items, 101);
            returnDailyMenuObject(menu);
            return menu;
        }
        return null;
    }

    /**
     * Deletes a daily menu by day.
     * Simulates a database delete operation.
     * @param day The day of the week.
     * @return true if deletion was successful, false otherwise.
     */
    @Override
    public boolean deleteDailyMenu(String day) {
        System.out.println("Deleting daily menu for day: " + day);
        // Simulate database delete: DELETE daily_menu WHERE day = :day
        deleteDailyMenuWhereDayEquals(day);
        boolean success = true; // Assume success based on executeUpdate
        destroyDailyMenu();
        return success;
    }

    /**
     * Helper method to validate a day string.
     * @param day The day to validate.
     * @return true if the day is a valid day of the week.
     */
    private boolean isValidDay(String day) {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        return Arrays.asList(days).contains(day);
    }

    /**
     * SELECT daily_menu WHERE day = :day
     * Corresponds to message m19 in sequence diagram.
     */
    public void selectDailyMenuWhereDayEquals(String day) {
        database.executeQuery("SELECT * FROM daily_menu WHERE day = '" + day + "'");
    }

    /**
     * Returns daily menu data from DB.
     * Corresponds to message m20 in sequence diagram.
     */
    public void returnDailyMenuData() {
        System.out.println("Repository: Returning daily menu data from DB.");
    }

    /**
     * Creates a DailyMenu object.
     * Corresponds to message m21 in sequence diagram.
     */
    public DailyMenu createDailyMenu(int id, String day, List<String> items, int restaurantId) {
        return new DailyMenu(id, day, items, restaurantId);
    }

    /**
     * Returns DailyMenu object to Service.
     * Corresponds to message m22 in sequence diagram.
     */
    public void returnDailyMenuObject(DailyMenu menu) {
        System.out.println("Repository: Returning DailyMenu object to Service.");
    }

    /**
     * DELETE daily_menu WHERE day = :day
     * Corresponds to message m38 in sequence diagram.
     */
    public void deleteDailyMenuWhereDayEquals(String day) {
        database.executeUpdate("DELETE FROM daily_menu WHERE day = '" + day + "'");
    }

    /**
     * Returns success status from DB.
     * Corresponds to message m39 in sequence diagram.
     */
    public void returnSuccessStatus() {
        System.out.println("Repository: Return success status from DB.");
    }

    /**
     * Destroys DailyMenu object.
     * Corresponds to message m40 in sequence diagram.
     */
    public void destroyDailyMenu() {
        System.out.println("Repository: Destroying DailyMenu object.");
    }
}