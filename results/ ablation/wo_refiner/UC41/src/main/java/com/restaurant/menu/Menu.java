package com.restaurant.menu;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a menu for a specific day of the week.
 * Contains a list of menu items.
 */
public class Menu {
    private String menuId;
    private String dayOfWeek;
    private List<MenuItem> items;
    private Date date;

    public Menu(String dayOfWeek, Date date) {
        this.dayOfWeek = dayOfWeek;
        this.date = date;
        this.items = new ArrayList<>();
        // In a real scenario, menuId would be generated (e.g., UUID)
        this.menuId = "MENU_" + dayOfWeek + "_" + date.getTime();
    }

    public String getMenuId() {
        return menuId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String day) {
        this.dayOfWeek = day;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public void addItem(MenuItem item) {
        this.items.add(item);
    }

    public void removeItem(MenuItem item) {
        this.items.remove(item);
    }

    /**
     * Validates the menu (basic validation).
     * Assumption: Menu is valid if it has at least one item.
     * @return true if menu is valid, false otherwise.
     */
    public boolean validateMenu() {
        return items != null && !items.isEmpty();
    }
}