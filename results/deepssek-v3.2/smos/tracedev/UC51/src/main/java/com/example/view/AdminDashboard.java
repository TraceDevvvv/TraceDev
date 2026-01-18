package com.example.view;

import com.example.model.RegistrationRequest;
import java.util.List;

/**
 * Dashboard for administrators to manage registration requests.
 */
public class AdminDashboard {
    public void displayPendingRegistrations(Object viewModel) {
        if (viewModel instanceof List) {
            @SuppressWarnings("unchecked")
            List<RegistrationRequest> requests = (List<RegistrationRequest>) viewModel;
            System.out.println("Pending Registration Requests:");
            for (RegistrationRequest request : requests) {
                System.out.println("  ID: " + request.getRequestId() + ", Student: " + request.getStudentName());
            }
        } else {
            System.out.println("No pending registrations.");
        }
    }

    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }
}