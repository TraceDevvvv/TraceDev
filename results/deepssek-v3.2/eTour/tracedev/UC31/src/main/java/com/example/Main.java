package com.example;

import com.example.session.Session;
import com.example.repository.AgencyRepositoryImpl;
import com.example.service.PasswordEncoder;
import com.example.service.ChangePasswordService;
import com.example.controller.ChangePasswordControllerImpl;
import com.example.view.ChangePasswordView;

/**
 * Main class to run the change password use case simulation.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Change Password Use Case Simulation ===\n");

        // 1. Setup: create components with dependencies
        Session session = new Session();
        session.setAgencyId("AG001"); // Simulate logged-in user

        AgencyRepositoryImpl repository = new AgencyRepositoryImpl();
        PasswordEncoder encoder = new PasswordEncoder();
        ChangePasswordService service = new ChangePasswordService(repository, encoder);
        ChangePasswordControllerImpl controller = new ChangePasswordControllerImpl(service);
        ChangePasswordView view = new ChangePasswordView(controller, session);

        // 2. Execute the UI flow
        view.startChangePasswordFlow();

        System.out.println("\n=== Simulation Complete ===");
    }
}