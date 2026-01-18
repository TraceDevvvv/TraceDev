package com.example.domain;

import java.util.List;

/**
 * Represents a daily menu containing a day and list of menu items.
 */
public class DailyMenu {
    private DayOfWeek day;
    private List<MenuItem> menuItems;

    public DailyMenu(DayOfWeek day, List<MenuItem> menuItems) {
        this.day = day;
        this.menuItems = menuItems;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}