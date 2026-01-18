package com.example.delaysystem;

import java.util.Date;
import java.util.UUID; // For generating unique IDs

/**
 * The ViewModel for the Delay Modification feature.
 * This class acts as an intermediary between the View and the Use Case Controller,
 * handling data preparation and command forwarding.
 */
public class DelayModificationViewModel {

    private DelayModificationUseCaseController useCaseController;
    private DelayModificationView view; // Reference to the view for callbacks

    /**
     * Constructor for DelayModificationViewModel.
     *
     * @param useCaseController The controller for use case execution.
     */
    public DelayModificationViewModel(DelayModificationUseCaseController useCaseController) {
        this.useCaseController = useCaseController;
    }

    /**
     * Sets the view associated with this ViewModel.
     *
     * @param view The DelayModificationView instance.
     */
    public void setView(DelayModificationView view) {
        this.view = view;
    }

    /**
     * Loads scheduling information for a specific date.
     * This method orchestrates the call to the use case controller and updates the view.
     *
     * @param date The date for which to load scheduling information.
     * @return A DTO containing scheduling information, or null if loading fails.
     */
    public SchedulingInfoDTO loadSchedulingInfo(Date date) {
        System.out.println("[ViewModel] Loading scheduling info for date: " + date);
        SchedulingInfoDTO schedulingInfoDTO = useCaseController.fetchSchedulingInfoByDate(date);

        if (schedulingInfoDTO == null) {
            System.err.println("[ViewModel] Received null scheduling info from UseCaseController. Showing error message.");
            // Corresponds to "ViewModel -> View : showErrorMessage(...)"
            if (view != null) {
                view.showErrorMessage("Failed to load scheduling data. Check server connection or data availability.");
            }
        } else {
            System.out.println("[ViewModel] Scheduling info loaded successfully for date: " + date);
            // Corresponds to "ViewModel -> View : displaySchedulingInfo(...)"
            if (view != null) {
                view.displaySchedulingInfo(schedulingInfoDTO);
            }
        }
        return schedulingInfoDTO; // Return for testing/further processing
    }

    /**
     * Saves delay data.
     * This method orchestrates the call to the use case controller and provides feedback to the view.
     *
     * @param delayToSave The DelayDTO containing the data to save.
     * @return true if the delay data was saved successfully, false otherwise.
     */
    public boolean saveDelay(DelayDTO delayToSave) {
        System.out.println("[ViewModel] Saving delay: " + delayToSave);

        // The sequence diagram shows `ViewModel -> UseCaseController : updateSchedulingDelay(newDelayDTO : DelayDTO)`
        // The `newDelayDTO` implies the DTO is created or prepared at this layer.
        // For existing delays, the DTO would come from the view. For new, it could be created here.
        // Assuming `delayToSave` is the DTO prepared by the view.
        if (delayToSave.id == null || delayToSave.id.isEmpty()) {
            // If it's a new delay, generate an ID (<<create>> DelayDTO concept)
            // The class diagram shows DelayDTO being created by ViewModel. This is one place.
            delayToSave.id = "D" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            System.out.println("[ViewModel] Generated new ID for delay: " + delayToSave.id);
        }

        boolean success = useCaseController.updateSchedulingDelay(delayToSave);

        if (success) {
            System.out.println("[ViewModel] Delay saved successfully. Showing success message.");
            // Corresponds to "ViewModel -> View : showSuccessMessage(...)"
            if (view != null) {
                view.showSuccessMessage("Delay updated successfully.");
            }
        } else {
            System.err.println("[ViewModel] Failed to save delay. Showing error message.");
            // Corresponds to "ViewModel -> View : showErrorMessage(...)"
            if (view != null) {
                view.showErrorMessage("Failed to save delay. Check server connection or input data.");
            }
        }
        return success;
    }
}