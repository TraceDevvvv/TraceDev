package com.example.infrastructure;

import com.example.domain.DailyMenu;
import com.example.domain.MenuItem;
import com.example.domain.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Transfer Object for DailyMenu.
 * Converts between domain and persistence representations.
 */
public class DailyMenuDTO {
    private DayOfWeek day;
    private List<MenuItemDTO> items;

    public DailyMenu toDomain() {
        List<MenuItem> menuItems = items.stream()
                                        .map(MenuItemDTO::toDomain)
                                        .collect(Collectors.toList());
        return new DailyMenu(day, menuItems);
    }

    public static DailyMenuDTO fromDomain(DailyMenu menu) {
        DailyMenuDTO dto = new DailyMenuDTO();
        dto.setDay(menu.getDay());
        List<MenuItemDTO> itemDTOs = menu.getMenuItems().stream()
                                         .map(MenuItemDTO::fromDomain)
                                         .collect(Collectors.toList());
        dto.setItems(itemDTOs);
        return dto;
    }

    // Getters and setters (required for conversion frameworks or serialization)
    public DayOfWeek getDay() {
        return day;
    }

    public void setDay(DayOfWeek day) {
        this.day = day;
    }

    public List<MenuItemDTO> getItems() {
        return items;
    }

    public void setItems(List<MenuItemDTO> items) {
        this.items = items;
    }
}