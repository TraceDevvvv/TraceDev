package com.example;

import com.example.model.Tourist;
import com.example.model.PreferenceSet;
import com.example.ui.EditPreferencesForm;
import com.example.controller.PreferenceController;
import com.example.service.PreferenceService;
import com.example.service.AuthenticationService;
import com.example.repository.PreferenceRepository;
import com.example.repository.PreferenceRepositoryImpl;
import com.example.adapter.ETourServerAdapter;
import com.example.util.Logger;
import com.example.dto.PreferenceSetDTO;

import java.util.HashMap;

/**
 * Main class to demonstrate the system.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Tourist Preferences Management System ===");
        
        // Initialize components
        Logger logger = new Logger();
        ETourServerAdapter adapter = new ETourServerAdapter();
        PreferenceRepository repository = new PreferenceRepositoryImpl();
        PreferenceService service = new PreferenceService(repository, adapter, logger);
        AuthenticationService authService = new AuthenticationService();
        PreferenceController controller = new PreferenceController(service, authService);
        EditPreferencesForm form = new EditPreferencesForm(controller);
        
        // Create a tourist
        PreferenceSet initialPrefs = new PreferenceSet("English", "Light", true, new HashMap<>());
        Tourist tourist = new Tourist("T001", "John Doe", initialPrefs);
        
        // Authenticate tourist
        if (!tourist.authenticate()) {
            System.out.println("Tourist authentication failed. Exiting...");
            return;
        }
        
        // Start the preferences modification flow as per sequence diagram
        System.out.println("\n--- Step 1: Tourist accesses preferences modification ---");
        form.openPreferencesModification();
        
        System.out.println("\n--- Step 2: Load current preferences ---");
        PreferenceSetDTO currentPrefs = controller.loadPreferences(tourist.getId());
        form.displayForm(currentPrefs);
        
        System.out.println("\n--- Step 3: Check ETour connection ---");
        try {
            if (!adapter.connect()) {
                form.showErrorMessage("Cannot connect to ETour server. Exiting...");
                return;
            }
        } catch (Exception e) {
            form.showErrorMessage("Connection to ETour server interrupted: " + e.getMessage());
            return;
        }
        
        System.out.println("\n--- Step 4: Tourist edits preferences ---");
        // Simulate user editing
        PreferenceSetDTO editedPrefs = form.getUserInput();
        boolean isValid = controller.validateInput(editedPrefs);
        if (!isValid) {
            form.showErrorMessage("Invalid input detected!");
            return;
        }
        
        System.out.println("\n--- Step 5: Tourist submits form ---");
        if (!form.showConfirmationDialog()) {
            System.out.println("Tourist cancelled the operation.");
            return;
        }
        
        System.out.println("\n--- Step 6: Update preferences ---");
        boolean success = controller.updatePreferences(tourist.getId(), editedPrefs);
        
        if (success) {
            form.showSuccessMessage();
            System.out.println("Exit Condition: System notifies successful modification");
        } else {
            form.showErrorMessage("Failed to update preferences.");
        }
        
        // Cleanup
        adapter.disconnect();
        
        System.out.println("\n=== Flow completed ===");
    }
}