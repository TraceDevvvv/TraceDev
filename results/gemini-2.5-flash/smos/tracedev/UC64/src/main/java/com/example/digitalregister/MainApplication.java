package com.example.digitalregister;

import com.example.digitalregister.controller.RegisterViewController;
import com.example.digitalregister.datasource.ArchiveDataSource;
import com.example.digitalregister.repository.IRegisterRepository;
import com.example.digitalregister.repository.RegisterRepositoryImpl;
import com.example.digitalregister.service.AuthenticationService;
import com.example.digitalregister.service.RegisterService;
import com.example.digitalregister.view.RegisterView;

import java.util.Scanner;

/**
 * Main application class to demonstrate the digital register viewing use case.
 * This acts as the 'Direction' actor and orchestrates the flow by simulating user inputs.
 */
public class MainApplication {

    public static void main(String[] args) {
        // --- System Initialization ---
        System.out.println("--- Initializing Digital Register System ---");

        // 1. Data Source
        ArchiveDataSource archiveDataSource = new ArchiveDataSource();

        // 2. Repository
        IRegisterRepository registerRepository = new RegisterRepositoryImpl(archiveDataSource);

        // 3. Service
        RegisterService registerService = new RegisterService(registerRepository);

        // 4. View (UI simulation)
        RegisterView registerView = new RegisterView();

        // 5. Authentication Service
        AuthenticationService authenticationService = new AuthenticationService();

        // 6. Controller
        RegisterViewController registerViewController = new RegisterViewController(
                registerService,
                registerView,
                authenticationService
        );

        System.out.println("--- System Initialization Complete ---");

        Scanner scanner = new Scanner(System.in);

        // --- Scenario 1: Successful Retrieval of Registers ---
        System.out.println("\n=== Scenario 1: Successful Retrieval of Registers ===");
        simulateUserFlow(registerViewController, registerView, scanner, archiveDataSource, "AY2023-2024", false);

        // --- Scenario 2: Retrieval with Connection Interruption ---
        System.out.println("\n=== Scenario 2: Retrieval with Connection Interruption ===");
        simulateUserFlow(registerViewController, registerView, scanner, archiveDataSource, "AY2023-2024", true);

        // --- Scenario 3: User Cancels Operation ---
        System.out.println("\n=== Scenario 3: User Cancels Operation ===");
        simulateUserFlow(registerViewController, registerView, scanner, archiveDataSource, "cancel", false);

        // --- Scenario 4: No Registers Found ---
        System.out.println("\n=== Scenario 4: No Registers Found (e.g., invalid yearId) ===");
        simulateUserFlow(registerViewController, registerView, scanner, archiveDataSource, "AY2050-2051", false);


        scanner.close();
        System.out.println("\n--- Application Simulation Ended ---");
    }

    /**
     * Simulates the user interaction flow for viewing digital registers.
     * @param controller The RegisterViewController instance.
     * @param view The RegisterView instance.
     * @param scanner The scanner for user input simulation.
     * @param archiveDataSource The ArchiveDataSource to control error simulation.
     * @param selectedYearId The academic year ID to "select" by the user, or "cancel" for cancellation scenario.
     * @param simulateConnectionError True to trigger a connection error in the data source.
     */
    private static void simulateUserFlow(RegisterViewController controller, RegisterView view, Scanner scanner,
                                         ArchiveDataSource archiveDataSource, String selectedYearId, boolean simulateConnectionError) {

        System.out.println("\n[Simulating user clicks 'Digital Register']");
        archiveDataSource.setSimulateConnectionError(simulateConnectionError); // Set error simulation for this flow

        // Dir -> View : clicks "Digital Register"
        // View -> Controller : handleDigitalRegisterClick()
        controller.handleDigitalRegisterClick();

        // The controller then calls view.displayAcademicYearSelection()
        // Here, we simulate the user input for academic year selection.

        if (view.isCancellationRequested()) {
            // This path is if getSelectedAcademicYearId was called previously and user typed 'cancel'
            // For current simulation, we explicitly pass 'cancel' as selectedYearId
            controller.handleCancellation();
            return;
        }

        // Simulate user selecting academic year (or cancelling)
        System.out.println("[Simulating user selects academic year: " + selectedYearId + "]");

        if ("cancel".equalsIgnoreCase(selectedYearId)) {
            // Simulate user input for cancellation
            view.resetCancellation(); // Ensure view's cancellation flag is clear before simulating new input
            System.out.println("(User types 'cancel' into the year selection prompt)");
            // In a real scenario, this would be an explicit "cancel" button click,
            // but for console simulation, we reuse the year input mechanism.
            controller.handleCancellation(); // Call the cancellation handler directly
            return;
        }

        // View -> Controller : handleAcademicYearSelection(yearId)
        controller.handleAcademicYearSelection(selectedYearId);
    }
}