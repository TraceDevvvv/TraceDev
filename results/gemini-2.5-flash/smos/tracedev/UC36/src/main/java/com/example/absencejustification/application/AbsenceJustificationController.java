package com.example.absencejustification.application;

import com.example.absencejustification.dataaccess.IJustificationRepository;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.Objects;

/**
 * The controller responsible for handling the business logic related to
 * recording absence justifications. It acts as an intermediary between the
 * presentation layer and the data access layer.
 */
public class AbsenceJustificationController {
    private final IJustificationRepository justificationRepository;

    /**
     * Constructs an AbsenceJustificationController with the required repository.
     * @param justificationRepository The repository to use for saving justifications.
     */
    public AbsenceJustificationController(IJustificationRepository justificationRepository) {
        this.justificationRepository = Objects.requireNonNull(justificationRepository, "JustificationRepository cannot be null.");
    }

    /**
     * Records an absence justification based on the provided data.
     * This method performs validation, creates the Justification entity,
     * and persists it using the repository.
     *
     * @param absenceId The ID of the absence to justify.
     * @param justificationData A map containing justification details (e.g., "date").
     * @return A String containing an error message if the operation failed, or null if successful.
     */
    public String recordAbsenceJustification(String absenceId, Map<String, String> justificationData) {
        System.out.println("[AbsenceJustificationController] Received request to record justification for Absence ID: " + absenceId);

        // Note left of JustController: Validate input data based on business rules.
        if (!validateJustificationData(justificationData)) {
            System.out.println("[AbsenceJustificationController] Justification data is invalid.");
            // JustController -> JustForm : validationError("Invalid justification data")
            return "Invalid justification data"; 
        }

        try {
            LocalDate justificationDate = LocalDate.parse(justificationData.get("date"));

            // create Justification
            // JustController -> Justification : new Justification(date, absenceId)
            Justification newJustification = new Justification(justificationDate, absenceId);

            // JustController -> JustRepo : save(justification)
            justificationRepository.save(newJustification);
            // Note right of JustController: Ensures integrity and persistence.

            System.out.println("[AbsenceJustificationController] Justification successfully recorded for Absence ID: " + absenceId);
            return null; // successStatus()
        } catch (DateTimeParseException e) {
            System.err.println("[AbsenceJustificationController] Error parsing date: " + justificationData.get("date") + ". " + e.getMessage());
            // This is a validation error related to data format
            return "Invalid date format provided.";
        } catch (RuntimeException e) {
            // JustController --x JustForm : systemError("Failed to save justification due to server error.")
            System.err.println("[AbsenceJustificationController] System error during justification save: " + e.getMessage());
            return "Failed to save justification due to server error."; // systemError()
        }
    }

    /**
     * Validates the justification data provided by the user.
     * @param justificationData A map containing the data to validate.
     * @return true if the data is valid, false otherwise.
     */
    private boolean validateJustificationData(Map<String, String> justificationData) {
        System.out.println("[AbsenceJustificationController] Validating justification data: " + justificationData);
        // Basic validation: Check if 'date' is present and can be parsed.\
        String dateString = justificationData.get("date");
        if (dateString == null || dateString.trim().isEmpty()) {
            System.out.println("[AbsenceJustificationController] Validation failed: Date is missing.");
            return false;
        }
        // Parsing is handled in recordAbsenceJustification, but a basic check here for non-parseable formats.
        // For strict pre-check, could attempt parse here, but for now, rely on main method's try-catch.
        // Add more complex business rules validation here if needed
        System.out.println("[AbsenceJustificationController] Justification data is valid.");
        return true;
    }
}