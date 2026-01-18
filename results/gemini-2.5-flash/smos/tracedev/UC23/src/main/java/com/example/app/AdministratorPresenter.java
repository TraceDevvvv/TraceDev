package com.example.app;

import java.util.List;

/**
 * AdministratorPresenter acts as the UI controller/presenter for Administrator actions
 * related to teaching management. It interacts with use cases and serv to handle logic
 * and updates the view (simulated by console output in this example).\
 */
public class AdministratorPresenter {
    private final InsertTeachingUseCase insertTeachingUseCase; // Dependency for inserting new teachings
    private final ErrodatiService errodatiService;             // Dependency for handling error display

    /**
     * Constructs a new AdministratorPresenter.
     *\
     * @param insertTeachingUseCase The use case for inserting teachings.
     * @param errodatiService       The service to display errors.
     */
    public AdministratorPresenter(InsertTeachingUseCase insertTeachingUseCase, ErrodatiService errodatiService) {
        this.insertTeachingUseCase = insertTeachingUseCase;
        this.errodatiService = errodatiService;
    }

    /**
     * Handles the event when the 'New Teaching' button is clicked (R5).\
     * This method orchestrates the display of the teaching form.
     */
    public void onNewTeachingClicked() {
        System.out.println("\n[Presenter] Administrator clicked 'New Teaching' button.");
        // Sequence Diagram: UI -> UI: displayTeachingForm()
        displayTeachingForm(); // Renamed from showTeachingForm()
    }

    /**
     * Displays the teaching creation form to the administrator (R6).
     * In a real application, this would render a UI component. Here, it's a console print.\
     */
    public void displayTeachingForm() { // Renamed from showTeachingForm()
        System.out.println("[Presenter] Displaying 'Create New Teaching' form.");
        System.out.println("Please enter teaching details (e.g., name).");
        // Simulate waiting for user input or showing a prompt.
    }

    /**
     * Handles the event when the administrator wants to save a new teaching.\
     * This method triggers the `InsertTeachingUseCase` and handles its outcomes.
     *\
     * @param teachingName The name of the teaching to be saved.
     */
    public void onSaveTeaching(String teachingName) {
        System.out.println("\n[Presenter] Administrator clicked 'Save' for teaching: '" + teachingName + "'");
        TeachingData teachingData = new TeachingData(teachingName);

        try {
            // Sequence Diagram: UI -> UseCase: execute(teachingData : TeachingData(name))
            insertTeachingUseCase.execute(teachingData);

            // Sequence Diagram: UseCase --> UI: teachingSavedSuccess()
            displaySuccessMessage("New teaching '" + teachingName + "' archived successfully."); // Calls the renamed method
        } catch (InvalidTeachingDataException e) {
            // Sequence Diagram: UseCase --> UI: invalidData(errors : List<String>)
            displayErrorMessages(e.getErrors()); // Calls the renamed method
        } catch (PersistenceException e) {
            // Sequence Diagram: UseCase --> UI: systemError(message : "Connection to archive failed.")
            systemError("Connection to teaching archive failed: " + e.getMessage()); // Calls the renamed method
        } catch (Exception e) {
            // Catch any other unexpected exceptions as a general system error
            systemError("An unexpected system error occurred: " + e.getMessage()); // Calls the renamed method
        }
    }

    /**
     * Displays a success message to the administrator after a teaching is saved (R12, R13).
     * In a real application, this would update a UI notification area.
     *\
     * @param message The success message to display.
     */
    public void displaySuccessMessage(String message) { // Renamed from displayTeachingSavedSuccess()
        System.out.println("[Presenter] Notification: " + message);
        // Sequence Diagram: UI -> Administrator: showNotification("New teaching archived successfully.")
        System.out.println("[Presenter] New teaching archived successfully (Exit Condition).");
    }

    /**
     * Displays validation errors to the administrator (R11, R14).
     * It also invokes the ErrodatiService to activate error screen.
     *\
     * @param errors A list of validation error messages.
     */
    public void displayErrorMessages(List<String> errors) { // Renamed from displayValidationErrors()
        System.out.println("[Presenter] Validation Errors:");
        for (String error : errors) {
            System.err.println(" - " + error);
        }
        // Sequence Diagram: UI -> Errodati: activate(errors)
        errodatiService.activate(errors); // Activates the error display service
        // Sequence Diagram: UI -> Administrator: showErrorScreen(errors : List<String>)
        System.out.println("[Presenter] Errodati use case activated (Exit Condition).");
    }

    /**
     * Displays a general system error message to the administrator (R15).
     *\
     * @param message The system error message to display.
     */
    public void systemError(String message) { // Renamed from displaySystemErrorMessage()
        System.err.println("[Presenter] System Error: " + message);
        // Sequence Diagram: UI -> Administrator: showSystemErrorMessage(...)
        System.out.println("[Presenter] Connection to SMOS server interrupted (Exit Condition).");
    }

    /**
     * Displays an Errodati-specific screen with the given errors.
     * This method is called internally by displayValidationErrors.\
     *\
     * @param errors The list of errors to display on the Errodati screen.
     */
    public void displayErrodatiScreen(List<String> errors) {
        // This method is called by ErrodatiService internally or serves as a more specific display.
        // As per sequence diagram, ErrodatiService.activate is called, then UI 'displays' Errodati screen.
        // For simplicity, we assume ErrodatiService handles its own display, or this is a secondary step.
        System.out.println("[Presenter] Displaying Errodati screen with errors: " + errors);
    }

    /**
     * Handles the event when the administrator cancels teaching creation (R16).\
     * This method stops the current operation and notifies the administrator.
     */
    public void onCancelTeachingCreation() {
        System.out.println("\n[Presenter] Administrator clicked 'Cancel'.");
        System.out.println("[Presenter] Notification: Operation cancelled by Administrator.");
        System.out.println("[Presenter] Administrator has interrupted the operation (Exit Condition).");
    }
}