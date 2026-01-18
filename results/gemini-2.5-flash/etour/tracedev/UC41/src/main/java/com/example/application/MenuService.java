package com.example.application;

import com.example.domain.DailyMenu;
import com.example.domain.NetworkConnectionException;
import com.example.infrastructure.ErrorLogger;
import com.example.infrastructure.IMenuRepository;

/**
 * Service layer for managing daily menus.
 */
public class MenuService {
    private IMenuRepository menuRepository;
    private ValidationService validationService;
    private ErrorLogger errorLogger; // Added to satisfy requirement R19

    public MenuService(IMenuRepository menuRepository, ValidationService validationService, ErrorLogger errorLogger) {
        this.menuRepository = menuRepository;
        this.validationService = validationService;
        this.errorLogger = errorLogger;
    }

    /**
     * Retrieves the menu for a specific day.
     * @param day The day of the week.
     * @return The DailyMenu for the specified day, or an empty DailyMenu if not found.
     * @throws NetworkConnectionException if a network error occurs during data retrieval.
     */
    public DailyMenu getMenuForDay(com.example.domain.DayOfWeek day) {
        System.out.println("[MenuService] Getting menu for day: " + day);
        try {
            DailyMenu dailyMenu = menuRepository.findByDay(day);
            System.out.println("[MenuService] Retrieved menu: " + (dailyMenu != null ? dailyMenu.getDay() : "null"));
            // If no menu exists, return an empty one for the day to allow editing
            return dailyMenu != null ? dailyMenu : new DailyMenu(day);
        } catch (NetworkConnectionException e) {
            // R19: Service catches NetworkConnectionException, logs it, and re-throws to Controller
            errorLogger.logError(e);
            throw e;
        }
    }

    /**
     * Attempts to update a daily menu. This method validates the menu but does not persist it.
     * It's used to verify data before presenting a confirmation.
     * @param dailyMenu The daily menu to update.
     * @return true if the menu is valid for update, false otherwise.
     */
    public boolean updateMenu(DailyMenu dailyMenu) {
        System.out.println("[MenuService] Updating menu (validation only) for day: " + dailyMenu.getDay());
        return validateMenu(dailyMenu);
    }

    /**
     * Saves the confirmed daily menu to the repository.
     * @param dailyMenu The daily menu to save.
     * @return true if the menu was successfully saved, false otherwise.
     * @throws NetworkConnectionException if a network error occurs during data persistence.
     */
    public boolean saveConfirmedMenu(DailyMenu dailyMenu) {
        System.out.println("[MenuService] Saving confirmed menu for day: " + dailyMenu.getDay());
        try {
            // Assuming validation already passed via updateMenu, but can re-validate here for safety
            if (!validateMenu(dailyMenu)) {
                System.out.println("[MenuService] Save failed: Menu validation failed again.");
                return false;
            }
            return menuRepository.save(dailyMenu);
        } catch (NetworkConnectionException e) {
            // R19: Service catches NetworkConnectionException, logs it, and re-throws to Controller
            errorLogger.logError(e);
            throw e;
        }
    }

    /**
     * Internal method to validate the DailyMenu using the ValidationService.
     * @param dailyMenu The DailyMenu to validate.
     * @return true if valid, false otherwise.
     */
    private boolean validateMenu(DailyMenu dailyMenu) {
        return validationService.validate(dailyMenu);
    }
}