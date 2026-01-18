package com.example;

/**
 * Interface for menu repository operations.
 * Defines methods for finding and deleting daily menus.
 */
public interface IMenuRepository {
    /**
     * Finds a daily menu by day.
     * @param day The day of the week.
     * @return The DailyMenu object, or null if not found.
     */
    DailyMenu findDailyMenu(String day);

    /**
     * Deletes a daily menu by day.
     * @param day The day of the week.
     * @return true if deletion was successful, false otherwise.
     */
    boolean deleteDailyMenu(String day);
}