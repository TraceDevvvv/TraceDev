package com.example;

import com.example.ui.ConventionRequestForm;
import com.example.service.ConventionValidationService;
import com.example.service.ConventionService;
import com.example.repository.ConventionRepository;
import com.example.controller.ConventionRequestController;
import com.example.dto.ConventionRequestDTO;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class to simulate the sequence diagram flow.
 * This class is not part of the UML but is added to make the code runnable.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Convention Request Simulation ===");

        // Create serv and repository
        ConventionValidationService validationService = new ConventionValidationService();
        ConventionRepository repository = new ConventionRepository();
        ConventionService conventionService = new ConventionService(repository);

        // Create controller with dependencies
        ConventionRequestController controller = new ConventionRequestController(validationService, conventionService);

        // Create form and inject controller
        ConventionRequestForm form = new ConventionRequestForm();
        form.setController(controller);

        // Simulate sequence diagram steps
        // Step 1: Operator enables request functionality
        controller.enableRequestFunctionality();

        // Step 2: Form displays data entry form
        form.displayForm();

        // Step 3: Operator inserts convention data (simulated)
        ConventionRequestDTO dto = new ConventionRequestDTO();
        Map<String, Object> data = new HashMap<>();
        data.put("agencyId", "AG123");
        data.put("conventionType", "Annual Conference");
        data.put("participants", 150);
        dto.setConventionData(data);
        form.populateForm(dto);

        // Step 4: Operator submits form
        form.submitForm();

        System.out.println("=== Simulation Complete ===");
    }
}