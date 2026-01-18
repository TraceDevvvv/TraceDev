package com.example.delaysystem;

import java.util.Date;
import java.util.Calendar; // For creating Date objects easily

/**
 * The View component for modifying delays.
 * This class simulates a UI that interacts with the ViewModel.
 */
public class DelayModificationView {

    private DelayModificationViewModel viewModel; // CD-TRACE: viewModel

    /**
     * Constructor for DelayModificationView.
     *
     * @param viewModel The ViewModel to interact with.
     */
    public DelayModificationView(DelayModificationViewModel viewModel) {
        this.viewModel = viewModel;
        // Set this view as the callback target for the ViewModel
        this.viewModel.setView(this);
    }

    /**
     * Simulates an administrator selecting a date to view/modify scheduling information.
     * Corresponds to `Admin -> View : selectDate(selectedDate : Date)` in sequence diagram.
     *
     * @param selectedDate The date chosen by the administrator.
     */
    public void onDateSelected(Date selectedDate) {
        System.out.println("\n--- Admin selects date: " + selectedDate + " ---");
        System.out.println("[View] Calling ViewModel to load scheduling info for date: " + selectedDate);
        // Corresponds to `View -> ViewModel : loadSchedulingInfo(selectedDate : Date)`
        viewModel.loadSchedulingInfo(selectedDate);
    }

    /**
     * Displays scheduling information on the UI.
     * Corresponds to `ViewModel -> View : displaySchedulingInfo(schedulingInfoDTO)` in sequence diagram.
     *
     * @param info The DTO containing scheduling information to display.
     */
    public void displaySchedulingInfo(SchedulingInfoDTO info) {
        System.out.println("\n[View] Displaying Scheduling Information for " + info.date + ":");
        if (info.delays.isEmpty()) {
            System.out.println("  No delays scheduled for this date.");
        } else {
            for (DelayDTO delay : info.delays) {
                System.out.println("  - ID: " + delay.id + ", Duration: " + delay.durationMinutes + " min, Reason: " + delay.reason);
            }
        }
        System.out.println("--- Display complete ---");
    }

    /**
     * Simulates getting edited delay data from the UI.
     * In a real application, this would parse user input fields.
     * For demonstration, it returns a hardcoded DTO that would be 'edited' by the Admin.
     *
     * @return A DelayDTO representing the edited data.
     */
    public DelayDTO getEditedDelayData() { // CD-TRACE: getEditedDelayData
        // This method simulates the UI input.
        // For the sequence diagram's "Admin edits the delay", let's return a specific edited delay.
        // The original sequence diagram had `Admin -> View : editDelayDetails(newDelayDTO : DelayDTO)` which was removed.
        // So, this method serves to provide the DTO that would have been "edited" on the screen.
        Calendar cal = Calendar.getInstance();
        cal.set(2023, Calendar.OCTOBER, 26, 0, 0, 0); // Date for which a delay is to be edited/added
        Date targetDate = cal.getTime();

        // Simulate an existing delay being edited, or a new one being prepared.
        // Let's assume we are editing D001 from Oct 26, 2023.
        return new DelayDTO("D001", targetDate, 75, "Revised Technical Issue");
        // Or for a new delay: return new DelayDTO(null, targetDate, 45, "New Operational Adjustment");
    }

    /**
     * Simulates the administrator clicking the save button.
     * Corresponds to `Admin -> View : clickSaveButton()` in sequence diagram.
     */
    public void onSaveButtonClick() { // CD-TRACE: onSaveButtonClick
        System.out.println("\n--- Admin clicks SAVE button ---");
        System.out.println("[View] Getting edited delay data from UI fields.");
        DelayDTO editedDelay = getEditedDelayData(); // Get data from simulated UI fields
        if (editedDelay != null) {
            System.out.println("[View] Calling ViewModel to save delay: " + editedDelay);
            // Corresponds to `View -> ViewModel : saveDelay(newDelayDTO : DelayDTO)`
            viewModel.saveDelay(editedDelay);
        } else {
            showErrorMessage("No delay data to save.");
        }
    }

    /**
     * Displays an error message to the user.
     * Corresponds to `ViewModel -> View : showErrorMessage(message : String)` in sequence diagram.
     *
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.err.println("\n[View] ERROR: " + message);
        System.out.println("--- Error message displayed to Admin ---");
    }

    /**
     * Displays a success message to the user.
     * Corresponds to `ViewModel -> View : showSuccessMessage(message : String)` in sequence diagram.
     *
     * @param message The success message to display.
     */
    public void showSuccessMessage(String message) {
        System.out.println("\n[View] SUCCESS: " + message);
        System.out.println("--- Success message displayed to Admin ---");
    }

    /**
     * Handles the cancel button click event.
     * Added to satisfy R12 in the class diagram. No specific sequence for this.
     */
    public void onCancelButtonClick() {
        System.out.println("\n[View] Admin clicked Cancel. Operation aborted.");
    }
}