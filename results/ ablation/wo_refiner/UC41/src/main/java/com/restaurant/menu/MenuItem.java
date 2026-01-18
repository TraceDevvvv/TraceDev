package com.restaurant.menu;

/**
 * Represents a single item in a menu.
 */
public class MenuItem {
    private String itemId;
    private String name;
    private String description;
    private float price;
    private String category;

    public MenuItem(String name, String description, float price, String category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        // In a real scenario, itemId would be generated (e.g., UUID)
        this.itemId = "ITEM_" + name.hashCode();
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}