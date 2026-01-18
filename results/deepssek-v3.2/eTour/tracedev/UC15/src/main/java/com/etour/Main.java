package com.etour;

import com.etour.presentation.TouristForm;
import com.etour.controller.ModifyTouristController;
import com.etour.controller.SearchTouristController;
import com.etour.repository.TouristRepository;
import com.etour.repository.TouristRepositoryImpl;
import com.etour.validation.TouristValidator;
import com.etour.service.AuthenticationService;
import com.etour.util.ConnectionManager;
import com.etour.mapper.TouristDataMapper;
import com.etour.dto.TouristDTO;

import java.util.List;

/**
 * Main application class that simulates the Modify Tourist Account use case.
 * Implements the sequence diagram flow.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize components
        ConnectionManager connMgr = new ConnectionManager("http://etour-server.com");
        TouristDataMapper dataMapper = new TouristDataMapper();
        TouristRepository repo = new TouristRepositoryImpl(dataMapper, connMgr);
        TouristValidator validator = new TouristValidator();
        AuthenticationService authService = new AuthenticationService();
        // Login the agency operator
        authService.login("AO001");

        ModifyTouristController modifyCtrl = new ModifyTouristController(repo, validator, authService, connMgr, dataMapper);
        SearchTouristController searchCtrl = new SearchTouristController(repo, dataMapper);
        TouristForm form = new TouristForm(modifyCtrl, searchCtrl);

        System.out.println("=== Modify Tourist Account Use Case Simulation ===\n");

        // Step: Check login (Entry Condition)
        if (!authService.isLoggedIn("AO001")) {
            form.showLoginRequired();
            return;
        }

        // Step: Fetch tourist list (from SearchTourist use case)
        form.viewTouristList();

        // Step: Select tourist and activate modify function
        form.selectTourist("T001");
        form.activateModifyFunction();

        // Step: Controller selects tourist
        modifyCtrl.selectTourist("T001");

        // Step: Check connection
        if (!modifyCtrl.checkConnection()) {
            form.showConnectionError();
            return;
        }

        // Step: Load tourist data
        TouristDTO loadedData = modifyCtrl.loadTouristData();
        form.loadData(loadedData);
        form.displayForm();

        // Simulate editing (in real UI this would be interactive)
        System.out.println("Simulating editing of tourist data...");
        loadedData.setName("John Updated");
        loadedData.setEmail("john.updated@example.com");

        // Step: Submit form
        form.submitForm();
        TouristDTO editedData = form.getEditedData(); // In simulation, we use the already edited data

        // Step: Validate data
        List<String> errors = modifyCtrl.validateTouristData(editedData);
        if (errors.isEmpty()) {
            // Step: Ask confirmation
            form.askConfirmation();
            // Assume confirmation given
            modifyCtrl.confirmModification(editedData);
            System.out.println("Tourist account modified successfully.");
        } else {
            form.showValidationErrors(errors);
            System.out.println("Modification aborted due to validation errors.");
        }

        // Simulate connection interruption scenario
        System.out.println("\n--- Simulating connection interruption ---");
        connMgr.setConnected(false);
        try {
            modifyCtrl.loadTouristData();
        } catch (RuntimeException e) {
            form.showConnectionError();
        }

        System.out.println("\n=== Simulation Complete ===");
    }
}