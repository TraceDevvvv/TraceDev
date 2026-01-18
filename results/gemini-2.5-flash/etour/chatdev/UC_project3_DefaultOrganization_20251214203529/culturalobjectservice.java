/*
Service layer for CulturalObject operations.
Handles business logic such as data validation and interacts with the CulturalObjectDAO.
*/
package com.chatdev.modifybeneconaturale.service;
import com.chatdev.modifybeneconaturale.dao.CulturalObjectDAO;
import com.chatdev.modifybeneconaturale.model.CulturalObject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class CulturalObjectService {
    private final CulturalObjectDAO culturalObjectDAO;
    /**
     * Enumeration to represent the result of an update operation.
     */
    public enum UpdateResult {
        SUCCESS,
        VALIDATION_ERROR,
        CONNECTION_ERROR,
        NOT_FOUND_ERROR,
        UNKNOWN_ERROR
    }
    /**
     * Constructor for CulturalObjectService.
     * @param culturalObjectDAO The DAO instance to interact with the data source.
     */
    public CulturalObjectService(CulturalObjectDAO culturalObjectDAO) {
        this.culturalObjectDAO = culturalObjectDAO;
    }
    /**
     * Validates the data of a CulturalObject.
     * Checks for null/empty fields, length constraints, and value constraints.
     * This is a client-side (or initial server-side) validation.
     *
     * @param culturalObject The object to validate.
     * @return A list of error messages. If the list is empty, the object is valid.
     */
    public List<String> validateCulturalObject(CulturalObject culturalObject) {
        List<String> errors = new ArrayList<>();
        if (culturalObject == null) {
            errors.add("Cultural object cannot be null.");
            return errors;
        }
        // Validate ID (should not be empty if updating an existing object)
        // Assumption: ID is a string, and for updates, it must exist.
        if (culturalObject.getId() == null || culturalObject.getId().trim().isEmpty()) {
            errors.add("ID cannot be empty.");
        }
        // Validate name
        if (culturalObject.getName() == null || culturalObject.getName().trim().isEmpty()) {
            errors.add("Name cannot be empty.");
        } else if (culturalObject.getName().trim().length() < 3 || culturalObject.getName().trim().length() > 100) {
            errors.add("Name must be between 3 and 100 characters.");
        } else {
            // Basic pattern check for name (e.g., alphanumeric and spaces)
            Pattern namePattern = Pattern.compile("^[a-zA-Z0-9\\s,.'-]+$"); // Allows letters, numbers, spaces, comma, period, apostrophe, hyphen
            Matcher nameMatcher = namePattern.matcher(culturalObject.getName().trim());
            if (!nameMatcher.matches()) {
                errors.add("Name contains invalid characters. Only alphanumeric, spaces, commas, periods, apostrophes, and hyphens are allowed.");
            }
        }
        // Validate description
        if (culturalObject.getDescription() == null || culturalObject.getDescription().trim().isEmpty()) {
            errors.add("Description cannot be empty.");
        } else if (culturalObject.getDescription().trim().length() < 10 || culturalObject.getDescription().trim().length() > 500) {
            errors.add("Description must be between 10 and 500 characters.");
        }
        // Validate location
        if (culturalObject.getLocation() == null || culturalObject.getLocation().trim().isEmpty()) {
            errors.add("Location cannot be empty.");
        } else if (culturalObject.getLocation().trim().length() < 5 || culturalObject.getLocation().trim().length() > 200) {
            errors.add("Location must be between 5 and 200 characters.");
        }
        // Validate value
        if (culturalObject.getValue() < 0) {
            errors.add("Value cannot be negative.");
        }
        // Additional business rules for value can be added here, e.g., maximum value.
        // It's assumed the system handles `Double.NaN` or `infinity` from parsing earlier.
        return errors;
    }
    /**
     * Updates a cultural object after comprehensive validation.
     * If validation fails, it indicates an "Errored" state.
     * Handles various exceptions from the DAO layer and maps them to a structured result.
     *
     * @param culturalObject The cultural object with updated data.
     * @return UpdateResult indicating the outcome of the operation.
     */
    public UpdateResult updateCulturalObject(CulturalObject culturalObject) {
        // Step 4: Verify the data entered (comprehensive validation).
        // This is crucial for data integrity, even if basic validation happened in UI.
        List<String> validationErrors = validateCulturalObject(culturalObject);
        if (!validationErrors.isEmpty()) {
            System.err.println("Service: Validation failed for Cultural Object ID: " + (culturalObject != null ? culturalObject.getId() : "null"));
            validationErrors.forEach(System.err::println);
            return UpdateResult.VALIDATION_ERROR; // Simulates "Errored" use case
        }
        try {
            // Step 6: Stores the modified data of the cultural.
            culturalObjectDAO.updateCulturalObject(culturalObject);
            return UpdateResult.SUCCESS;
        } catch (CulturalObjectDAO.ConnectionInterruptionException e) {
            System.err.println("Service: Connection error during update: " + e.getMessage());
            return UpdateResult.CONNECTION_ERROR; // Represents ETOUR exit condition
        } catch (CulturalObjectDAO.CulturalObjectNotFoundException e) {
            System.err.println("Service: Cultural object not found during update: " + e.getMessage());
            return UpdateResult.NOT_FOUND_ERROR; // Object not found in data layer
        } catch (IllegalArgumentException e) {
            // Catches null updatedObject from DAO, or other illegal arguments during data access.
            System.err.println("Service: Illegal argument during update: " + e.getMessage());
            return UpdateResult.VALIDATION_ERROR; // Treat as validation/input error for the client
        } catch (Exception e) {
            // Catch any other unexpected exceptions.
            System.err.println("Service: An unexpected error occurred during update: " + e.getMessage());
            e.printStackTrace(); // Log the stack trace for unexpected errors
            return UpdateResult.UNKNOWN_ERROR;
        }
    }
    /**
     * Retrieves all cultural objects from the data source via the DAO.
     * Re-throws ConnectionInterruptionException to be handled by the client (GUI).
     *
     * @return A list of CulturalObject.
     * @throws CulturalObjectDAO.ConnectionInterruptionException If a connection error occurs during data retrieval.
     */
    public List<CulturalObject> getAllCulturalObjects() throws CulturalObjectDAO.ConnectionInterruptionException {
        return culturalObjectDAO.getAllCulturalObjects();
    }
    /**
     * Retrieves a cultural object by its ID from the data source via the DAO.
     *
     * @param id The ID of the cultural object.
     * @return The CulturalObject if found, null otherwise.
     */
    public CulturalObject getCulturalObjectById(String id) {
        return culturalObjectDAO.getCulturalObjectById(id);
    }
}