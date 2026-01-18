package com.etour.preferences.controller;

import com.etour.preferences.dto.PreferenceDTO;
import com.etour.preferences.exception.InvalidPreferenceDataException;
import com.etour.preferences.exception.PreferenceNotFoundException;
import com.etour.preferences.service.PreferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * REST controller for handling HTTP requests related to user preferences.
 * This controller exposes endpoints for retrieving and updating generic personal preferences.
 */
@RestController
@RequestMapping("/api/preferences")
public class PreferenceController {

    private final PreferenceService preferenceService;

    /**
     * Constructs a new PreferenceController with the given PreferenceService.
     *
     * @param preferenceService The service layer for preference management.
     */
    @Autowired
    public PreferenceController(PreferenceService preferenceService) {
        this.preferenceService = preferenceService;
    }

    /**
     * Endpoint to retrieve generic personal preferences for a specific user.
     * Corresponds to Flow of Events step 2: "Upload your preferences and the general view in a form."
     *
     * @param userId The unique identifier of the user whose preferences are to be retrieved.
     * @return A {@link ResponseEntity} containing the {@link PreferenceDTO} if found,
     *         or an appropriate error status if not.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<PreferenceDTO> getPreferences(@PathVariable Long userId) {
        try {
            // Access to functionality for the modification of generic personal preferences. (Implicitly handled by accessing this endpoint)
            // Upload your preferences and the general view in a form. (The DTO returned here serves this purpose)
            PreferenceDTO preferences = preferenceService.getPreferences(userId);
            return ResponseEntity.ok(preferences);
        } catch (PreferenceNotFoundException e) {
            // Handle case where preferences for the user are not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Endpoint to update generic personal preferences for a specific user.
     * Corresponds to Flow of Events steps 3-6:
     * 3. Edit the fields in the form and submit.
     * 4. Asks for confirmation of the change. (Implicitly handled by client-side UI/UX)
     * 5. Confirm the operation. (Implicitly handled by client-side UI/UX)
     * 6. Stores preferences changed.
     *
     * @param userId The unique identifier of the user whose preferences are to be updated.
     * @param preferenceDTO The {@link PreferenceDTO} containing the updated preference data.
     *                      The userId in the DTO should match the path variable userId.
     * @return A {@link ResponseEntity} containing the updated {@link PreferenceDTO} on success,
     *         or an appropriate error status if the update fails.
     */
    @PutMapping("/{userId}")
    public ResponseEntity<PreferenceDTO> updatePreferences(@PathVariable Long userId,
                                                           @Valid @RequestBody PreferenceDTO preferenceDTO) {
        // Ensure the userId in the path matches the userId in the DTO for security and consistency
        if (!userId.equals(preferenceDTO.getUserId())) {
            throw new InvalidPreferenceDataException("User ID in path does not match user ID in request body.");
        }

        try {
            // Edit the fields in the form and submit. (The incoming preferenceDTO represents this)
            // Stores preferences changed.
            PreferenceDTO updatedPreferences = preferenceService.updatePreferences(preferenceDTO);
            // Exit condition: The system shall notify the successful modification of general preferences.
            return ResponseEntity.ok(updatedPreferences);
        } catch (InvalidPreferenceDataException e) {
            // Handle cases where the submitted preference data is invalid
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (PreferenceNotFoundException e) {
            // Although updatePreferences creates if not found, this catch is here for completeness
            // if the service logic were to change or for other specific not-found scenarios.
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Catch any other unexpected exceptions, e.g., connection interruption to ETOUR server
            // Quality requirement: Interruption of the connection to the server ETOUR.
            // This could manifest as a database connection issue or other backend problems.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Global exception handler for InvalidPreferenceDataException.
     * This provides a consistent error response for validation failures.
     *
     * @param ex The exception that was thrown.
     * @return A {@link ResponseEntity} with a BAD_REQUEST status and the exception message.
     */
    @ExceptionHandler(InvalidPreferenceDataException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidPreferenceDataException(InvalidPreferenceDataException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Global exception handler for PreferenceNotFoundException.
     * This provides a consistent error response when preferences are not found.
     *
     * @param ex The exception that was thrown.
     * @return A {@link ResponseEntity} with a NOT_FOUND status and the exception message.
     */
    @ExceptionHandler(PreferenceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handlePreferenceNotFoundException(PreferenceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}