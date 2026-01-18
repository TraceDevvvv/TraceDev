package com.example;

import java.util.List;

/**
 * Data Transfer Object for menu information.
 */
public class MenuDTO {
    private String dayOfWeek;
    private List<String> menuItems;

    public MenuDTO(String dayOfWeek, List<String> menuItems) {
        this.dayOfWeek = dayOfWeek;
        this.menuItems = menuItems;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<String> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<String> menuItems) {
        this.menuItems = menuItems;
    }
}