package com.example;

import java.util.List;

/**
 * Controller that orchestrates menu modification, including validation,
 * authentication, and server connection checks.
 */
public class ModifyMenuController {
    private MenuRepository menuRepository;
    private MenuValidationStrategy validationStrategy;
    private AuthenticationService authService;
    private WeeklyMenu weeklyMenu;

    public ModifyMenuController(MenuRepository menuRepository,
                                MenuValidationStrategy validationStrategy,
                                AuthenticationService authService) {
        this.menuRepository = menuRepository;
        this.validationStrategy = validationStrategy;
        this.authService = authService;
        this.weeklyMenu = new WeeklyMenu();
    }

    /**
     * Loads menu data for a specific day and returns as DTO.
     */
    public MenuDTO loadMenuForm(String dayOfWeek) {
        DailyMenu dailyMenu = menuRepository.findByDay(dayOfWeek);
        if (dailyMenu != null) {
            return convertToDTO(dailyMenu);
        }
        return new MenuDTO(dayOfWeek, List.of()); // empty menu
    }

    /**
     * Uploads menu data for a day (loads from repository and returns DTO).
     * Called from view when a day is selected.
     */
    public MenuDTO uploadMenuData(String dayOfWeek) {
        // In a real scenario, we might check authentication and connection here.
        // For simplicity, we just delegate to loadMenuForm.
        return loadMenuForm(dayOfWeek);
    }

    /**
     * Validates the menu DTO and if valid, asks for confirmation.
     */
    public boolean validateAndSaveMenu(MenuDTO menuDTO) {
        boolean isValid = validationStrategy.validate(menuDTO);
        if (isValid) {
            // In the sequence diagram, controller asks view for confirmation.
            // This method returns true to indicate validation passed.
            return true;
        }
        return false;
    }

    /**
     * Called after user confirmation to save the menu.
     */
    public void saveConfirmedMenu(MenuDTO menuDTO) {
        weeklyMenu.updateMenuForDay(menuDTO.getDayOfWeek(), menuDTO.getMenuItems());
        DailyMenu dailyMenu = new DailyMenu(menuDTO.getDayOfWeek(), menuDTO.getMenuItems());
        menuRepository.save(dailyMenu);
    }

    /**
     * Asks the view for confirmation (in sequence diagram, controller calls view).
     * This method is a placeholder; the actual interaction is handled by the view.
     */
    public void askForConfirmation() {
        // This method would typically trigger the view's confirmation dialog.
        // Implementation here is a no-op because the view handles UI.
        System.out.println("Controller: Please confirm the menu changes.");
    }

    /**
     * Converts a DailyMenu to a MenuDTO.
     */
    public MenuDTO convertToDTO(DailyMenu dailyMenu) {
        return new MenuDTO(dailyMenu.getDayOfWeek(), dailyMenu.getMenuItems());
    }

    public DailyMenu getDailyMenuObject() {
        // This method corresponds to sequence diagram message m11
        // It should return a DailyMenu object from the repository
        // For now, we'll implement a simple version
        return menuRepository.findByDay("Monday");
    }
}