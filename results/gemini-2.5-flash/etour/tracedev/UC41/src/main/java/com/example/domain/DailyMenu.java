package com.example.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a menu for a specific day of the week.
 */
public class DailyMenu {
    private DayOfWeek day;
    private List<MenuItem> menuItems;

    public DailyMenu(DayOfWeek day, List<MenuItem> menuItems) {
        this.day = day;
        this.menuItems = new ArrayList<>(menuItems);
    }

    public DailyMenu(DayOfWeek day) {
        this.day = day;
        this.menuItems = new ArrayList<>();
    }

    // Getters
    public DayOfWeek getDay() {
        return day;
    }

    public List<MenuItem> getMenuItems() {
        return new ArrayList<>(menuItems); // Return a copy to prevent external modification
    }

    // Methods for managing menu items
    public void addMenuItem(MenuItem item) {
        if (item != null) {
            this.menuItems.add(item);
        }
    }

    public void removeMenuItem(MenuItem item) {
        if (item != null) {
            this.menuItems.remove(item);
        }
    }

    public void updateMenuItem(MenuItem oldItem, MenuItem newItem) {
        if (oldItem != null && newItem != null) {
            int index = this.menuItems.indexOf(oldItem);
            if (index != -1) {
                this.menuItems.set(index, newItem);
            }
        }
    }

    @Override
    public String toString() {
        return "DailyMenu{" +
               "day=" + day +
               ", menuItems=" + menuItems +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyMenu dailyMenu = (DailyMenu) o;
        return day == dailyMenu.day && Objects.equals(menuItems, dailyMenu.menuItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, menuItems);
    }
}