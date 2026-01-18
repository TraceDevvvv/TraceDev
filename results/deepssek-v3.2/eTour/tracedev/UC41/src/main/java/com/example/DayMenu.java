package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * DayMenu class representing a menu for a specific day.
 */
public class DayMenu {
    private String dayOfWeek;
    private List<MenuItem> menuItems;

    public DayMenu() {
        this.menuItems = new ArrayList<>();
    }

    public DayMenu(String dayOfWeek, List<MenuItem> menuItems) {
        this.dayOfWeek = dayOfWeek;
        this.menuItems = menuItems != null ? menuItems : new ArrayList<>();
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems != null ? menuItems : new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        if (menuItems == null) {
            menuItems = new ArrayList<>();
        }
        menuItems.add(item);
    }

    public void removeMenuItem(MenuItem item) {
        if (menuItems != null) {
            menuItems.remove(item);
        }
    }
}