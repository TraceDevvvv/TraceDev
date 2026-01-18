package com.example.turista;

import com.example.turista.application.TuristaService;
import com.example.turista.data.ETOURServerAdapter;
import com.example.turista.data.ITuristaRepository;
import com.example.turista.data.TuristaRepository;
import com.example.turista.presentation.AgencyOperatorController;
import com.example.turista.presentation.AgencyOperatorView;

import java.util.Scanner;

/**
 * Main class to demonstrate the Turista account details viewing use case.
 * This sets up the dependencies and simulates user interaction.
 *
 * note top: This sequence illustrates the use case goal: allowing Agency Operator to view Turista account details (R1)
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Starting Turista Account Details Viewer Application ---");

        // 1. Initialize Data Access Layer
        ETOURServerAdapter etourServerAdapter = new ETOURServerAdapter("https://etour.example.com/api");
        ITuristaRepository turistaRepository = new TuristaRepository(etourServerAdapter);

        // 2. Initialize Application Layer
        TuristaService turistaService = new TuristaService(turistaRepository);

        // 3. Initialize Presentation Layer
        AgencyOperatorView view = new AgencyOperatorView();
        AgencyOperatorController controller = new AgencyOperatorController(turistaService, view);
        view.setController(controller); // Inject controller into view

        // Simulate Agency Operator interaction (R2)
        // alt [Agency Operator is logged in (R3)] - This is assumed for the main method context
        System.out.println("\nAgency Operator is logged in. Ready to view Turista details."); // R3

        Scanner scanner = new Scanner(System.in);
        String choice;

        do {
            System.out.println("\n--- Simulation Menu ---");
            System.out.println("1. View Turista Card (Success)");
            System.out.println("2. View Turista Card (Simulate Data Not Found - ID 102)");
            System.out.println("3. View Turista Card (Simulate ETOUR Connection Error)");
            System.out.println("4. Toggle ETOUR Server Connection (Current: " + (etourServerAdapter.checkConnection() ? "UP" : "DOWN") + ")");
            System.out.println("5. Custom Turista ID (Input from UI)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextLine();

            etourServerAdapter.simulateSuccess(); // Reset adapter state for each choice

            switch (choice) {
                case "1":
                    System.out.println("\nSimulating operator selection for a valid Turista (ID: 101)...");
                    view.notifySelection("101"); // AO -> View: notifySelection(turistaId) (R6)
                    break;
                case "2":
                    System.out.println("\nSimulating operator selection for a non-existent Turista (ID: 102)...");
                    // To trigger data not found specifically via ID 102, as per ETOURServerAdapter
                    // or simulate a specific adapter state. Let's use the ID for this.
                    view.notifySelection("102"); // AO -> View: notifySelection(turistaId) (R6)
                    break;
                case "3":
                    System.out.println("\nSimulating ETOUR server connection interruption...");
                    etourServerAdapter.simulateConnectionDown();
                    view.notifySelection("103"); // Use an arbitrary ID, the error is connection-based
                    etourServerAdapter.simulateConnectionUp(); // Reset connection immediately after test
                    break;
                case "4":
                    if (etourServerAdapter.checkConnection()) {
                        etourServerAdapter.simulateConnectionDown();
                    } else {
                        etourServerAdapter.simulateConnectionUp();
                    }
                    System.out.println("ETOUR Server Connection is now: " + (etourServerAdapter.checkConnection() ? "UP" : "DOWN"));
                    break;
                case "5":
                    // Simulate selecting a Turista from a list (R4)
                    String inputId = view.getSelectedTuristaIdFromUI();
                    view.notifySelection(inputId); // AO -> View: notifySelection(turistaId) (R6)
                    break;
                case "0":
                    System.out.println("Exiting application.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("\n------------------------------------------------\n");
        } while (!choice.equals("0"));

        scanner.close();
        System.out.println("--- Application Terminated ---");
    }
}