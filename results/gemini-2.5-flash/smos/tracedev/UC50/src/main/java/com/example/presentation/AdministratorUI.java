package com.example.presentation;

import com.example.application.EnrollmentService;
import com.example.domain.RegistrationRequest;
import java.util.List;

/**
 * Represents the Administrator User Interface.
 * This class handles displaying information to the administrator and
 * forwarding actions to the EnrollmentService.
 */
public class AdministratorUI {
    private final EnrollmentService enrollmentService;

    /**
     * Constructor for AdministratorUI, injecting the EnrollmentService dependency.
     * @param enrollmentService The service responsible for enrollment logic.
     */
    public AdministratorUI(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    /**
     * Displays a list of pending registration requests to the administrator.
     * Renamed from displayPendingRegistrationRequests to displayRegistrationRequests as per class diagram.
     * The parameter `requests` is retained to align with sequence diagram message `displayPendingRequests(pendingRequestsList)`.
     * @param requests The list of pending registration requests to display.
     */
    public void displayRegistrationRequests(List<RegistrationRequest> requests) {
        System.out.println("\n--- Administrator UI: Pending Registration Requests ---");
        if (requests.isEmpty()) {
            System.out.println("No pending registration requests at this time.");
        } else {
            for (RegistrationRequest request : requests) {
                System.out.println("  ID: " + request.getId() +
                                   ", Student: " + request.getStudentName() +
                                   ", Email: " + request.getStudentEmail() +
                                   ", Status: " + request.getStatus());
            }
        }
        System.out.println("---------------------------------------------------\n");
    }

    /**
     * Simulates the administrator clicking an 'Accept' button for a specific request.
     * Renamed from clicksAcceptButton to handleAcceptButtonClick as per class diagram.
     * This method orchestrates the acceptance process by calling the EnrollmentService.
     *
     * Note: The sequence diagram implies the UI directly calls `getPendingRegistrationRequests()`
     * after acceptance. This logic is handled here, following the sequence diagram.
     * The `administratorId` is hardcoded for this example.
     * @param requestId The ID of the registration request to accept.
     */
    public void handleAcceptButtonClick(String requestId) {
        System.out.println("\n[UI] Administrator clicks ACCEPT for request ID: " + requestId);
        String administratorId = "admin-001"; // Hardcoded administrator ID for demonstration

        // The sequence diagram shows UI -> Service -> UI callbacks.
        // For this, the UI instance itself is passed to the service.
        enrollmentService.acceptEnrollment(requestId, administratorId, this);

        // After the acceptance process (whether success or failure notification)
        // the UI then refreshes the list of pending requests.
        // This is explicitly shown in the sequence diagram under the "Successful Enrollment Flow"
        // after the enrollmentAcceptedConfirmation.
        // Also needs to happen after "Connection to the SMOS server IS interrupted" for consistency.
        System.out.println("[UI] Refreshing pending registration requests list...");
        List<RegistrationRequest> currentPendingRequests = enrollmentService.getPendingRegistrationRequests();
        displayRegistrationRequests(currentPendingRequests);
    }

    /**
     * Displays a confirmation message indicating that an enrollment was successfully accepted.
     * Added to satisfy requirement R7.
     */
    public void enrollmentAcceptedConfirmation() {
        System.out.println("\n[UI] CONFIRMATION: Student enrollment successfully accepted!");
    }

    /**
     * Displays an error message indicating that an enrollment failed.
     * Added to satisfy requirement R8.
     * @param errorMessage The message describing why the enrollment failed.
     */
    public void enrollmentFailed(String errorMessage) {
        System.err.println("\n[UI] ERROR: Student enrollment failed! Reason: " + errorMessage);
    }

    /**
     * Displays a generic error message to the user.
     * Added to satisfy requirement R8.
     * @param errorMessage The message to display.
     */
    public void displayErrorMessage(String errorMessage) {
        System.err.println("\n[UI] DISPLAY ERROR: " + errorMessage);
    }
}