package com.restaurant.menumanager.model;

/**
 * Represents a single item on the menu with a name, price, and description.
 */
public class MenuItem {
    private String name;
    private double price;
    private String description;

    /**
     * Constructs a new MenuItem.
     *
     * @param name The name of the menu item.
     * @param price The price of the menu item.
     * @param description The description of the menu item.
     */
    public MenuItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    /**
     * Returns the name of the menu item.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the menu item.
     *
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the price of the menu item.
     *
     * @return The price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the menu item.
     *
     * @param price The new price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the description of the menu item.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the menu item.
     *
     * @param description The new description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns a string representation of the MenuItem.
     *
     * @return A formatted string including name, price, and description.
     */
    @Override
    public String toString() {
        return String.format("%s (%.2f) - %s", name, price, description);
    }
}