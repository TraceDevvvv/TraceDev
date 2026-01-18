package com.example.tourist;

/**
 * Application Service for managing Tourist data.
 * It orchestrates operations between the Controller and the Repository/Domain Entities.
 */
public class TouristManagementService {
    private final ITouristRepository touristRepository;

    /**
     * Constructor for TouristManagementService.
     * @param touristRepository The repository dependency for accessing tourist data.
     */
    public TouristManagementService(ITouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    /**
     * Retrieves tourist data by ID and converts it to a DTO.\n
     * @param touristId The ID of the tourist.\n
     * @return A TouristDTO if found, otherwise null.\n
     */
    public TouristDTO getTouristById(String touristId) {
        System.out.println("[TouristManagementService] Getting tourist by ID: " + touristId);
        Tourist tourist = touristRepository.findById(touristId);
        if (tourist != null) {
            return new TouristDTO(tourist);
        }
        return null;
    }

    /**
     * Validates the provided tourist data.\n
     *\n
     * This method retrieves the existing Tourist entity, updates its details (temporarily\n
     * or on a copy for validation context), and then calls the entity's validate method.\n
     *\n
     * @param touristDTO The DTO containing data to validate.\n
     * @return A ValidationResult indicating the validity of the data.\n
     */
    public ValidationResult validateTouristData(TouristDTO touristDTO) {
        System.out.println("[TouristManagementService] Validating tourist data for ID: " + touristDTO.id);

        // Fetch existing tourist for validation context
        Tourist existingTouristForValidation = touristRepository.findById(touristDTO.id);
        // Traceability for m17: existingTouristForValidation ' // Added for validation context
        System.out.println("[TouristManagementService] Repository returned: existingTouristForValidation (ID: " + touristDTO.id + ")");

        if (existingTouristForValidation == null) {
            // If not found, perhaps it's a new tourist being validated, or an invalid ID.
            // For this use case (changing existing data), we assume the ID should exist.
            // If it's for a new tourist creation, the logic would differ slightly.
            // Assumption: For "Change Tourist Account Data" use case, ID must exist.
            ValidationResult result = new ValidationResult();
            result.addError("Tourist with ID " + touristDTO.id + " not found for validation.");
            return result;
        }

        // Create a temporary tourist entity or clone to apply updates for validation
        // and avoid modifying the actual fetched entity before successful save.
        // For simplicity, we can update the fetched one if it's not going to be saved unless valid.
        // Or, more robustly, create a new Tourist object with updated data for validation:
        Tourist touristToValidate = new Tourist(
                existingTouristForValidation.getId(),
                touristDTO.name,
                touristDTO.surname,
                touristDTO.email,
                touristDTO.address
        );

        // Delegate validation to the domain entity
        ValidationResult validationResult = touristToValidate.validate();
        System.out.println("[TouristManagementService] Validation result: " + validationResult.toString());
        // m21 and m36 are return values from Service to Controller, explicitly logged there.
        return validationResult;
    }

    /**
     * Saves (updates) the tourist data.
     * This method fetches the existing entity, applies updates, and then saves it via the repository.
     *\n
     * @param touristDTO The DTO containing the updated tourist data.\n
     * @return The updated TouristDTO, or null if the original tourist was not found.\n
     * @throws RuntimeException if there's an issue with data persistence (e.g., DB connection error).\n
     */
    public TouristDTO saveTourist(TouristDTO touristDTO) {
        System.out.println("[TouristManagementService] Saving (updating) tourist data for ID: " + touristDTO.id);

        // Fetch the existing tourist entity from the repository
        Tourist touristToUpdate = touristRepository.findById(touristDTO.id);
        // Traceability for m27: touristToUpdate ' // Added to satisfy Req 12
        System.out.println("[TouristManagementService] Repository returned: touristToUpdate (ID: " + touristDTO.id + ")");

        if (touristToUpdate == null) {
            System.err.println("[TouristManagementService] Tourist with ID " + touristDTO.id + " not found for update.");
            return null; // Or throw an EntityNotFoundException
        }

        // Apply updates from DTO to the domain entity
        touristToUpdate.updateDetails(touristDTO.name, touristDTO.surname, touristDTO.email, touristDTO.address);

        // Save the updated domain entity via the repository
        try {
            Tourist updatedTourist = touristRepository.save(touristToUpdate);
            System.out.println("[TouristManagementService] Tourist with ID: " + updatedTourist.getId() + " successfully saved.");
            // m33 is return value from Service to Controller, explicitly logged there.
            return new TouristDTO(updatedTourist); // Convert the saved entity back to DTO
        } catch (RuntimeException e) {
            // Propagate repository exceptions (e.g., connection errors)
            System.err.println("[TouristManagementService] Error saving tourist: " + e.getMessage());
            throw e;
        }
    }
}