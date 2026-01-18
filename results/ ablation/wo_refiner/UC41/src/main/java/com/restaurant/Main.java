package com.restaurant;

import com.restaurant.menu.ui.MenuUI;
import com.restaurant.menu.controller.MenuController;
import com.restaurant.menu.service.MenuService;
import com.restaurant.menu.validation.MenuValidator;
import com.restaurant.menu.auth.AuthenticationService;
import com.restaurant.menu.repository.MenuRepositoryImpl;
import com.restaurant.menu.converter.DTOConverter;
import com.restaurant.menu.dto.MenuDTO;
import com.restaurant.menu.dto.WeekMenuDTO;
import com.restaurant.menu.validation.ValidationResult;
import javax.sql.DataSource;

/**
 * Main class to simulate the sequence diagram flow.
 * This is a runnable demonstration of the ModifyMenu use case.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Restaurant Menu Modification Use Case Simulation ===");

        // Initialize components (as per sequence diagram)
        AuthenticationService authService = new AuthenticationService();
        DataSource dataSource = null; // Assuming a DataSource is required
        MenuRepositoryImpl menuRepo = new MenuRepositoryImpl(dataSource);
        DTOConverter dtoConverter = new DTOConverter();
        MenuService menuService = new MenuService(menuRepo, dtoConverter);
        MenuValidator validator = new MenuValidator();
        MenuController controller = new MenuController(menuService, validator, authService);
        MenuUI ui = new MenuUI();

        // Simulate Restaurant Operator
        System.out.println("\n1. Restaurant Operator starts use case.");
        ui.startModifyMenuUseCase();

        // Authentication (requirement R4, R17)
        System.out.println("\n2. Authenticating...");
        boolean authSuccess = authService.authenticate("admin", "password");
        if (!authSuccess) {
            System.out.println("Authentication failed. Use case terminates.");
            return;
        }
        System.out.println("Authentication successful.");

        // Activate editing (requirement R6, R7)
        System.out.println("\n3. Activating menu editing...");
        ui.activateMenuEditing();
        controller.activateEditing();

        // Show week menu form (requirement R5, R7)
        System.out.println("\n4. Loading week menu...");
        WeekMenuDTO weekMenu = controller.showWeekMenuForm();
        ui.displayWeekFormWithDays(weekMenu);

        // Select a day (requirement R8)
        System.out.println("\n5. Selecting day...");
        String selectedDay = "Monday";
        ui.selectDay(selectedDay);
        controller.submitDaySelection(selectedDay);

        // Load day menu data (requirement R9)
        System.out.println("\n6. Loading menu data for " + selectedDay + "...");
        MenuDTO menuDTO = controller.loadDayMenuData(selectedDay);
        ui.displayMenuForm(menuDTO);

        // Edit menu data
        System.out.println("\n7. Editing menu data...");
        ui.editMenuData(menuDTO);
        ValidationResult validationResult = controller.editMenu(menuDTO);

        // Simulate validation success path
        if (validationResult.isValid()) {
            System.out.println("Validation successful.");
            ui.showConfirmationDialog();

            // Confirm edit
            System.out.println("\n8. Confirming edit...");
            boolean confirmed = controller.confirmEdit(menuDTO.getMenuId());
            if (confirmed) {
                controller.prepareForSave();
                ui.finalConfirmation();

                // Save menu
                System.out.println("\n9. Saving menu...");
                boolean saveSuccess = controller.saveMenu(menuDTO);
                if (saveSuccess) {
                    controller.notifySuccess(menuDTO.getMenuId());
                    ui.showSuccessNotification();
                    System.out.println("\nExit condition: successful modification.");
                } else {
                    System.out.println("Save failed.");
                }
            }
        } else {
            // Validation failed (requirement R11)
            System.out.println("Validation failed.");
            ui.showErrors(validationResult);
            System.out.println("Use case Errored activated.");
        }

        // Simulate cancel operation (optional)
        System.out.println("\n10. Simulating cancel operation...");
        ui.cancelOperation();
        controller.cancelEdit(menuDTO.getMenuId());
        System.out.println("Alternative exit condition: operation cancelled.");

        // Simulate server connection interruption (optional, requirement R16)
        try {
            System.out.println("\n11. Simulating server connection interruption...");
            throw new com.restaurant.menu.exception.ServerConnectionException("Connection lost");
        } catch (com.restaurant.menu.exception.ServerConnectionException ex) {
            menuService.handleConnectionException(ex);
        } catch (com.restaurant.menu.exception.ServiceUnavailableException ex) {
            controller.handleServiceUnavailableException(ex);
        } catch (com.restaurant.menu.exception.ErrorResponse ex) {
            ui.handleErrorResponse(ex);
            ui.showConnectionError();
            System.out.println("Use case terminated abruptly.");
        }

        System.out.println("\n=== Simulation Complete ===");
    }
}