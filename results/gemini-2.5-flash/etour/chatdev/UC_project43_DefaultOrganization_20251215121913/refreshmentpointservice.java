'''
Service layer for managing RefreshmentPoint entities.
Handles business logic, validation, and interacts with the RefreshmentPointDAO.
'''
package service;
import dao.RefreshmentPointDAO;
import model.RefreshmentPoint;
import java.util.ArrayList;
import java.util.List;
public class RefreshmentPointService {
    private final RefreshmentPointDAO refreshmentPointDAO;
    /**
     * Constructs a RefreshmentPointService with a given DAO.
     * @param refreshmentPointDAO The DAO to use for data operations.
     */
    public RefreshmentPointService(RefreshmentPointDAO refreshmentPointDAO) {
        this.refreshmentPointDAO = refreshmentPointDAO;
    }
    /**
     * Retrieves a RefreshmentPoint by its ID.
     *
     * @param id The ID of the refreshment point.
     * @return The RefreshmentPoint object if found, null otherwise.
     * @throws RuntimeException if there's an issue retrieving data from the DAO.
     */
    public RefreshmentPoint getRefreshmentPoint(String id) {
        try {
            return refreshmentPointDAO.findById(id);
        } catch (Exception e) {
            // Log the exception in a real application (e.g., using a logging framework like SLF4J)
            System.err.println("Service Error: Could not retrieve refreshment point " + id + ". Error: " + e.getMessage());
            // In a real application, you might throw a custom RuntimeException or wrap the original
            throw new RuntimeException("Failed to retrieve refreshment point data.", e);
        }
    }
    /**
     * Updates an existing RefreshmentPoint after validation.
     * This method covers steps 4, 5, and 6 of the use case flow.
     *
     * @param point The RefreshmentPoint object with updated data.
     * @return A list of error messages if validation fails, an empty list otherwise.
     * @throws RuntimeException if there's an issue saving the data (e.g., simulating "ETOUR" connection interruption).
     */
    public List<String> updateRefreshmentPoint(RefreshmentPoint point) {
        // Step 4: Verify the data entered
        List<String> validationErrors = validateRefreshmentPoint(point);
        if (!validationErrors.isEmpty()) {
            return validationErrors; // Return errors if validation fails (activates 'use case Errored')
        }
        try {
            System.out.println("Service: Attempting to update refreshment point: " + point.getId());
            // Step 6: Stores the modified data of the point of rest.
            refreshmentPointDAO.save(point);
            return new ArrayList<>(); // No errors, return empty list
        } catch (Exception e) {
            // Simulate 'ETOUR connection interruption' or other persistent storage error
            System.err.println("Service Error ('ETOUR'): Failed to save refreshment point " + point.getId() + ". Error: " + e.getMessage());
            // Use case Errored: If a critical system error occurs during save, activate use case Errored.
            // This is represented by throwing a RuntimeException, which GUI will catch and display.
            throw new RuntimeException("Failed to store modified data. Possible connection interruption to server ETOUR.", e);
        }
    }
    /**
     * Validates the data of a RefreshmentPoint.
     * According to use case, if data is invalid or insufficient, system activates use case Errored.
     *
     * @param point The RefreshmentPoint to validate.
     * @return A list of error messages. Empty if validation passes.
     */
    public List<String> validateRefreshmentPoint(RefreshmentPoint point) {
        List<String> errors = new ArrayList<>();
        if (point == null) {
            errors.add("Refreshment Point data cannot be null.");
            return errors;
        }
        // Basic validation for required fields
        if (point.getId() == null || point.getId().trim().isEmpty()) {
            errors.add("ID cannot be empty.");
        }
        if (point.getName() == null || point.getName().trim().isEmpty()) {
            errors.add("Name cannot be empty.");
        }
        if (point.getAddress() == null || point.getAddress().trim().isEmpty()) {
            errors.add("Address cannot be empty.");
        }
        // Basic phone number format check (optional but good for robustness)
        if (point.getContactPhone() == null || point.getContactPhone().trim().isEmpty()) {
            errors.add("Contact Phone cannot be empty.");
        } else if (!point.getContactPhone().matches("^[0-9\\-+()\\s]*[0-9]$")) { // Simple regex for phone numbers
            errors.add("Contact Phone is invalid. Please use digits, +, -, () and spaces only.");
        }
        // Description can be empty, as it's often optional.
        System.out.println("Service: Validation result for " + (point.getId() != null ? point.getId() : "null ID") + ": " + (errors.isEmpty() ? "Passed" : errors.size() + " errors"));
        return errors;
    }
    /**
     * Retrieves all available refreshment point IDs from the DAO.
     * @return A list of strings representing all known refreshment point IDs.
     * @throws RuntimeException if there's an issue retrieving the IDs from the DAO.
     */
    public List<String> getAllPointIds() {
        try {
            return refreshmentPointDAO.getAllIds();
        } catch (Exception e) {
            System.err.println("Service Error: Could not retrieve all point IDs. Error: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve refreshment point IDs.", e);
        }
    }
}