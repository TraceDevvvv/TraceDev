package com.example.menu;

import java.util.List;
import java.util.Objects;

/**
 * Represents a daily menu with a unique ID, day of the week, and a list of items.
 * The 'items' list is simplified for this example, could be MenuItem objects.
 */
public class DailyMenu {
    private final String id;
    private final DayOfWeek dayOfWeek;
    private final List<String> items;

    /**
     * Constructs a new DailyMenu.
     *
     * @param id The unique identifier for the menu.
     * @param dayOfWeek The day of the week this menu is for.
     * @param items A list of menu items (simplified as Strings for this example).
     */
    public DailyMenu(String id, DayOfWeek dayOfWeek, List<String> items) {
        this.id = Objects.requireNonNull(id, "ID cannot be null");
        this.dayOfWeek = Objects.requireNonNull(dayOfWeek, "DayOfWeek cannot be null");
        this.items = Objects.requireNonNull(items, "Items list cannot be null");
    }

    /**
     * Gets the unique identifier of the daily menu.
     * @return The menu's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the day of the week this menu is associated with.
     * @return The DayOfWeek.
     */
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Gets the list of items on this daily menu.
     * @return A list of menu items.
     */
    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "DailyMenu{" +
               "id='" + id + '\'' +
               ", dayOfWeek=" + dayOfWeek +
               ", items=" + items +
               '}';
    }
}