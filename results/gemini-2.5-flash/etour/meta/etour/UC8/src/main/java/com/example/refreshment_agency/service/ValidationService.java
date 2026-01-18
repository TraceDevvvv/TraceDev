package com.example.refreshment_agency.service;

import com.example.refreshment_agency.exception.InvalidDataException;
import com.example.refreshment_agency.model.RefreshmentPoint;

/**
 * Service class for validating RefreshmentPoint data.
 * This class contains methods to ensure the integrity and correctness of refreshment point information.
 */
public class ValidationService {

    /**
     * Validates a RefreshmentPoint object.
     * Checks for common data integrity issues such as null or empty strings,
     * and valid ranges for numeric fields.
     *
     * @param refreshmentPoint The RefreshmentPoint object to validate.
     * @throws InvalidDataException If any validation rule is violated.
     */
    public void validateRefreshmentPoint(RefreshmentPoint refreshmentPoint) throws InvalidDataException {
        if (refreshmentPoint == null) {
            throw new InvalidDataException("RefreshmentPoint cannot be null.");
        }

        // Validate Name
        if (refreshmentPoint.getName() == null || refreshmentPoint.getName().trim().isEmpty()) {
            throw new InvalidDataException("Refreshment point name cannot be empty.");
        }
        if (refreshmentPoint.getName().trim().length() < 3 || refreshmentPoint.getName().trim().length() > 100) {
            throw new InvalidDataException("Refreshment point name must be between 3 and 100 characters.");
        }

        // Validate Address
        if (refreshmentPoint.getAddress() == null || refreshmentPoint.getAddress().trim().isEmpty()) {
            throw new InvalidDataException("Address cannot be empty.");
        }
        if (refreshmentPoint.getAddress().trim().length() < 5 || refreshmentPoint.getAddress().trim().length() > 200) {
            throw new InvalidDataException("Address must be between 5 and 200 characters.");
        }

        // Validate Contact Info (simple check for non-empty)
        if (refreshmentPoint.getContactInfo() == null || refreshmentPoint.getContactInfo().trim().isEmpty()) {
            throw new InvalidDataException("Contact information cannot be empty.");
        }
        // A more robust validation would include email/phone format checks
        if (refreshmentPoint.getContactInfo().trim().length() < 5 || refreshmentPoint.getContactInfo().trim().length() > 100) {
            throw new InvalidDataException("Contact information must be between 5 and 100 characters.");
        }

        // Validate Capacity
        if (refreshmentPoint.getCapacity() <= 0) {
            throw new InvalidDataException("Capacity must be a positive number.");
        }
        if (refreshmentPoint.getCapacity() > 1000) { // Arbitrary upper limit
            throw new InvalidDataException("Capacity cannot exceed 1000.");
        }

        // Validate Serv Offered
        if (refreshmentPoint.getServOffered() == null || refreshmentPoint.getServOffered().trim().isEmpty()) {
            throw new InvalidDataException("Serv offered cannot be empty.");
        }
        if (refreshmentPoint.getServOffered().trim().length() < 5 || refreshmentPoint.getServOffered().trim().length() > 500) {
            throw new InvalidDataException("Serv offered must be between 5 and 500 characters.");
        }

        // Validate Status
        if (refreshmentPoint.getStatus() == null) {
            throw new InvalidDataException("Status cannot be null. Must be ACTIVE or INACTIVE.");
        }
        // Enum validation is implicitly handled by the type system, but an explicit check ensures it's not null.
    }
}