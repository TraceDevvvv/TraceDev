package com.example;

/**
 * Interface for menu repository operations.
 */
public interface MenuRepository {
    /**
     * Find a DayMenu by day of the week.
     * @param dayOfWeek The day.
     * @return DayMenu for that day.
     */
    DayMenu findByDay(String dayOfWeek);

    /**
     * Save a DayMenu.
     * @param menu The menu to save.
     */
    void save(DayMenu menu);

    /**
     * Upload menu data for a specific day.
     * Added to satisfy requirement Flow of Events: 5.
     * @param dayOfWeek The day of the week.
     * @param menuData The menu data to upload.
     * @return true if upload succeeded, false otherwise.
     */
    boolean uploadMenuData(String dayOfWeek, MenuDataDTO menuData);
}