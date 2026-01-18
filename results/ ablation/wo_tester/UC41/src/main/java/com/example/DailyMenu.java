package com.example;

import java.util.List;

/**
 * Represents a menu for a single day.
 */
public class DailyMenu {
    private String dayOfWeek;
    private List<String> menuItems;

    public DailyMenu(String dayOfWeek, List<String> menuItems) {
        this.dayOfWeek = dayOfWeek;
        this.menuItems = menuItems;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public List<String> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<String> menuItems) {
        this.menuItems = menuItems;
    }
}