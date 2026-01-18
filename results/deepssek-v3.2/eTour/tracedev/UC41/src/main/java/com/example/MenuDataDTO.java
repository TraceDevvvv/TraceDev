package com.example;

import java.util.List;

/**
 * Data Transfer Object for menu data.
 */
public class MenuDataDTO {
    private String dayOfWeek;
    private List<MenuItemDTO> menuItems;

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<MenuItemDTO> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemDTO> menuItems) {
        this.menuItems = menuItems;
    }
}