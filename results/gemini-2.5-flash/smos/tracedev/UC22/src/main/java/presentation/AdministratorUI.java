package presentation;

import application.TeachingService;
import dtos.TeachingDto;

import java.util.List;
import java.util.Scanner; // For simulating user input in main

/**
 * Represents the User Interface for Administrator actions.
 * Handles user interactions and displays information.
 */
public class AdministratorUI {
    // - teachingController : TeachingController
    private TeachingController teachingController;

    /**
     * Constructor for AdministratorUI.
     * Initializes the TeachingController dependency.
     */
    public AdministratorUI() {
        // Initialize dependencies. In a real application, this would often be
        // handled by a dependency injection framework.
        this.teachingController = new TeachingController();
        System.out.println("AdministratorUI: Initialized.");
    }

    /**
     * Simulates the Administrator clicking a management button.
     * This method initiates the "View Teachings" use case.
     */
    public void clickManagementButton() {
        System.out.println("\n--- AdministratorUI: Administrator clicks 'Management Management' button. ---");
        // note right of UI: Administrator is logged in and has 'administrator' role. (R3, R4)
        // note left of UI: Precondition: 'Management Management' button is available. (R5)
        System.out.println("AdministratorUI: Precondition met - Management button available.");
        System.out.println("AdministratorUI: Administrator is logged in with 'administrator' role.");

        // UI -> UI : displayArchiveAndTeacherScreen()
        displayArchiveAndTeacherScreen();

        // UI -> Controller : viewTeachings()
        System.out.println("AdministratorUI: Calling TeachingController to view teachings.");
        List<TeachingDto> teachingDtos = teachingController.viewTeachings();

        // UI -> UI : displayTeachings(teachingDtos)
        displayTeachings(teachingDtos);

        // UI -> UI : displayManagementOptions()
        displayManagementOptions();
        System.out.println("--- AdministratorUI: Finished processing 'View Teachings' use case. ---\n");
    }

    /**
     * Displays the archive and teacher screen.
     * This is an internal UI method.
     */
    public void displayArchiveAndTeacherScreen() {
        System.out.println("AdministratorUI: Displaying archive and teacher management screen...");
        // Dummy implementation for screen display
    }

    /**
     * Displays a list of teachings to the Administrator.
     *
     * @param teachings A list of TeachingDto objects to display.
     */
    public void displayTeachings(List<TeachingDto> teachings) {
        System.out.println("AdministratorUI: Displaying teachings:");
        if (teachings == null || teachings.isEmpty()) {
            System.out.println("AdministratorUI: No teachings to display or an error occurred.");
            return;
        }
        for (TeachingDto dto : teachings) {
            System.out.println("  - ID: " + dto.id + ", Title: " + dto.title + ", Instructor: " + dto.instructor + ", Summary: " + dto.summary);
        }
        // note left of UI: System must display within 2 seconds.
        System.out.println("AdministratorUI: Teachings displayed successfully within assumed time limit.");
    }

    /**
     * Displays additional management options after showing teachings.
     * This is an internal UI method.
     */
    public void displayManagementOptions() {
        System.out.println("AdministratorUI: Displaying further management options (e.g., Add, Edit, Delete Teaching)...");
        // Dummy implementation for displaying options
    }

    /**
     * Main method to demonstrate the AdministratorUI functionality.
     * This acts as the entry point for running the application.
     */
    public static void main(String[] args) {
        System.out.println("Starting Administrator application simulation...");
        AdministratorUI adminUI = new AdministratorUI();

        // Simulate the Administrator initiating the workflow
        adminUI.clickManagementButton();

        // Simulate another scenario: what if SMOS connection fails?
        System.out.println("\n--- Simulating scenario where SMOS connection might be interrupted ---");
        // To simulate this, we need a way to control the SmosServerProxy behavior.
        // For simplicity, we'll directly set a flag on TeachingRepository.
        // In a real application, this would be injected or configured differently.
        System.out.println("Please enter 'fail' to simulate SMOS connection interruption, or anything else to succeed:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        boolean simulateFailure = "fail".equalsIgnoreCase(input.trim());
        scanner.close();

        // Reset and re-initialize to apply the failure condition
        // In a real app, this would be handled more gracefully, e.g., via config.
        // For this demo, we'll manually dig into the dependency chain.
        adminUI.teachingController = new TeachingController(); // Re-initialize controller
        TeachingService service = new TeachingService(); // Re-initialize service
        service.setTeachingRepository(new dataaccess.TeachingRepository(simulateFailure)); // Inject modified repository
        adminUI.teachingController.setTeachingService(service); // Inject modified service

        adminUI.clickManagementButton();
    }

    // Setter for TeachingController, primarily for testing purposes or dynamic configuration
    public void setTeachingController(TeachingController teachingController) {
        this.teachingController = teachingController;
    }
}