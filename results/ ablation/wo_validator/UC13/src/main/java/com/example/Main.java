package com.example;

import com.example.agency.AgencyOperator;
import com.example.agency.ManageTouristAccountController;
import com.example.dto.OperationResultDTO;
import com.example.repository.TouristRepository;
import com.example.server.ETOURServerConnector;
import com.example.tourist.Tourist;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * Main class to demonstrate the system.
 * Simulates the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        // Setup components
        DataSource dataSource = null; // In a real app, configure a DataSource
        TouristRepository repository = new TouristRepository(dataSource);
        ETOURServerConnector serverConnector = new ETOURServerConnector("https://api.etour.example.com/notify", "secret-api-key");
        ManageTouristAccountController controller = new ManageTouristAccountController(repository, serverConnector);

        // Create an agency operator
        AgencyOperator operator = new AgencyOperator("OP001", "alice", Arrays.asList("MANAGE_TOURIST"));

        // Simulate enabling a tourist account
        System.out.println("=== Enabling Tourist Account ===");
        OperationResultDTO result = controller.toggleTouristAccountStatus(operator.getId(), "T001", true);
        printResult(result);

        // Simulate disabling a tourist account
        System.out.println("\n=== Disabling Tourist Account ===");
        result = controller.toggleTouristAccountStatus(operator.getId(), "T002", false);
        printResult(result);

        // Test with non-existent tourist
        System.out.println("\n=== Non-existent Tourist ===");
        result = controller.toggleTouristAccountStatus(operator.getId(), "T999", true);
        printResult(result);
    }

    private static void printResult(OperationResultDTO result) {
        if (result.isSuccessful()) {
            System.out.println("Success: " + result.getMessage() + " for tourist " + result.getTouristId() + " new status=" + result.getNewStatus());
        } else {
            System.out.println("Failure: " + result.getMessage());
        }
    }
}