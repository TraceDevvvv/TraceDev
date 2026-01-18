package com.example.smos.app;

import com.example.smos.controller.Administrator;
import com.example.smos.model.RegistrationRequest;
import com.example.smos.model.Student;
import com.example.smos.server.SMOSServer;

import java.util.List;
import java.util.Optional;

/**
 * Main application class to demonstrate the "Reject Enrollment Student" use case.
 * This class simulates the interaction between an Administrator, Registration Requests,
 * Students, and the SMOS Server to illustrate the rejection process.
 */
public class RejectEnrollmentStudentApp {

    public static void main(String[] args) {
        System.out.println("--- Starting Reject Enrollment Student Use Case Demonstration ---");

        // 1. Initialize SMOS Server
        SMOSServer smosServer = new SMOSServer();

        // 2. Initialize Administrator, providing the SMOS Server dependency
        Administrator administrator = new Administrator(smosServer);

        // 3. Create some Student objects
        Student student1 = new Student("S001", "Alice Smith", "alice.smith@example.com");
        Student student2 = new Student("S002", "Bob Johnson", "bob.j@example.com");
        Student student3 = new Student("S003", "Charlie Brown", "charlie.b@example.com");
        Student student4 = new Student("S004", "Diana Prince", "diana.p@example.com");

        // 4. Create some RegistrationRequest objects
        RegistrationRequest req1 = new RegistrationRequest(student1);
        RegistrationRequest req2 = new RegistrationRequest(student2);
        RegistrationRequest req3 = new RegistrationRequest(student3);
        RegistrationRequest req4 = new RegistrationRequest(student4);

        // 5. Add these requests to the Administrator's managed list (simulating requests coming into the system)
        System.out.println("\n--- Administrator receives new registration requests ---");
        administrator.addRegistrationRequest(req1);
        administrator.addRegistrationRequest(req2);
        administrator.addRegistrationRequest(req3);
        administrator.addRegistrationRequest(req4);

        // --- Preconditions ---
        // • The user has already done the case of use "View Registration Requests"
        // • The user clicks on the "Reject" button associated with a registration request

        System.out.println("\n--- Administrator views pending registration requests (Precondition) ---");
        List<RegistrationRequest> pendingRequests = administrator.viewPendingRegistrationRequests();
        if (pendingRequests.isEmpty()) {
            System.out.println("No pending requests to display.");
        } else {
            System.out.println("Pending Requests:");
            pendingRequests.forEach(System.out::println);
        }

        // Simulate the administrator choosing to reject req2 (Bob Johnson)
        String requestIdToReject = req2.getRequestId();
        System.out.println("\n--- Administrator clicks 'Reject' button for request ID: " + requestIdToReject + " ---");

        // --- Events Sequence ---
        // User: 1. Eliminates the registration request to the system.
        // System: 2. Displays the list of registrations yet to be activated

        boolean rejectedSuccessfully = administrator.rejectRegistrationRequest(requestIdToReject);

        if (rejectedSuccessfully) {
            System.out.println("\n--- System displays updated list of registrations yet to be activated ---");
            List<RegistrationRequest> updatedPendingRequests = administrator.viewPendingRegistrationRequests();
            if (updatedPendingRequests.isEmpty()) {
                System.out.println("No pending requests remaining.");
            } else {
                System.out.println("Updated Pending Requests:");
                updatedPendingRequests.forEach(System.out::println);
            }
        } else {
            System.out.println("Failed to reject request " + requestIdToReject + ".");
        }

        // --- Postconditions ---
        // • The user refused a request for registration to the system
        // • The user interrupts the connection operation to the interrupted SMOS server

        System.out.println("\n--- Verifying Postconditions ---");

        // Postcondition 1: The user refused a request for registration to the system
        Optional<RegistrationRequest> rejectedRequestOptional = administrator.getRegistrationRequestById(requestIdToReject);
        if (rejectedRequestOptional.isPresent()) {
            RegistrationRequest rejectedReq = rejectedRequestOptional.get();
            if (rejectedReq.getStatus() == RegistrationRequest.RequestStatus.REJECTED) {
                System.out.println("Postcondition 1 Met: Request " + rejectedReq.getRequestId() + " for " + rejectedReq.getStudent().getName() + " is now " + rejectedReq.getStatus() + ".");
            } else {
                System.out.println("Postcondition 1 Failed: Request " + rejectedReq.getRequestId() + " status is " + rejectedReq.getStatus() + ", expected REJECTED.");
            }
        } else {
            System.out.println("Postcondition 1 Failed: Rejected request " + requestIdToReject + " not found.");
        }

        // Postcondition 2: The user interrupts the connection operation to the interrupted SMOS server
        if (smosServer.isConnectionInterrupted(student2.getStudentId())) {
            System.out.println("Postcondition 2 Met: SMOS Server confirms connection interrupted for student " + student2.getStudentId() + ".");
        } else {
            System.out.println("Postcondition 2 Failed: SMOS Server did NOT interrupt connection for student " + student2.getStudentId() + ".");
        }

        // --- Demonstrate Edge Cases ---
        System.out.println("\n--- Demonstrating Edge Cases ---");

        // Edge Case 1: Try to reject a request that doesn't exist
        System.out.println("\nAttempting to reject a non-existent request (ID: NON_EXISTENT_REQ):");
        administrator.rejectRegistrationRequest("NON_EXISTENT_REQ");

        // Edge Case 2: Try to reject an already rejected request
        System.out.println("\nAttempting to reject an already rejected request (ID: " + requestIdToReject + "):");
        administrator.rejectRegistrationRequest(requestIdToReject);

        // Edge Case 3: Simulate approving a request and then trying to reject it
        System.out.println("\nSimulating approval of req1 and then attempting to reject it:");
        Optional<RegistrationRequest> req1Optional = administrator.getRegistrationRequestById(req1.getRequestId());
        if (req1Optional.isPresent()) {
            RegistrationRequest approvedReq = req1Optional.get();
            approvedReq.setStatus(RegistrationRequest.RequestStatus.APPROVED);
            System.out.println("Request " + approvedReq.getRequestId() + " for " + approvedReq.getStudent().getName() + " is now " + approvedReq.getStatus() + ".");
            administrator.rejectRegistrationRequest(approvedReq.getRequestId());
        }

        System.out.println("\n--- Reject Enrollment Student Use Case Demonstration Finished ---");
        smosServer.shutdown(); // Clean up server resources
    }
}