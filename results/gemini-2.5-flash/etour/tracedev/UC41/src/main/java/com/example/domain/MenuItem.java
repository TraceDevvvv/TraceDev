package com.example.domain;

/**
 * Represents a single item on a daily menu.
 */
public class MenuItem {
    private String name;
    private String description;
    private double price;
    private boolean available;

    public MenuItem(String name, String description, double price, boolean available) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
               "name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", price=" + price +
               ", available=" + available +
               '}';
    }
}