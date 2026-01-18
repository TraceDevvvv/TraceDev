package com.restaurant.menumanager.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a daily menu containing a list of MenuItem objects.
 * Each menu is associated with a specific day of the week.
 */
public class Menu {
    private String dayOfWeek;
    private List<MenuItem> items;

    /**
     * Constructs a new Menu for a given day of the week.
     * Initializes an empty list of menu items.
     *
     * @param dayOfWeek The day of the week this menu belongs to (e.g., "Monday").
     */
    public Menu(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        this.items = new ArrayList<>();
    }

    /**
     * Returns the day of the week for this menu.
     *
     * @return The day of the week.
     */
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    /**
     * Returns the list of menu items for this day.
     *
     * @return A list of MenuItem objects.
     */
    public List<MenuItem> getItems() {
        return items;
    }

    /**
     * Adds a new MenuItem to this menu.
     *
     * @param item The MenuItem to add.
     */
    public void addItem(MenuItem item) {
        if (item != null) {
            this.items.add(item);
        }
    }

    /**
     * Removes a specified MenuItem from this menu.
     *
     * @param item The MenuItem to remove.
     */
    public void removeItem(MenuItem item) {
        this.items.remove(item);
    }

    /**
     * Updates an existing MenuItem at a specific index in the menu.
     *
     * @param index The index of the item to update.
     * @param newItem The new MenuItem to replace the old one.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size()).
     */
    public void updateItem(int index, MenuItem newItem) {
        if (index >= 0 && index < items.size() && newItem != null) {
            this.items.set(index, newItem);
        } else {
            throw new IndexOutOfBoundsException("Invalid index for menu item update or new item is null.");
        }
    }

    /**
     * Returns a string representation of the Menu, including the day of the week
     * and all its menu items.
     *
     * @return A formatted string representing the menu.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Menu for ").append(dayOfWeek).append(" ---\n");
        if (items.isEmpty()) {
            sb.append("No items on the menu for this day.\n");
        } else {
            for (int i = 0; i < items.size(); i++) {
                sb.append(String.format("%d. %s\n", (i + 1), items.get(i).toString()));
            }
        }
        sb.append("---------------------------\n");
        return sb.toString();
    }
}