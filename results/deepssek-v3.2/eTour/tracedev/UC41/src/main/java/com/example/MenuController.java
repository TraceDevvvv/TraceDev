package com.example;

import java.util.List;

/**
 * MenuController class as defined in UML.
 * Encapsulates the control logic for menu operations including display, validation, and persistence.
 */
public class MenuController {
    private MenuRepository menuRepository;
    private DayMenuForm dayMenuForm;
    private ValidationResult validationResult;
    private NotificationService notificationService;
    private AuthenticationService authenticationService;
    private Validator validator;
    private WeeklyFormData weeklyFormData;
    private ErrorHandler errorHandler;

    public MenuController() {
        // Default constructor - dependencies would typically be injected in a real application
    }

    // Getters and setters for dependency injection
    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void setDayMenuForm(DayMenuForm dayMenuForm) {
        this.dayMenuForm = dayMenuForm;
    }

    public void setValidationResult(ValidationResult validationResult) {
        this.validationResult = validationResult;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public void setWeeklyFormData(WeeklyFormData weeklyFormData) {
        this.weeklyFormData = weeklyFormData;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    /**
     * Display weekly form data, returning a WeeklyFormData object.
     * Updated return type to satisfy requirement Flow of Events: 2
     * @return WeeklyFormData containing data for the weekly form.
     */
    public WeeklyFormData displayWeeklyForm() {
        // Implementation for displaying weekly form.
        // In a real scenario, this might load data from a repository or configuration.
        weeklyFormData.setDays(List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"));
        return weeklyFormData;
    }

    /**
     * Select a day of the week and return a DayMenuForm for that day.
     * @param dayOfWeek The selected day.
     * @return DayMenuForm object for the selected day.
     */
    public DayMenuForm selectDay(String dayOfWeek) {
        // Creates a new DayMenuForm with the selected day.
        DayMenuForm form = new DayMenuForm();
        form.setSelectedDay(dayOfWeek);
        return form;
    }

    /**
     * Load menu for a specific day of the week.
     * This method fetches the menu from repository and returns a DayMenuForm.
     * @param dayOfWeek The day to load.
     * @return DayMenuForm populated with menu data.
     */
    public DayMenuForm loadMenuForDay(String dayOfWeek) {
        DayMenu menu = menuRepository.findByDay(dayOfWeek);
        DayMenuForm form = new DayMenuForm();
        form.populateForm(menu);
        return form;
    }

    /**
     * Validate menu data using the Validator component.
     * @param formData The menu data transfer object.
     * @return ValidationResult indicating validity and any errors.
     */
    public ValidationResult validateMenuData(MenuDataDTO formData) {
        // Delegate validation to the Validator class.
        return validator.validate(formData);
    }

    /**
     * Confirm and save the menu.
     * @param menu The DayMenu object to save.
     */
    public void confirmAndSave(DayMenu menu) {
        // Save the menu via repository.
        menuRepository.save(menu);
    }

    /**
     * Request user confirmation for an operation.
     * This method is added to satisfy sequence diagram consistency.
     * @param message The confirmation message.
     * @return true if the user confirms, false otherwise.
     */
    public boolean requestConfirmation(String message) {
        // In a real implementation, this would interact with UI.
        // For now, assuming confirmation is given.
        System.out.println("Confirmation requested: " + message);
        return true;
    }

    /**
     * Cancel the current operation.
     * Added to satisfy requirement Exit Conditions (cancels operation).
     */
    public void cancelOperation() {
        // Notify that the operation was cancelled.
        if (notificationService != null) {
            notificationService.sendCancelNotification("Operation cancelled by user.");
        }
    }

    /**
     * Check the connection to external systems (e.g., ETOUR).
     * Added to satisfy requirement Exit Conditions (connection interrupted).
     * @return true if connection is available, false otherwise.
     */
    public boolean checkConnection() {
        // This would normally check a connection.
        // For simplicity, we assume connection is always available.
        return true;
    }

    /**
     * Upload menu data for a specific day.
     * Added to satisfy requirement Flow of Events: 5.
     * @param dayOfWeek The day of the week.
     * @param menuData The menu data to upload.
     * @return true if upload succeeded, false otherwise.
     */
    public boolean uploadMenuData(String dayOfWeek, MenuDataDTO menuData) {
        // Delegate to repository.
        return menuRepository.uploadMenuData(dayOfWeek, menuData);
    }

    /**
     * Added to match sequence diagram message handleConnectionError()
     */
    public void handleConnectionError() {
        if (errorHandler != null) {
            errorHandler.handleConnectionError();
        }
    }

    /**
     * Added to match sequence diagram message handleValidationErrors(errors)
     */
    public void handleValidationErrors(List<String> errors) {
        if (errorHandler != null) {
            errorHandler.handleValidationErrors(errors);
        }
    }

    /**
     * Added to return a DayMenuForm for selected day as per message m9.
     */
    public DayMenuForm getDayMenuFormForSelectedDay(String dayOfWeek) {
        return selectDay(dayOfWeek);
    }

    /**
     * Added to return an error response as per message m22.
     */
    public String getErrorResponse() {
        return "Error occurred while processing request.";
    }

    /**
     * Added to return a cancellation response as per message m57.
     */
    public String getCancellationResponse() {
        return "Operation cancelled successfully.";
    }

    /**
     * Added to return a success response as per message m39.
     */
    public String getSuccessResponse() {
        return "Operation completed successfully.";
    }
}