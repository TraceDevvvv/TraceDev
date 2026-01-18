package com.example.dto;

import com.example.entity.Menu;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object for Menu.
 */
public class MenuDTO {
    private String dayOfWeek;
    private List<String> items;

    // Default constructor
    public MenuDTO() {
        this.items = new ArrayList<>();
    }

    // Constructor from Menu entity
    public MenuDTO(Menu menu) {
        this.dayOfWeek = menu.getDayOfWeek();
        this.items = new ArrayList<>(menu.getItems()); // Copy to avoid direct reference
    }

    // Converts DTO to Menu entity
    public Menu toMenu(Long restaurantId) {
        Menu menu = new Menu(restaurantId, this.dayOfWeek);
        menu.setItems(this.items); // Items are set; updateLastUpdated is called inside setter
        return menu;
    }

    // Getters and setters
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}