package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * DayMenuForm class representing the UI form for a day's menu.
 */
public class DayMenuForm {
    private String selectedDay;
    private List<MenuItem> menuItems;

    public DayMenuForm() {
        this.menuItems = new ArrayList<>();
    }

    public String getSelectedDay() {
        return selectedDay;
    }

    public void setSelectedDay(String selectedDay) {
        this.selectedDay = selectedDay;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems != null ? menuItems : new ArrayList<>();
    }

    /**
     * Populate the form with data from a DayMenu.
     * @param menu The DayMenu object.
     */
    public void populateForm(DayMenu menu) {
        this.selectedDay = menu.getDayOfWeek();
        this.menuItems = menu.getMenuItems();
    }

    /**
     * Display a confirmation message.
     * Added to satisfy requirement Flow of Events: 10.
     * @param message The confirmation message.
     * @return true if confirmed, false otherwise.
     */
    public boolean displayConfirmation(String message) {
        System.out.println("Displaying confirmation: " + message);
        // In a real UI, this would show a dialog and wait for user input.
        return true;
    }

    /**
     * Create a MenuDataDTO from the form data.
     * Added for consistency with sequence diagram.
     * @return MenuDataDTO representing the form data.
     */
    public MenuDataDTO createMenuDataDTO() {
        MenuDataDTO dto = new MenuDataDTO();
        dto.setDayOfWeek(this.selectedDay);
        // Convert MenuItem to MenuItemDTO
        List<MenuItemDTO> itemDTOs = new ArrayList<>();
        for (MenuItem item : menuItems) {
            MenuItemDTO itemDTO = new MenuItemDTO();
            itemDTO.setName(item.getName());
            itemDTO.setDescription(item.getDescription());
            itemDTO.setPrice(item.getPrice());
            itemDTO.setCategory(item.getCategory());
            itemDTOs.add(itemDTO);
        }
        dto.setMenuItems(itemDTOs);
        return dto;
    }
}