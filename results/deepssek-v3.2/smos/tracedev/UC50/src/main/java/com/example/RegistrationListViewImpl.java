package com.example;

import java.util.List;

/**
 * Concrete implementation of RegistrationListView.
 * Prints registrations to console.
 */
public class RegistrationListViewImpl implements RegistrationListView {
    @Override
    public void displayRegistrations(List<Registration> registrations) {
        System.out.println("=== Pending Registrations ===");
        if (registrations.isEmpty()) {
            System.out.println("No pending registrations.");
        } else {
            for (Registration r : registrations) {
                System.out.println("Request ID: " + r.getRequestId() +
                        ", User ID: " + r.getAssociatedUserId() +
                        ", Submitted: " + r.getSubmissionDate());
            }
        }
        System.out.println("=============================");
    }
}