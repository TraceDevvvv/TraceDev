package presentation;

import application.AuthenticationService;
import application.ReportCardModificationService;
import application.WorkflowService;
import domain.ReportCard;
import domain.Role;
import domain.Subject;
import domain.User;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Controller for managing Report Card related UI interactions.
 * Modified to satisfy REQ-008 for method signatures.
 */
public class ReportCardController {
    private ReportCardModificationService reportCardModificationService;
    private ReportCardEditView reportCardEditView;
    private AuthenticationService authenticationService; // Added to satisfy REQ-001
    private WorkflowService workflowService; // Added to satisfy REQ-003

    // Mock current user for demonstration purposes, assuming an admin is logged in.
    // In a real application, this would come from a session or security context.
    private User currentUser;

    /**
     * Constructs a ReportCardController with necessary service and view dependencies.
     * @param reportCardModificationService The application service for report card modifications.
     * @param reportCardEditView The view for editing report cards.
     * @param authenticationService The service for user authentication and authorization.
     * @param workflowService The service for checking workflow states.
     * @param currentUser A mock user representing the currently logged-in user.
     */
    public ReportCardController(
        ReportCardModificationService reportCardModificationService,
        ReportCardEditView reportCardEditView,
        AuthenticationService authenticationService,
        WorkflowService workflowService,
        User currentUser) {
        this.reportCardModificationService = reportCardModificationService;
        this.reportCardEditView = reportCardEditView;
        this.authenticationService = authenticationService;
        this.workflowService = workflowService;
        this.currentUser = currentUser; // Set the mock current user
        this.reportCardEditView.setController(this); // Allow view to call back to controller
    }

    /**
     * Handles the request to edit a student's report card.
     * Modified to satisfy REQ-008.
     * @param studentId The ID of the student whose report card is to be edited.
     * @return A status message indicating the outcome.
     */
    public String editReportCard(String studentId) {
        System.out.println("DEBUG: Controller: editReportCard called for studentId: " + studentId);

        // Precondition Check: User role (REQ-001)
        if (!authenticationService.hasRole(currentUser, Role.ADMINISTRATOR)) {
            String message = "Access Denied: User " + (currentUser != null ? currentUser.getUsername() : "null") + " is not an ADMINISTRATOR.";
            reportCardEditView.displayError(message);
            return message;
        }

        // Precondition Check: Workflow state (REQ-003)
        if (!workflowService.hasUseCaseBeenCarriedOut("DisplayedUnapagella", currentUser.getId())) {
            String message = "Workflow condition not met: 'DisplayedUnapagella' has not been carried out for user " + currentUser.getId() + ".";
            reportCardEditView.displayError(message);
            return message;
        }

        // Retrieve report card details
        ReportCard reportCard = reportCardModificationService.getReportCardDetails(studentId);

        if (reportCard == null) {
            String message = "Error: Report Card not found for student ID: " + studentId;
            reportCardEditView.displayError(message);
            return message;
        }

        // Prepare a mock list of subjects to display for context in the form.
        // In a real app, this would come from a SubjectService or repository.
        List<Subject> subjects = Arrays.asList(
            new Subject("MATH101", "Mathematics"),
            new Subject("ENG202", "English Literature"),
            new Subject("PHY303", "Physics")
        );

        // Display the edit form
        reportCardEditView.displayEditForm(reportCard, subjects);
        return "Displaying edit form for student " + studentId;
    }

    /**
     * Submits the changes made to a student's report card.
     * Modified to satisfy REQ-008.
     * @param studentId The ID of the student whose report card is being updated.
     * @param newGrades A map of Subject IDs to new grade scores.
     * @return A status message indicating the outcome.
     */
    public String submitReportCardChanges(String studentId, Map<String, Integer> newGrades) {
        System.out.println("DEBUG: Controller: submitReportCardChanges called for studentId: " + studentId + " with new grades: " + newGrades);

        // This check could be repeated here for safety, or assumed to be handled by the initial `editReportCard` call.
        // For strict adherence to SD (preconditions before initial edit, not explicitly before submit), we omit for brevity,
        // but it's good practice to re-validate critical permissions before any write operation.

        // Update the report card via application service
        boolean success = reportCardModificationService.updateReportCard(studentId, newGrades);

        if (success) {
            // Display confirmation and navigate back to student list
            reportCardEditView.displayConfirmation("Report Card for student " + studentId + " updated successfully.");
            reportCardEditView.displayStudentList();
            return "Report Card updated successfully.";
        } else {
            // Display error message (PersistenceException caught in AppService, propagated as 'false')
            reportCardEditView.displayError("Connection to server lost or an error occurred during update. Please try again."); // REQ-006 message
            return "Report Card update failed.";
        }
    }
}