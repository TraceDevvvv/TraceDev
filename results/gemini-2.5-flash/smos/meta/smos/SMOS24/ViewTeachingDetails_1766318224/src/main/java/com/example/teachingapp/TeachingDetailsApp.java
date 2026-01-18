package com.example.teachingapp;

import com.example.teachingapp.data.TeachingDAO;
import com.example.teachingapp.data.TeachingDAOImpl;
import com.example.teachingapp.model.Teaching;
import com.example.teachingapp.service.TeachingService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main application class to demonstrate the "ViewTeachingDetails" use case.
 * This class simulates the user interaction and system responses as described
 * in the use case, acting as a simple command-line interface.
 */
public class TeachingDetailsApp {

    private final TeachingService teachingService;
    private final Scanner scanner;

    /**
     * Constructor for the TeachingDetailsApp.
     * Initializes the TeachingService with a concrete DAO implementation.
     */
    public TeachingDetailsApp() {
        // Initialize the data access object (DAO)
        TeachingDAO teachingDAO = new TeachingDAOImpl();
        // Initialize the service layer with the DAO
        this.teachingService = new TeachingService(teachingDAO);
        // Initialize scanner for user input
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the user login process.
     * As per the precondition, the user is already logged in.
     * In a real application, this would involve authentication logic.
     */
    private void simulateLogin() {
        System.out.println("Administrator logged in successfully. (Simulated)");
    }

    /**
     * Displays a list of available teachings to the administrator.
     * This fulfills the precondition "System is viewing the list of teachings".
     */
    private void displayTeachingsList() {
        System.out.println("\n--- Available Teachings ---");
        List<Teaching> teachings = teachingService.getAllTeachings();
        if (teachings.isEmpty()) {
            System.out.println("No teachings available at the moment.");
        } else {
            teachings.forEach(teaching ->
                    System.out.println("ID: " + teaching.getTeachingId() + ", Title: " + teaching.getTitle() + ", Instructor: " + teaching.getInstructorName())
            );
        }
        System.out.println("---------------------------\n");
    }

    /**
     * Prompts the user to select a teaching and displays its details.
     * This simulates the "User clicks on the 'Teaching details' button" event.
     */
    private void viewTeachingDetails() {
        System.out.print("Enter the Teaching ID to view details (e.g., T001): ");
        String teachingId = scanner.nextLine();

        // Retrieve teaching details using the service layer
        Optional<Teaching> teachingOptional = teachingService.getTeachingDetails(teachingId);

        // Event sequence: System displays the screen with the details of a single teaching.
        if (teachingOptional.isPresent()) {
            Teaching teaching = teachingOptional.get();
            System.out.println("\n--- Teaching Details ---");
            System.out.println(teaching.toString()); // Uses the overridden toString for detailed display
            System.out.println("------------------------\n");
            // Postcondition: The user displays the detailed information of a single teaching
            System.out.println("Detailed information for Teaching ID '" + teachingId + "' displayed successfully.");
        } else {
            System.out.println("\nError: Teaching with ID '" + teachingId + "' not found.");
        }
    }

    /**
     * Simulates the postcondition "Connection to the interrupted SMOS server".
     * In a real system, this might involve closing connections or logging out.
     */
    private void simulateSMOSConnectionInterruption() {
        System.out.println("Simulating: Connection to the interrupted SMOS server.");
        // In a real scenario, this might involve resource cleanup, logging, etc.
    }

    /**
     * The main method to run the application.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        TeachingDetailsApp app = new TeachingDetailsApp();

        // Precondition: The user has already logged in.
        app.simulateLogin();

        // Precondition: System is viewing the list of teachings.
        app.displayTeachingsList();

        // Event sequence: User clicks on the "Teaching details" button.
        // System displays the screen with the details of a single teaching.
        app.viewTeachingDetails();

        // Postcondition: Connection to the interrupted SMOS server.
        app.simulateSMOSConnectionInterruption();

        // Close the scanner to prevent resource leaks.
        app.scanner.close();
    }
}