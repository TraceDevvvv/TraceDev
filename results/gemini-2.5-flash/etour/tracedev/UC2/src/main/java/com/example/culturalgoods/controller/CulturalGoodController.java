package com.example.culturalgoods.controller;

import com.example.culturalgoods.dto.CulturalGoodDTO;
import com.example.culturalgoods.exception.DuplicateCulturalGoodException;
import com.example.culturalgoods.exception.InvalidDataException;
import com.example.culturalgoods.exception.SystemUnavailableException;
import com.example.culturalgoods.service.CulturalGoodApplicationService;
import com.example.culturalgoods.service.AuthenticationService;
import com.example.culturalgoods.ui.UI;

/**
 * Controller for managing Cultural Good related user interactions.
 * This class handles requests from the UI, interacts with the application service,
 * and directs the UI to display appropriate responses.
 */
public class CulturalGoodController {
    private final CulturalGoodApplicationService culturalGoodService;
    private final UI ui;
    private final AuthenticationService authenticationService; // Added for R3

    /**
     * Constructs a CulturalGoodController.
     *
     * @param culturalGoodService The application service for cultural good logic.
     * @param ui The user interface boundary.
     * @param authenticationService The authentication service.
     */
    public CulturalGoodController(CulturalGoodApplicationService culturalGoodService, UI ui, AuthenticationService authenticationService) {
        this.culturalGoodService = culturalGoodService;
        this.ui = ui;
        this.authenticationService = authenticationService;
    }

    /**
     * Displays the form for inserting a cultural good.
     * Entry Condition: Agency Operator IS logged in.
     */
    public void showInsertForm() {
        // Entry Condition: Agency Operator is logged in (R3)
        if (!authenticationService.checkAuthentication()) {
            ui.showErrorMessage("Authentication required. Please log in first.");
            return;
        }
        System.out.println("[Controller] Activating insert cultural good feature.");
        // Sequence diagram: UI -> CulturalGoodController: showInsertForm()
        // Sequence diagram: CulturalGoodController --> UI: displayForm()
        ui.displayForm();
    }

    /**
     * Handles the submission of cultural good data for preview and validation.
     * Implements part of Flow of Events step 3.
     *
     * @param dto The CulturalGoodDTO submitted by the user.
     * @return A status string indicating the result (e.g., "CONFIRM", "ERROR").
     */
    public String submitCulturalGoodData(CulturalGoodDTO dto) {
        System.out.println("[Controller] Submitting cultural good data for preview: " + dto.getName());
        try {
            // Sequence diagram: CulturalGoodController -> CulturalGoodApplicationService: previewCulturalGood(dto)
            CulturalGoodDTO validatedDto = culturalGoodService.previewCulturalGood(dto);
            // Sequence diagram: CulturalGoodController --> UI: displayConfirmationRequest(dto)
            ui.showConfirmationDialog(validatedDto);
            return "CONFIRM"; // Data valid, prompt for confirmation
        } catch (InvalidDataException e) {
            System.err.println("[Controller] Invalid data: " + e.getMessage());
            // Sequence diagram: CulturalGoodApplicationService --> CulturalGoodController: throw InvalidDataException
            // Sequence diagram: CulturalGoodController --> UI: showErrorMessage(\"Data is invalid or insufficient\")
            ui.showErrorMessage("Data is invalid or insufficient: " + e.getMessage());
            return "ERROR"; // Invalid data
        } catch (DuplicateCulturalGoodException e) {
            System.err.println("[Controller] Duplicate cultural good: " + e.getMessage());
            // Sequence diagram: CulturalGoodApplicationService --> CulturalGoodController: throw DuplicateCulturalGoodException
            // Sequence diagram: CulturalGoodController --> UI: showErrorMessage(\"Cultural good with this name already exists.\")
            ui.showErrorMessage("Cultural good with this name already exists: " + e.getMessage());
            return "ERROR"; // Duplicate cultural good
        }
    }

    /**
     * Confirms the insertion of a cultural good after user approval.
     * Implements Flow of Events step 5 and step 6.
     *
     * @param dto The CulturalGoodDTO to be inserted.
     * @return A status string indicating the result (e.g., "SUCCESS", "FAILURE").
     */
    public String confirmInsertion(CulturalGoodDTO dto) {
        System.out.println("[Controller] Confirming insertion for: " + dto.getName());
        try {
            // Sequence diagram: CulturalGoodController -> CulturalGoodApplicationService: insertCulturalGood(dto)
            culturalGoodService.insertCulturalGood(dto);
            // Sequence diagram: CulturalGoodController --> UI: showSuccessMessage(\"The cultural good has been included.\")
            ui.showSuccessMessage("The cultural good has been included.");
            return "SUCCESS"; // Insertion successful
        } catch (SystemUnavailableException e) {
            System.err.println("[Controller] System unavailable: " + e.getMessage());
            // Sequence diagram: CulturalGoodApplicationService --x CulturalGoodController: throw SystemUnavailableException
            // Sequence diagram: CulturalGoodController --x UI: showErrorMessage(\"Unable to save due to connection issues.\")
            ui.showErrorMessage("Unable to save due to connection issues: " + e.getMessage());
            return "FAILURE"; // System unavailable
        }
    }
}