package com.example;

import java.util.List;

/**
 * DailyMenu class representing a daily menu in the system.
 * Contains attributes such as id, day, menu items, and restaurant ID.
 */
public class DailyMenu {
    private int id;
    private String day;
    private List<String> menuItems;
    private int restaurantId;

    /**
     * Constructor to create a DailyMenu object.
     * @param id The unique identifier.
     * @param day The day of the week.
     * @param menuItems The list of menu items.
     * @param restaurantId The ID of the associated restaurant.
     */
    public DailyMenu(int id, String day, List<String> menuItems, int restaurantId) {
        this.id = id;
        this.day = day;
        this.menuItems = menuItems;
        this.restaurantId = restaurantId;
    }

    // Getters for attributes.
    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public List<String> getMenuItems() {
        return menuItems;
    }

    public int getRestaurantId() {
        return restaurantId;
    }
}