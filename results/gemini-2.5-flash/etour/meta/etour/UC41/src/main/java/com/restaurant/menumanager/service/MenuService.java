package com.restaurant.menumanager.service;

import com.restaurant.menumanager.data.MenuRepository;
import com.restaurant.menumanager.model.Menu;
import com.restaurant.menumanager.model.MenuItem;

import java.util.Arrays;
import java.util.List;

/**
 * Provides business logic for managing menus, interacting with the MenuRepository.
 * This service layer handles operations like retrieving, updating, and initializing menus.
 */
public class MenuService {
    private final MenuRepository menuRepository;
    private static final List<String> ALL_DAYS_OF_WEEK = Arrays.asList(
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    );

    /**
     * Constructs a MenuService with a given MenuRepository.
     *
     * @param menuRepository The repository to use for menu persistence.
     */
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    /**
     * Retrieves the menu for a specific day of the week.
     * If no menu exists for the day, an empty menu is returned.
     *
     * @param dayOfWeek The day for which to retrieve the menu.
     * @return The Menu object for the specified day.
     */
    public Menu getMenuForDay(String dayOfWeek) {
        return menuRepository.loadMenu(dayOfWeek);
    }

    /**
     * Updates the menu for a specific day of the week.
     *
     * @param dayOfWeek The day for which to update the menu.
     * @param updatedMenu The new Menu object to save.
     * @return true if the menu was updated successfully, false otherwise.
     */
    public boolean updateMenu(String dayOfWeek, Menu updatedMenu) {
        if (updatedMenu == null || !dayOfWeek.equalsIgnoreCase(updatedMenu.getDayOfWeek())) {
            System.err.println("Error: Mismatch between dayOfWeek parameter and updatedMenu's day, or updatedMenu is null.");
            return false;
        }
        return menuRepository.saveMenu(dayOfWeek, updatedMenu);
    }

    /**
     * Returns a list of all standard days of the week.
     *
     * @return A list of strings representing the days of the week.
     */
    public List<String> getAllDaysOfWeek() {
        return ALL_DAYS_OF_WEEK;
    }

    /**
     * Initializes empty menus for all days of the week if they don't already exist.
     * This ensures that there's always a base menu file for each day.
     */
    public void initializeDailyMenus() {
        for (String day : ALL_DAYS_OF_WEEK) {
            // loadMenu will return an empty menu if it doesn't exist,
            // then we save it to create the file.
            Menu menu = menuRepository.loadMenu(day);
            if (menu.getItems().isEmpty()) { // Only save if it was truly empty (newly created)
                menuRepository.saveMenu(day, menu);
            }
        }
    }
}