package com.example;

import com.example.controller.PreferenceController;
import com.example.dto.PreferenceDTO;
import com.example.repository.JpaPreferenceRepository;
import com.example.repository.JpaTouristRepository;
import com.example.repository.PreferenceRepository;
import com.example.repository.TouristRepository;
import com.example.service.PreferenceService;
import com.example.service.PreferenceServiceImpl;

/**
 * Main class to simulate the sequence diagram flow.
 * Creates the object graph and invokes methods as per the scenario.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Setup repositories (simulating JPA implementations)
        PreferenceRepository prefRepo = new JpaPreferenceRepository();
        TouristRepository touristRepo = new JpaTouristRepository();

        // 2. Setup service layer
        PreferenceService service = new PreferenceServiceImpl(prefRepo, touristRepo);

        // 3. Setup controller
        PreferenceController controller = new PreferenceController(service);

        // 4. Simulate the sequence diagram flows
        System.out.println("=== Starting Preference Management Simulation ===\n");

        // Scenario: Tourist accesses preference edit functionality
        System.out.println("1. Tourist accesses preference edit functionality");
        String touristId = "tourist123";

        // UI calls controller.showEditForm
        PreferenceDTO currentPref = controller.showEditForm(touristId);
        System.out.println("   Current preferences retrieved: " + currentPref + "\n");

        // Tourist edits and submits form (Normal Flow)
        System.out.println("2. Tourist edits form and submits");
        PreferenceDTO updatedPref = new PreferenceDTO("fr", "Europe/Paris", false, true);
        boolean preliminaryResult = controller.updatePreferences(touristId, updatedPref);
        System.out.println("   Preliminary update result (should be false): " + preliminaryResult + "\n");

        // Tourist confirms operation
        System.out.println("3. Tourist confirms operation");
        boolean finalResult = controller.confirmUpdate(touristId, updatedPref);
        System.out.println("   Final update result: " + finalResult + "\n");

        // Alternative Flow: Connection interruption simulation
        System.out.println("4. Simulating connection interruption...");
        // We can simulate by causing an exception in the repository.
        // For simplicity, we'll just call update with a different ID and assume it fails.
        boolean interruptedResult = controller.confirmUpdate("nonexistent", updatedPref);
        System.out.println("   Update result with interruption simulation: " + interruptedResult + "\n");

        System.out.println("=== Simulation Complete ===");
    }
}