package com.example.school;

import application.DisciplinaryNoteService;
import infrastructure.adapters.*;
import infrastructure.components.MessageBrokerClient;
import infrastructure.ports.IDisciplinaryNoteRepository;
import infrastructure.ports.IInfoLookupService;
import infrastructure.ports.INotificationService;
import ui.entrypoint.CreateNoteRequest;
import ui.entrypoint.DisciplinaryNoteController;
import ui.entrypoint.DisciplinaryNoteResponse;
import ui.entrypoint.NewNoteForm;

import java.time.LocalDate;

/**
 * Main application class to demonstrate the integration of all components
 * based on the provided Class and Sequence Diagrams.
 * This acts as the "bootstrap" or entry point for the application,
 * setting up the dependency graph and simulating user interactions.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Starting School Disciplinary Note Application ---");

        // 1. Instantiate Infrastructure Components
        MessageBrokerClient messageBrokerClient = new MessageBrokerClient();
        messageBrokerClient.connect(); // Connect to message broker once at application start

        // 2. Instantiate Adapters (implementations of Ports)
        IDisciplinaryNoteRepository disciplinaryNoteRepository = new DisciplinaryNoteRepositoryImpl();
        IInfoLookupService infoLookupService = new InfoLookupAdapter();
        NotificationMessageProducer notificationMessageProducer = new NotificationMessageProducer(messageBrokerClient);
        INotificationService notificationService = new EmailNotificationAdapter(notificationMessageProducer, infoLookupService);

        // 3. Instantiate Application Serv
        DisciplinaryNoteService disciplinaryNoteService = new DisciplinaryNoteService(
                disciplinaryNoteRepository,
                notificationService,
                infoLookupService
        );

        // 4. Instantiate UI/Entrypoint Layer (Controller)
        DisciplinaryNoteController disciplinaryNoteController = new DisciplinaryNoteController(disciplinaryNoteService);

        System.out.println("\n--- Simulation of Use Case: Administrator inserts disciplinary notes ---");

        // Precondition: Admin is logged in (R3) - Assumed by controller instantiation.
        // Precondition: Admin executed UseCase "SviewTetTingloregister" (R4) and "ViewElonconote" (R5)
        System.out.println("\nAdmin Action: Clicks 'New Note' button.");
        NewNoteForm newNoteForm = disciplinaryNoteController.displayNewNoteForm();
        System.out.println("UI displays form: " + newNoteForm);

        System.out.println("\nAdmin Action: Fills out form data and clicks 'Save'.");
        // Simulate form data
        CreateNoteRequest createNoteRequest = new CreateNoteRequest(
                "student123",
                LocalDate.of(2023, 10, 26),
                "teacher001",
                "Disruptive behavior during class, refusing to follow instructions."
        );
        System.out.println("UI sends request to Controller: " + createNoteRequest);

        DisciplinaryNoteResponse response = disciplinaryNoteController.createNote(createNoteRequest);
        System.out.println("UI receives response from Controller: " + response);

        // R14: Returns to registry screen upon success.
        // UI interpretation of the response:
        if (response.getNoteId() != null) {
            System.out.println("Admin sees confirmation: \"" + response.getMessage() + "\"");
            System.out.println("Admin UI would now display the registry screen, potentially refreshing to show the new note (ID: " + response.getNoteId() + ").");
        } else {
            System.out.println("Admin sees error: \"" + response.getMessage() + "\"");
            System.out.println("Admin UI would show an error message and keep the form open or redirect to an error page.");
        }

        System.out.println("\n--- Simulating a second note with different data ---");
        CreateNoteRequest secondNoteRequest = new CreateNoteRequest(
                "student456",
                LocalDate.of(2023, 10, 25),
                "teacher002",
                "Failure to submit homework for three consecutive days."
        );
        System.out.println("UI sends second request to Controller: " + secondNoteRequest);
        DisciplinaryNoteResponse secondResponse = disciplinaryNoteController.createNote(secondNoteRequest);
        System.out.println("UI receives second response from Controller: " + secondResponse);

        System.out.println("\n--- Simulating a note with invalid data (missing student ID) ---");
        CreateNoteRequest invalidRequest = new CreateNoteRequest(
                null, // Missing student ID
                LocalDate.of(2023, 10, 27),
                "teacher001",
                "Attempted to cheat on a quiz."
        );
        System.out.println("UI sends invalid request to Controller: " + invalidRequest);
        DisciplinaryNoteResponse invalidResponse = disciplinaryNoteController.createNote(invalidRequest);
        System.out.println("UI receives invalid response from Controller: " + invalidResponse);


        System.out.println("\n--- Application simulation finished ---");

        messageBrokerClient.disconnect(); // Disconnect from message broker at application shutdown
    }
}