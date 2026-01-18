package com.example;

/**
 * View for menu editing. Interacts with the controller and the operator.
 */
public class MenuFormView {
    private ModifyMenuController controller;
    private String currentSessionId = "session123"; // Simulated session

    public MenuFormView(ModifyMenuController controller) {
        this.controller = controller;
    }

    public void activateMenuEditing() {
        System.out.println("Menu editing activated.");
        displayWeeklyForm();
    }

    public void displayWeeklyForm() {
        System.out.println("Displaying weekly menu form (list of days).");
    }

    public void displayDayMenuForm(MenuDTO menuDTO) {
        System.out.println("Displaying menu form for " + menuDTO.getDayOfWeek() +
                           " with items: " + menuDTO.getMenuItems());
    }

    public void displayMenuForm(MenuDTO menuDTO) {
        // This method is called in sequence diagram message m14
        // Implementation: display the menu form with data loaded
        displayDayMenuForm(menuDTO);
    }

    public void submitForm(MenuDTO menuDTO) {
        System.out.println("Submitting form for " + menuDTO.getDayOfWeek());
        boolean valid = controller.validateAndSaveMenu(menuDTO);
        if (valid) {
            controller.askForConfirmation();
            boolean confirmed = askForConfirmation();
            if (confirmed) {
                confirmOperation();
            } else {
                cancelOperation();
            }
        } else {
            showErrorNotification();
        }
    }

    public void selectDayOfWeek(String day) {
        System.out.println("Day selected: " + day);
        MenuDTO dto = controller.uploadMenuData(day);
        if (dto != null) {
            displayMenuForm(dto); // Updated to use displayMenuForm
        } else {
            showErrorNotification();
        }
    }

    public boolean askForConfirmation() {
        showConfirmationDialog();
        // Simulate user confirmation (in real UI, wait for user input).
        // For this example, we assume the operator confirms.
        return true;
    }

    public void showConfirmationDialog() {
        System.out.println("Show confirmation dialog: Save menu changes?");
    }

    public void confirmOperation() {
        System.out.println("Operator confirmed. Saving menu...");
        // In a real scenario, we would call controller.saveConfirmedMenu with the current DTO.
        // For simplicity, we just print.
    }

    public void cancelOperation() {
        System.out.println("Operation cancelled by operator.");
        showCancellationMessage();
    }

    public void showSuccessNotification() {
        System.out.println("SUCCESS: Menu saved successfully.");
    }

    public void showErrorNotification() {
        System.out.println("ERROR: Operation failed. Please check data or connection.");
    }

    public void showCancellationMessage() {
        System.out.println("Menu changes were cancelled.");
    }
}