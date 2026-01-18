package com.example.justice.service;

import com.example.justice.domain.Justice;
import com.example.justice.dto.JusticeUpdateDTO;
import com.example.justice.repository.JusticeRepository;

/**
 * Application Service layer for Justice-related operations.
 * Encapsulates business logic and orchestrates data access operations.
 */
public class JusticeApplicationService {
    private final JusticeRepository justiceRepository;

    /**
     * Constructs a JusticeApplicationService with a JusticeRepository dependency.
     *
     * @param repository The data access layer for Justice entities.
     */
    public JusticeApplicationService(JusticeRepository repository) {
        this.justiceRepository = repository;
    }

    /**
     * Retrieves the details of a specific Justice record.
     * Used as part of the initial display flow (entry condition).
     *
     * @param justiceId The ID of the justice to retrieve.
     * @return The Justice object if found, otherwise null.
     */
    public Justice getJusticeDetails(String justiceId) {
        System.out.println("JusticeApplicationService: Fetching details for Justice ID: " + justiceId);
        return justiceRepository.findById(justiceId);
    }

    /**
     * Updates an existing Justice record based on the provided DTO.
     * This method contains the core business logic for the update operation.
     *
     * @param justiceDto The DTO containing the ID and updated fields.
     * @return The updated Justice object, or null if the Justice could not be found.
     */
    public Justice updateJustice(JusticeUpdateDTO justiceDto) {
        System.out.println("JusticeApplicationService: Attempting to update Justice ID: " + justiceDto.getId());

        // 1. Find the existing Justice entity.
        Justice existingJustice = justiceRepository.findById(justiceDto.getId());

        if (existingJustice == null) {
            System.out.println("JusticeApplicationService: Justice with ID " + justiceDto.getId() + " not found.");
            return null; // Justice not found, cannot update.
        }

        // 2. Apply updates to the domain entity.
        // The domain entity itself handles how its state changes.
        existingJustice.update(justiceDto);
        System.out.println("JusticeApplicationService: Justice entity updated in memory: " + existingJustice);


        // 3. Save the updated entity back to the repository.
        Justice savedJustice = justiceRepository.save(existingJustice);
        System.out.println("JusticeApplicationService: Justice ID " + savedJustice.getId() + " saved to repository.");

        return savedJustice;
    }
}