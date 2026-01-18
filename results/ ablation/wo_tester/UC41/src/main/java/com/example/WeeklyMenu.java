package com.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages weekly menus, each day associated with a DailyMenu.
 */
public class WeeklyMenu {
    private Map<String, DailyMenu> weeklyMenus;

    public WeeklyMenu() {
        this.weeklyMenus = new HashMap<>();
    }

    public DailyMenu getMenuForDay(String dayOfWeek) {
        return weeklyMenus.get(dayOfWeek);
    }

    public DailyMenu getOrCreateDailyMenu() {
        // This method is called in sequence diagram message m30
        // Implementation: get or create a DailyMenu for a specific day
        // For now, we'll implement a simple version that returns the first available
        // In a complete implementation, we might need to pass parameters
        if (weeklyMenus.isEmpty()) {
            return null;
        }
        return weeklyMenus.values().iterator().next();
    }

    public void updateMenuForDay(String dayOfWeek, List<String> menuItems) {
        DailyMenu dailyMenu = weeklyMenus.get(dayOfWeek);
        if (dailyMenu == null) {
            dailyMenu = new DailyMenu(dayOfWeek, menuItems);
            weeklyMenus.put(dayOfWeek, dailyMenu);
        } else {
            dailyMenu.setMenuItems(menuItems);
        }
    }
}