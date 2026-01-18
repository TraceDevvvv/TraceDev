package com.activeconvention.ui;

import com.activeconvention.model.Convention;
import com.activeconvention.model.ConventionRequest;
import com.activeconvention.service.ConventionService;
import com.activeconvention.util.EtourConnectionException;
import com.activeconvention.util.Logger;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * ConventionController class for handling user interactions and updating the view and model.
 * This class acts as the 'Controller' in the MVC pattern, mediating between the ConventionView
 * and the ConventionService.
 */
public class ConventionController {

    private ConventionService conventionService;
    private ConventionView conventionView;

    /**
     * Constructor for ConventionController.
     *
     * @param conventionService The service layer for convention-related operations.
     * @param conventionView    The view layer for displaying UI and handling user input.
     */
    public ConventionController(ConventionService conventionService, ConventionView conventionView) {
        this.conventionService = conventionService;
        this.conventionView = conventionView;
        Logger.logInfo("ConventionController initialized.");
    }

    /**
     * Initializes the controller by setting up action listeners for the view's components
     * and enabling the activation function.
     */
    public void init() {
        // Enable the activation function in the view
        conventionView.enableActivationFunction();

        // Load and display initial pending convention requests
        loadPendingConventionRequests();

        // Add action listeners to view components
        conventionView.addViewDetailsListener(new ViewDetailsListener());
        conventionView.addActivateConventionListener(new ActivateConventionListener());
        conventionView.addCancelListener(new CancelListener());
        conventionView.addApproveActivationCheckboxListener(new ApproveActivationCheckboxListener());
        conventionView.addConventionRequestTableSelectionListener(new ConventionRequestTableSelectionListener());

        Logger.logInfo("ConventionController initialized with listeners.");
    }

    /**
     * Loads pending convention requests from the service and displays them in the view.
     */
    private void loadPendingConventionRequests() {
        try {
            List<ConventionRequest> requests = conventionService.getPendingConventionRequests();
            conventionView.showConventionList(requests);
            Logger.logInfo("Pending convention requests loaded and displayed.");
        } catch (Exception e) {
            Logger.logError("Failed to load pending convention requests.", e);
            conventionView.showError("Failed to load pending convention requests: " + e.getMessage());
        }
    }

    /**
     * Loads the data for a specific convention request and displays it in the form.
     *
     * @param requestId The ID of the convention request to load.
     */
    public void loadAndDisplayConventionData(String requestId) {
        try {
            // First, get the ConventionRequest to find the Convention ID
            ConventionRequest request = conventionService.getConventionRequest(requestId);
            if (request == null) {
                conventionView.showError("Convention request with ID " + requestId + " not found.");
                Logger.logError("Convention request not found: " + requestId, null);
                return;
            }

            // Then, get the full Convention details using the Convention ID
            Convention convention = conventionService.getConventionDetails(request.getConventionId());
            if (convention != null) {
                conventionView.displayConventionForm(convention);
                Logger.logInfo("Convention data loaded and displayed for request ID: " + requestId);
            } else {
                conventionView.showError("Convention details for ID " + request.getConventionId() + " not found.");
                Logger.logError("Convention details not found for ID: " + request.getConventionId(), null);
            }
        } catch (Exception e) {
            Logger.logError("Error loading convention data for request ID: " + requestId, e);
            conventionView.showError("Error loading convention data: " + e.getMessage());
        }
    }

    /**
     * Activates a convention after user confirmation.
     *
     * @param convention The Convention object to activate.
     */
    public void activateConvention(Convention convention) {
        if (convention == null) {
            conventionView.showError("No convention selected for activation.");
            Logger.logError("Attempted to activate null convention.", null);
            return;
        }

        // Step 4: Asks for confirmation of the activation.
        if (conventionView.getConfirmation()) {
            // Step 5: Confirm the operation.
            // Step 6: Processing the request.
            try {
                boolean success = conventionService.activateConvention(convention);
                if (success) {
                    conventionView.showSuccess("Convention " + convention.getId() + " activated successfully.");
                    Logger.logInfo("Convention activated: " + convention.getId());
                    loadPendingConventionRequests(); // Refresh the list after activation
                } else {
                    conventionView.showError("Failed to activate convention " + convention.getId() + ". Please check data.");
                    Logger.logError("Activation failed for convention: " + convention.getId() + " due to service logic.", null);
                }
            } catch (EtourConnectionException e) {
                // Handle ETOUR server connection interruption
                handleEtourConnectionInterruption(e);
            } catch (Exception e) {
                Logger.logError("Unexpected error during convention activation for " + convention.getId(), e);
                conventionView.showError("An unexpected error occurred: " + e.getMessage());
            }
        } else {
            conventionView.showError("Convention activation cancelled by user.");
            Logger.logInfo("Convention activation cancelled by user for " + convention.getId());
        }
    }

    /**
     * Handles the interruption of the connection to the ETOUR server.
     *
     * @param e The EtourConnectionException that occurred.
     */
    public void handleEtourConnectionInterruption(EtourConnectionException e) {
        Logger.logError("ETOUR server connection interrupted during activation.", e);
        conventionView.showError("Error: ETOUR server connection lost. Please try again later. Details: " + e.getMessage());
    }

    /**
     * ActionListener for the 'View Details' button.
     * Loads and displays the details of the selected convention request.
     */
    private class ViewDetailsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ConventionRequest selectedRequest = conventionView.getSelectedConventionRequest();
            if (selectedRequest != null) {
                loadAndDisplayConventionData(selectedRequest.getRequestId());
            } else {
                conventionView.showError("Please select a convention request from the list.");
            }
        }
    }

    /**
     * ActionListener for the 'Activate Convention' button.
     * Initiates the activation process for the currently displayed convention.
     */
    private class ActivateConventionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Convention conventionToActivate = conventionView.getSelectedConvention();
            if (conventionToActivate != null) {
                activateConvention(conventionToActivate);
            } else {
                conventionView.showError("No convention details are currently displayed for activation.");
            }
        }
    }

    /**
     * ActionListener for the 'Cancel' button.
     * Resets the form and clears displayed convention details.
     */
    private class CancelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Reset the form in the view
            conventionView.displayConventionForm(new Convention("", "", "", "", "", "")); // Clear fields
            conventionView.setActivateConventionButtonEnabled(false);
            conventionView.isApproveActivationChecked(); // This call doesn't do anything, should be reset
            // A better way to reset the form is needed in ConventionView, or call a specific reset method.
            // For now, we'll simulate a reset by clearing fields and disabling buttons.
            conventionView.showError("Operation cancelled."); // Use error for cancellation feedback
            Logger.logInfo("Convention details view cancelled.");
        }
    }

    /**
     * ActionListener for the 'Approve Activation' checkbox.
     * Enables/disables the 'Activate Convention' button based on checkbox state.
     */
    private class ApproveActivationCheckboxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            conventionView.setActivateConventionButtonEnabled(conventionView.isApproveActivationChecked());
        }
    }

    /**
     * ListSelectionListener for the convention request table.
     * Enables/disables the 'View Details' button based on whether a row is selected.
     */
    private class ConventionRequestTableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) { // Ensure event is not a "temporary" selection change
                boolean rowSelected = conventionView.getSelectedConventionRequest() != null;
                conventionView.setViewDetailsButtonEnabled(rowSelected);
            }
        }
    }
}