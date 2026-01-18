package com.restaurant.menu.dto;

import java.util.List;

/**
 * Data Transfer Object for Menu.
 */
public class MenuDTO {
    private String menuId;
    private String dayOfWeek;
    private List<MenuItemDTO> items;
    private String date;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String id) {
        this.menuId = id;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String day) {
        this.dayOfWeek = day;
    }

    public List<MenuItemDTO> getItems() {
        return items;
    }

    public void setItems(List<MenuItemDTO> items) {
        this.items = items;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}