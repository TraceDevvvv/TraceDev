package com.example.viewrequestssubscription;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main application class to demonstrate the "ViewRequestsSubscription" use case.
 * This class simulates an administrator's interaction with the system to view
 * pending registration requests.
 */
public class ViewRequestsSubscriptionApp {

    private static final Logger LOGGER = Logger.getLogger(ViewRequestsSubscriptionApp.class.getName());

    /**
     * The main method, entry point for the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // 1. Initialize the system components
        // In a real application, these would typically be managed by a dependency injection framework.
        RegistrationRequestRepository repository = new InMemoryRegistrationRequestRepository();
        RegistrationRequestService service = new RegistrationRequestService(repository);

        LOGGER.log(Level.INFO, "Application started. Initializing sample data...");

        // 2. Simulate initial data (some pending, some approved requests)
        // This sets up the state before the administrator views the requests.
        populateSampleData(repository);

        LOGGER.log(Level.INFO, "Sample data populated.");

        // 3. Simulate Administrator Login (Precondition)
        // For this demonstration, we assume the administrator is successfully logged in.
        boolean isAdminLoggedIn = true;
        if (!isAdminLoggedIn) {
            LOGGER.log(Level.WARNING, "Administrator not logged in. Cannot proceed with viewing requests.");
            return;
        }
        LOGGER.log(Level.INFO, "Administrator is logged in.");

        // 4. Simulate Administrator clicks "View list request list" button (Precondition)
        LOGGER.log(Level.INFO, "Administrator clicks 'View list request list' button.");

        // 5. System displays the list of registrations yet to be activated (Event Sequence 1)
        displayPendingRegistrationRequests(service);

        // 6. Demonstrate scenario with no pending requests
        LOGGER.log(Level.INFO, "\n--- Demonstrating scenario with no pending requests ---");
        // Clear existing pending requests or add only approved ones
        clearPendingRequests(repository);
        populateOnlyApprovedData(repository);
        displayPendingRegistrationRequests(service);

        // 7. Postconditions:
        // "The user is viewing the list of requests for registration to the system by students"
        // This is simulated by the console output.
        // "the user interrupts the connection to the SMOS server interrupted"
        // This is an external event that cannot be directly simulated in this simple console app.
        // However, the service layer's error handling (try-catch and logging) is designed
        // to gracefully handle potential data access issues, which could arise from
        // network interruptions or database unavailability.

        LOGGER.log(Level.INFO, "Application finished.");
    }

    /**
     * Populates the repository with sample {@link RegistrationRequest} data,
     * including both PENDING and APPROVED requests.
     *
     * @param repository The repository to populate.
     */
    private static void populateSampleData(RegistrationRequestRepository repository) {
        // Pending requests
        repository.save(new RegistrationRequest(
                UUID.randomUUID().toString(), "S001", "Alice Smith", "alice.s@example.com",
                LocalDateTime.now().minusDays(5), RegistrationRequest.RequestStatus.PENDING));
        repository.save(new RegistrationRequest(
                UUID.randomUUID().toString(), "S002", "Bob Johnson", "bob.j@example.com",
                LocalDateTime.now().minusDays(3), RegistrationRequest.RequestStatus.PENDING));
        repository.save(new RegistrationRequest(
                UUID.randomUUID().toString(), "S003", "Charlie Brown", "charlie.b@example.com",
                LocalDateTime.now().minusHours(10), RegistrationRequest.RequestStatus.PENDING));

        // Approved requests (should not appear in the pending list)
        repository.save(new RegistrationRequest(
                UUID.randomUUID().toString(), "S004", "Diana Prince", "diana.p@example.com",
                LocalDateTime.now().minusDays(7), RegistrationRequest.RequestStatus.APPROVED));
        repository.save(new RegistrationRequest(
                UUID.randomUUID().toString(), "S005", "Eve Adams", "eve.a@example.com",
                LocalDateTime.now().minusDays(1), RegistrationRequest.RequestStatus.APPROVED));
    }

    /**
     * Clears all requests from the repository.
     * @param repository The repository to clear.
     */
    private static void clearPendingRequests(RegistrationRequestRepository repository) {
        List<RegistrationRequest> allRequests = repository.findAll();
        for (RegistrationRequest request : allRequests) {
            repository.deleteById(request.getRequestId());
        }
        LOGGER.log(Level.INFO, "All existing requests cleared from repository.");
    }

    /**
     * Populates the repository with only approved requests to simulate no pending requests.
     * @param repository The repository to populate.
     */
    private static void populateOnlyApprovedData(RegistrationRequestRepository repository) {
        repository.save(new RegistrationRequest(
                UUID.randomUUID().toString(), "S006", "Frank White", "frank.w@example.com",
                LocalDateTime.now().minusDays(2), RegistrationRequest.RequestStatus.APPROVED));
        repository.save(new RegistrationRequest(
                UUID.randomUUID().toString(), "S007", "Grace Black", "grace.b@example.com",
                LocalDateTime.now().minusDays(4), RegistrationRequest.RequestStatus.APPROVED));
        LOGGER.log(Level.INFO, "Repository populated with only approved requests.");
    }

    /**
     * Calls the service to get pending requests and prints them to the console.
     *
     * @param service The {@link RegistrationRequestService} to use.
     */
    private static void displayPendingRegistrationRequests(RegistrationRequestService service) {
        LOGGER.log(Level.INFO, "Fetching pending registration requests...");
        List<RegistrationRequest> pendingRequests = service.getPendingRegistrationRequests();

        if (pendingRequests.isEmpty()) {
            System.out.println("----------------------------------------------------");
            System.out.println("No pending registration requests found.");
            System.out.println("----------------------------------------------------");
        } else {
            System.out.println("----------------------------------------------------");
            System.out.println("List of Registrations Yet To Be Activated:");
            System.out.println("----------------------------------------------------");
            for (int i = 0; i < pendingRequests.size(); i++) {
                RegistrationRequest request = pendingRequests.get(i);
                System.out.printf("%d. Request ID: %s, Student Name: %s, Email: %s, Date: %s%n",
                        (i + 1), request.getRequestId(), request.getStudentName(),
                        request.getStudentEmail(), request.getRequestDate().toLocalDate());
            }
            System.out.println("----------------------------------------------------");
        }
    }
}