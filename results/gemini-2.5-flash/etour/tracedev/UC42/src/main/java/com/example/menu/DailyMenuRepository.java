package com.example.menu;

/**
 * Interface for managing DailyMenu entities in the persistence layer.
 * Defines operations for finding and deleting daily menus.
 */
public interface DailyMenuRepository {
    /**
     * Finds a DailyMenu entity by its associated DayOfWeek.
     *
     * @param dayOfWeek The DayOfWeek to search for.
     * @return The DailyMenu object if found, null otherwise.
     * @throws NetworkConnectionException if a network connection issue occurs during the search.
     */
    DailyMenu findByDayOfWeek(DayOfWeek dayOfWeek) throws NetworkConnectionException;

    /**
     * Deletes a DailyMenu entity based on its associated DayOfWeek.
     *
     * @param dayOfWeek The DayOfWeek of the menu to be deleted.
     * @throws NetworkConnectionException if a network connection issue occurs during deletion.
     */
    void delete(DayOfWeek dayOfWeek) throws NetworkConnectionException;
}