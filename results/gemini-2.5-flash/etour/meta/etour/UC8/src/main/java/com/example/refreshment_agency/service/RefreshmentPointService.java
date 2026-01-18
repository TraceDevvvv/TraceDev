package com.example.refreshment_agency.service;

import com.example.refreshment_agency.exception.InvalidDataException;
import com.example.refreshment_agency.exception.RefreshmentPointNotFoundException;
import com.example.refreshment_agency.model.RefreshmentPoint;
import com.example.refreshment_agency.repository.RefreshmentPointRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class to handle business logic related to refreshment points.
 * It interacts with the repository for data persistence and with the validation service for data integrity.
 */
public class RefreshmentPointService {
    private final RefreshmentPointRepository refreshmentPointRepository;
    private final ValidationService validationService;

    /**
     * Constructs a RefreshmentPointService with the given repository and validation service.
     *
     * @param refreshmentPointRepository The repository for accessing refreshment point data.
     * @param validationService          The service for validating refreshment point data.
     */
    public RefreshmentPointService(RefreshmentPointRepository refreshmentPointRepository, ValidationService validationService) {
        this.refreshmentPointRepository = refreshmentPointRepository;
        this.validationService = validationService;
    }

    /**
     * Retrieves a list of all refreshment points.
     *
     * @return A list of RefreshmentPoint objects.
     */
    public List<RefreshmentPoint> getRefreshmentPoints() {
        return refreshmentPointRepository.findAll();
    }

    /**
     * Retrieves a refreshment point by its ID.
     *
     * @param id The ID of the refreshment point to retrieve.
     * @return An Optional containing the RefreshmentPoint if found, otherwise empty.
     */
    public Optional<RefreshmentPoint> getRefreshmentPointById(long id) {
        return refreshmentPointRepository.findById(id);
    }

    /**
     * Updates an existing refreshment point with new data.
     * The data is validated before being saved.
     *
     * @param id           The ID of the refreshment point to update.
     * @param updatedPoint The RefreshmentPoint object containing the new data.
     * @return The updated and saved RefreshmentPoint object.
     * @throws RefreshmentPointNotFoundException If no refreshment point with the given ID is found.
     * @throws InvalidDataException              If the updated data is invalid.
     */
    public RefreshmentPoint updateRefreshmentPoint(long id, RefreshmentPoint updatedPoint) throws RefreshmentPointNotFoundException, InvalidDataException {
        // Ensure the ID in the updatedPoint matches the ID being updated
        if (id != updatedPoint.getId()) {
            throw new InvalidDataException("ID mismatch: The ID in the updated object does not match the target ID.");
        }

        // First, check if the refreshment point exists
        if (refreshmentPointRepository.findById(id).isEmpty()) {
            throw new RefreshmentPointNotFoundException("Refreshment point with ID " + id + " not found.");
        }

        // Validate the updated data
        validationService.validateRefreshmentPoint(updatedPoint);

        // Save the updated data
        return refreshmentPointRepository.save(updatedPoint);
    }

    /**
     * Simulates cancelling an operation. In a real application, this might involve
     * rolling back transactions or clearing temporary states.
     */
    public void cancelOperation() {
        System.out.println("Service layer: Operation cancelled.");
        // No actual state change needed for this simple console application
    }
}