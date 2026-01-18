package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a daily menu for a restaurant.
 */
public class Menu {
    private Long id;
    private Long restaurantId;
    private String dayOfWeek;
    private List<String> items;
    private Date lastUpdated;

    // Constructor
    public Menu(Long restaurantId, String dayOfWeek) {
        this.restaurantId = restaurantId;
        this.dayOfWeek = dayOfWeek;
        this.items = new ArrayList<>();
        this.lastUpdated = new Date(); // Set initial lastUpdated to current date
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
        updateLastUpdated();
    }

    // Additional methods
    public void addItem(String item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
        updateLastUpdated();
    }

    public void removeItem(String item) {
        if (items != null) {
            items.remove(item);
            updateLastUpdated();
        }
    }

    public void updateLastUpdated() {
        this.lastUpdated = new Date(); // Update to current time
    }

    // Getter for lastUpdated
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}