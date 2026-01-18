package com.example.delay;

import java.util.Date;

/**
 * Boundary class (Controller) that handles UI interactions for delay changes.
 */
public class DelayChangeController {
    private DelayChangeUseCase useCase;
    private DelayRepository repository;

    /**
     * Constructor with dependency injection.
     * @param useCase The use case handler.
     * @param repository The delay repository.
     */
    public DelayChangeController(DelayChangeUseCase useCase, DelayRepository repository) {
        this.useCase = useCase;
        this.repository = repository;
    }

    /**
     * Displays delay data for a given date.
     * @param date The date to query.
     * @return DelayDTO containing date and delay value.
     */
    public DelayDTO displayDelayData(Date date) {
        Delay delay = repository.findByDate(date);
        if (delay == null) {
            // If no delay exists, create a default one with zero delay.
            delay = new Delay(date, 0);
        }
        return new DelayDTO(delay.getDate(), delay.getDelay());
    }

    /**
     * Handles edit request from administrator.
     * @param delayDTO The DTO with new delay value.
     */
    public void handleEditRequest(DelayDTO delayDTO) {
        // This method would typically be called after editDelay and before clickSave.
        System.out.println("Controller handling edit request for date: " + delayDTO.getDate());
    }

    /**
     * Shows success feedback to the administrator.
     * @param message The success message.
     */
    public void showSuccessFeedback(String message) {
        System.out.println("SUCCESS: " + message);
    }

    /**
     * Shows error feedback to the administrator.
     * @param message The error message.
     */
    public void showErrorFeedback(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Interrupts the current operation (e.g., user cancels).
     * Calls cancel on the handler to propagate interruption.
     */
    public void interrupt() {
        if (useCase instanceof ChangeDelayCommandHandler) {
            ((ChangeDelayCommandHandler) useCase).cancel();
        }
        showErrorFeedback("Operation cancelled");
    }

    /**
     * Updates the screen after date selection (Flow of Events #1).
     * @param date The selected date.
     */
    public void updateScreen(Date date) {
        // In a real UI, this would refresh the display with data for the given date.
        System.out.println("Screen updated for date: " + date);
    }

    /**
     * Simulates the sequence starting with date selection.
     * This method triggers the main flow as per sequence diagram.
     * @param date The selected date.
     */
    public void onDateSelected(Date date) {
        // Entry condition check: prior use case performed (assumed satisfied).
        updateScreen(date);
        DelayDTO dto = displayDelayData(date);
        // In a real system, the DTO would be displayed on UI, then user would edit and save.
        System.out.println("Delay data displayed: " + dto);
    }

    /**
     * Simulates the save action after editing.
     * @param command The change delay command built from user input.
     */
    public void onSave(ChangeDelayCommand command) {
        Result result = useCase.execute(command);
        if (result.isSuccess()) {
            showSuccessFeedback("Delay updated");
        } else {
            showErrorFeedback(result.getMessage());
        }
    }
}