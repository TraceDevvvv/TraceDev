package com.example.entities;

import com.example.enums.DayOfWeek;
import java.util.List;

/**
 * Represents a menu for a specific day.
 */
public class Menu {
    private Long id;
    private DayOfWeek day;
    private List<MenuItem> items;

    public Menu() {}

    public Menu(Long id, DayOfWeek day, List<MenuItem> items) {
        this.id = id;
        this.day = day;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
}