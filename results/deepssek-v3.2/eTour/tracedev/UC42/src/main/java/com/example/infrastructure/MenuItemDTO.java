package com.example.infrastructure;

import com.example.domain.MenuItem;
import java.math.BigDecimal;

/**
 * Data Transfer Object for MenuItem.
 * Converts between domain and persistence representations.
 */
public class MenuItemDTO {
    private String name;
    private String description;
    private String price; // Stored as String (e.g., for database compatibility)

    public MenuItem toDomain() {
        BigDecimal priceDecimal = new BigDecimal(price);
        return new MenuItem(name, description, priceDecimal);
    }

    public static MenuItemDTO fromDomain(MenuItem item) {
        MenuItemDTO dto = new MenuItemDTO();
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setPrice(item.getPrice().toString());
        return dto;
    }

    // Getters and setters
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}