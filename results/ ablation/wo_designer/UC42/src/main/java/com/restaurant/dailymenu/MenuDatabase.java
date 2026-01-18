
package com.restaurant.dailymenu;

import java.util.HashMap;
import java.util.Map;

public class MenuDatabase {
    private Map<DayOfWeek, String> dailyMenus;
    
    public MenuDatabase() {
        dailyMenus = new HashMap<>();
        // Initialize with sample data
        for (DayOfWeek day : DayOfWeek.values()) {
            dailyMenus.put(day, "Menu for " + day);
        }
    }
    
    /**
     * Delete menu for specific day
     * @param day day to delete menu for
     * @return true if deletion successful
     */
    public boolean deleteMenu(DayOfWeek day) {
        if (dailyMenus.containsKey(day)) {
            dailyMenus.remove(day);
            System.out.println("Database: Menu for " + day + " deleted.");
            return true;
        }
        return false;
    }
    
    /**
     * Check if menu exists for day
     * @param day day to check
     * @return true if menu exists
     */
    public boolean menuExists(DayOfWeek day) {
        return dailyMenus.containsKey(day);
    }
    
    /**
     * Get menu for day
     * @param day day to get menu for
     * @return menu content or null if not found
     */
    public String getMenu(DayOfWeek day) {
        return dailyMenus.get(day);
    }
}
