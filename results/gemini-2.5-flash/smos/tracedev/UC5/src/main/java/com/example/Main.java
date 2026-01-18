package com.example;

import com.example.auth.AuthService;
import com.example.controller.UserListController;
import com.example.model.UserListModel;
import com.example.repository.ArchiveUserRepository;
import com.example.service.UserService;
import com.example.view.UserListView;

/**
 * Main class to simulate the application startup and the "View Users" sequence diagram.
 * This class is responsible for wiring up all the components (MVC, Serv, Repositories).
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Starting Application Simulation ---");

        // 1. Initialize components (Dependency Injection)
        // Repository Layer
        ArchiveUserRepository archiveUserRepository = new ArchiveUserRepository();

        // Application Service Layer
        UserService userService = new UserService(archiveUserRepository);

        // Authentication Service (R3)
        AuthService authService = new AuthService();

        // MVC Components
        UserListModel userListModel = new UserListModel();
        UserListView userListView = new UserListView();
        UserListController userListController = new UserListController(userService, userListModel, userListView, authService);

        // Wire controller to view
        userListView.setController(userListController);

        System.out.println("\n--- Scenario 1: Administrator logs in and successfully views users ---");
        authService.setAdministratorLoggedIn(true); // Ensure admin is logged in
        archiveUserRepository.setSimulateConnectionFailure(false); // Ensure no connection failure

        // Simulate Admin clicking "User Manager" button
        userListView.handleUserManagerClick();

        System.out.println("\n--- Scenario 2: Administrator is NOT logged in (R3) ---");
        authService.setAdministratorLoggedIn(false); // Simulate admin not logged in
        // Reset state for next scenario if needed, though handleUserManagerClick handles it.

        userListView.handleUserManagerClick();

        System.out.println("\n--- Scenario 3: Connection to SMOS server interrupted ---");
        authService.setAdministratorLoggedIn(true); // Ensure admin is logged in
        archiveUserRepository.setSimulateConnectionFailure(true); // Simulate connection failure

        userListView.handleUserManagerClick();

        System.out.println("\n--- Application Simulation Complete ---");
    }
}