package com.example.refreshmentpoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service layer for Refreshment Point operations.
 * Handles business logic, interacts with repository and validator.
 */
public class RefreshmentPointService {
    private IRefreshmentPointRepository repository;
    private RefreshmentPointValidator validator;
    private NetworkStatusMonitor networkMonitor; // Added to satisfy requirement XC3
    private RefreshmentPointDTO currentUpdatedDTO; // Temporarily holds DTO for confirmation flow

    public RefreshmentPointService(IRefreshmentPointRepository repository, RefreshmentPointValidator validator, NetworkStatusMonitor networkMonitor) {
        this.repository = repository;
        this.validator = validator;
        this.networkMonitor = networkMonitor;
    }

    /**
     * Retrieves RefreshmentPoint data for editing.
     *
     * @param pointId The ID of the refreshment point.
     * @return A RefreshmentPointDTO containing the point's data, or null if not found.
     * @throws ETOURConnectionException If the connection to the ETOUR server is interrupted.
     */
    public RefreshmentPointDTO retrievePointData(String pointId) throws ETOURConnectionException {
        System.out.println("Service: Retrieving data for point ID: " + pointId);
        RefreshmentPoint refreshmentPointEntity = repository.findById(pointId);

        if (refreshmentPointEntity != null) {
            // Convert entity to DTO for presentation
            return new RefreshmentPointDTO(
                    refreshmentPointEntity.getId(),
                    refreshmentPointEntity.getName(),
                    refreshmentPointEntity.getLocation(),
                    refreshmentPointEntity.getType()
            );
        }
        return null; // Point not found
    }

    /**
     * Processes an update request for a refreshment point, including validation.
     * This method validates the data and prepares for confirmation.
     *
     * @param pointId The ID of the refreshment point to update.
     * @param updatedData The DTO containing the proposed updated data.
     * @return A map indicating validation status and if confirmation is required.
     */
    public Map<String, Object> processPointUpdate(String pointId, RefreshmentPointDTO updatedData) {
        System.out.println("Service: Processing update for point ID: " + pointId + " with data: " + updatedData);
        Map<String, Object> result = new HashMap<>();

        if (validator.validate(updatedData)) {
            result.put("isValid", true);
            result.put("requiresConfirmation", true);
            this.currentUpdatedDTO = updatedData; // Store DTO for later finalization
        } else {
            result.put("isValid", false);
            result.put("errors", validator.getErrors());
            this.currentUpdatedDTO = null; // Clear if invalid
        }
        return result;
    }

    /**
     * Finalizes the update operation for a refreshment point after confirmation.
     *
     * @param pointId The ID of the refreshment point to finalize update for.
     * @return true if the update was successful, false otherwise.
     * @throws ETOURConnectionException If the connection to the ETOUR server is interrupted.
     */
    public boolean finalizePointUpdate(String pointId) throws ETOURConnectionException {
        System.out.println("Service: Finalizing update for point ID: " + pointId);

        if (currentUpdatedDTO == null || !pointId.equals(currentUpdatedDTO.id)) {
            System.err.println("Service: Error: No valid data available for finalization or ID mismatch.");
            return false; // No data to update or ID mismatch
        }

        // Retrieve the existing entity to update it
        RefreshmentPoint existingPoint = repository.findById(pointId);
        if (existingPoint == null) {
            System.err.println("Service: Error: RefreshmentPoint with ID " + pointId + " not found for update.");
            return false;
        }

        // Apply changes from the DTO to the entity
        existingPoint.updateDetails(
                currentUpdatedDTO.name,
                currentUpdatedDTO.location,
                currentUpdatedDTO.type
        );

        // Store the modified data
        repository.update(existingPoint);
        this.currentUpdatedDTO = null; // Clear stored DTO after successful update
        return true;
    }
}