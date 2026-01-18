package com.ata;

import com.ata.repository.ClassJpaRepository;
import com.ata.repository.ClassRepository;
import com.ata.service.AuthenticationService;
import com.ata.service.ClassQueryService;
import com.ata.service.ClassQueryServiceImpl;
import com.ata.usecase.DisplayClassesController;
import com.ata.view.ClassDisplayScreen;

/**
 * Entry point that sets up dependencies and runs the use case.
 * Simulates the ATA staff requesting class display.
 */
public class Application {
    public static void main(String[] args) {
        // Set up dependencies as per class diagram
        ClassRepository repository = new ClassJpaRepository();
        ClassQueryService queryService = new ClassQueryServiceImpl(repository);
        AuthenticationService authService = new AuthenticationService();
        DisplayClassesController controller = new DisplayClassesController(queryService, authService);
        ClassDisplayScreen screen = new ClassDisplayScreen(controller);

        // Simulate ATA staff interaction as per sequence diagram
        System.out.println("ATA Staff requests to display classes.");
        screen.displayClassList();
    }
}