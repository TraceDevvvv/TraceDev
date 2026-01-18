package com.example.reportcard;

import java.util.Objects;

/**
 * Controller responsible for handling user requests related to report card deletion.
 * It coordinates interactions between the UI (ConfirmationPresenter), business logic
 * (ReportCardEliminationService), and display logic (ReportCardDisplayService).
 */
public class ReportCardController {
    private final ReportCardEliminationService reportCardEliminationService;
    private final ConfirmationPresenter confirmationPresenter;
    private final ReportCardDisplayService reportCardDisplayService; // Added to satisfy REQ-002

    // For simplicity, assuming the acting user is an Administrator directly.
    // In a real app, this would be retrieved from a session or security context.
    private final Administrator actingAdministrator;

    /**
     * Constructs a ReportCardController with its required dependencies.
     *
     * @param reportCardEliminationService The service for eliminating report cards.
     * @param confirmationPresenter The presenter for UI confirmations and messages.
     * @param reportCardDisplayService The service for displaying report cards and managing selection context.
     * @param administrator The administrator interacting with the system (for authorization context).
     */
    public ReportCardController(
            ReportCardEliminationService reportCardEliminationService,
            ConfirmationPresenter confirmationPresenter,
            ReportCardDisplayService reportCardDisplayService,
            Administrator administrator) {
        this.reportCardEliminationService = Objects.requireNonNull(reportCardEliminationService, "ReportCardEliminationService cannot be null");
        this.confirmationPresenter = Objects.requireNonNull(confirmationPresenter, "ConfirmationPresenter cannot be null");
        this.reportCardDisplayService = Objects.requireNonNull(reportCardDisplayService, "ReportCardDisplayService cannot be null");
        this.actingAdministrator = Objects.requireNonNull(administrator, "Administrator context cannot be null");
    }

    /**
     * Initiates the report card deletion process.
     * This method corresponds to the "requestReportCardDeletion" call in the sequence diagram.
     * Entry Conditions: Administrator logged in, Report Card displayed, Delete button clicked.
     *
     * @param reportCardId The ID of the report card requested for deletion.
     */
    public void requestReportCardDeletion(String reportCardId) {
        System.out.println("\n[ReportCardController] Administrator requests deletion for Report Card ID: " + reportCardId);

        // Pre-condition check (as per sequence diagram note: "Logged in as Administrator")
        if (!actingAdministrator.isLoggedIn() || !actingAdministrator.hasRole("ADMIN")) {
            System.out.println("[ReportCardController] Authorization denied: User is not an active administrator.");
            confirmationPresenter.displaySuccessMessage("Deletion failed: Unauthorized access.");
            return;
        }

        // Display a confirmation form to the administrator
        confirmationPresenter.displayConfirmationForm("Confirm deletion of report card " + reportCardId + "?");
        // The actual confirmation (yes/no) will come through confirmReportCardDeletion method
    }

    /**
     * Confirms or cancels the report card deletion based on administrator input.
     * This method corresponds to the "confirmReportCardDeletion" call in the sequence diagram.
     *
     * @param reportCardId The ID of the report card to be deleted.
     * @param confirmation True if the administrator confirmed deletion, false otherwise.
     */
    public void confirmReportCardDeletion(String reportCardId, boolean confirmation) {
        System.out.println("[ReportCardController] Administrator confirms deletion for Report Card ID: " + reportCardId + ", Confirmation: " + confirmation);

        if (!actingAdministrator.isLoggedIn() || !actingAdministrator.hasRole("ADMIN")) {
            System.out.println("[ReportCardController] Authorization denied: User is not an active administrator.");
            confirmationPresenter.displaySuccessMessage("Deletion failed: Unauthorized access.");
            return;
        }

        if (confirmation) {
            // Proceed with elimination if confirmed
            boolean success = reportCardEliminationService.eliminateReportCard(reportCardId);

            if (success) {
                // If deletion was successful, display success message and update UI
                confirmationPresenter.displaySuccessMessage("Report card deleted successfully.");
                confirmationPresenter.displayClassListForm();
            } else {
                // If deletion failed (e.g., report card not found, persistence error)
                System.err.println("[ReportCardController] Failed to delete report card " + reportCardId + " via elimination service.");
                confirmationPresenter.displaySuccessMessage("Failed to delete report card. Please try again.");
                // Optionally display the report card again or go back to a safer state
                reportCardDisplayService.displayReportCard(reportCardId); // Display current one if failed
            }
        } else {
            // Administrator cancelled deletion
            System.out.println("[ReportCardController] Report card deletion for " + reportCardId + " cancelled by administrator.");
            confirmationPresenter.displaySuccessMessage("Report card deletion cancelled.");
            // Optionally redisplay the current report card or return to previous state
            reportCardDisplayService.displayReportCard(reportCardId);
        }
    }
}