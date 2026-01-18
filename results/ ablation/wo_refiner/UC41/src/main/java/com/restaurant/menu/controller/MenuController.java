package com.restaurant.menu.controller;

import com.restaurant.menu.dto.MenuDTO;
import com.restaurant.menu.dto.WeekMenuDTO;
import com.restaurant.menu.service.MenuService;
import com.restaurant.menu.validation.MenuValidator;
import com.restaurant.menu.validation.ValidationResult;
import com.restaurant.menu.exception.ServiceUnavailableException;
import com.restaurant.menu.exception.ErrorResponse;
import com.restaurant.menu.exception.InvalidMenuDataException;
import com.restaurant.menu.converter.DTOConverter;
import com.restaurant.menu.auth.AuthenticationService;

/**
 * Controller for menu operations.
 * Orchestrates the flow of the ModifyMenu use case.
 */
public class MenuController {
    private MenuService menuService;
    private MenuValidator validator;
    private AuthenticationService authService;
    private DTOConverter dtoConverter;

    public MenuController(MenuService menuService, MenuValidator validator, AuthenticationService authService) {
        this.menuService = menuService;
        this.validator = validator;
        this.authService = authService;
        this.dtoConverter = new DTOConverter();
    }

    /**
     * Activates the editing mode (requirement R6).
     */
    public void activateEditing() {
        // In a real application, might set some state or log.
        System.out.println("Editing mode activated.");
    }

    public WeekMenuDTO showWeekMenuForm() {
        return menuService.getWeekMenu();
    }

    /**
     * Loads menu data for a given day (requirement R9).
     */
    public MenuDTO loadDayMenuData(String day) {
        // Assumption: getMenuByDay returns a Menu entity, convert to DTO.
        var menu = menuService.getMenuByDay(day);
        return dtoConverter.toMenuDTO(menu);
    }

    /**
     * Uploads day menu data (requirement R9).
     */
    public MenuDTO uploadDayMenuData(String day) {
        // This method appears to be the same as loadDayMenuData per diagram.
        // Call loadDayMenuData to avoid duplication.
        return loadDayMenuData(day);
    }

    /**
     * Submits day selection (requirement R8).
     */
    public void submitDaySelection(String day) {
        // Could store selected day in session or state.
        System.out.println("Day selected: " + day);
    }

    /**
     * Edits a menu (validates and processes edits).
     */
    public ValidationResult editMenu(MenuDTO menuDTO) {
        // Convert DTO to Menu for validation
        var menu = dtoConverter.toMenu(menuDTO);
        ValidationResult result = validator.validate(menu);
        if (!result.isValid()) {
            handleEditError(result);
        }
        return result;
    }

    /**
     * Handles edit errors (requirement R11).
     */
    public void handleEditError(ValidationResult validationResult) {
        // Log errors, maybe throw an exception
        throw new InvalidMenuDataException("Menu data validation failed", validationResult.getErrors());
    }

    public boolean confirmEdit(String menuId) {
        // In a real app, might check if menu exists, etc.
        System.out.println("Edit confirmed for menu: " + menuId);
        return true;
    }

    /**
     * Prepares for save operation.
     */
    public void prepareForSave() {
        System.out.println("Preparing to save menu...");
    }

    /**
     * Notifies successful modification (requirement R14).
     */
    public void notifySuccess(String menuId) {
        System.out.println("Menu " + menuId + " modified successfully.");
    }

    public void cancelEdit(String menuId) {
        System.out.println("Edit cancelled for menu: " + menuId);
    }

    /**
     * Handles service unavailable exceptions (requirement R16).
     */
    public void handleServiceUnavailableException(ServiceUnavailableException ex) {
        // Log and create error response
        System.err.println("Service unavailable: " + ex.getMessage());
        throw new ErrorResponse(503, "Service unavailable. Please try again later.");
    }

    public boolean saveMenu(MenuDTO menuDTO) {
        return menuService.updateMenu(menuDTO);
    }
}