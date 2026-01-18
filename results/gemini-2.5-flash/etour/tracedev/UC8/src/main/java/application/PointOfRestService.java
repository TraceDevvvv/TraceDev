package application;

import domain.IPointOfRestRepository;
import domain.PointOfRest;
import dto.PointOfRestDTO;
import infrastructure.ConnectionException;

/**
 * Application Layer: Service component for Point of Rest management.
 * Orchestrates business logic, interacts with repositories, and handles DTO conversions.
 */
public class PointOfRestService {
    private IPointOfRestRepository pointOfRestRepository;
    private ErroredUseCase erroredUseCase; // Added to satisfy requirement R12

    // Constructor for dependency injection
    public PointOfRestService(IPointOfRestRepository pointOfRestRepository, ErroredUseCase erroredUseCase) {
        this.pointOfRestRepository = pointOfRestRepository;
        this.erroredUseCase = erroredUseCase;
    }

    /**
     * Retrieves the details of a Point of Rest by its ID.
     * Converts the domain entity to a DTO for presentation.
     * @param id The ID of the Point of Rest.
     * @return A PointOfRestDTO containing the details, or null if not found.
     */
    public PointOfRestDTO getPointOfRestDetails(String id) {
        System.out.println("[Service] Getting Point of Rest details for ID: " + id);
        PointOfRest point = null;
        try {
            // Interaction: Service -> IRepository: findById(id)
            point = pointOfRestRepository.findById(id);
        } catch (ConnectionException e) {
            System.err.println("[Service] Connection error retrieving Point of Rest: " + e.getMessage());
            // Interaction: Service -> ErroredUseCase: handleError(errorMessage)
            erroredUseCase.handleError("ETOUR server connection lost while retrieving Point of Rest details.");
            return null; // Indicate failure to retrieve
        }


        if (point != null) {
            // Interaction: Service -> DTO: createFromEntity(PointOfRest entity)
            return PointOfRestDTO.createFromEntity(point);
        }
        System.out.println("[Service] Point of Rest with ID " + id + " not found.");
        return null;
    }

    /**
     * Validates the provided PointOfRestDTO and prepares it for an update.
     * This might involve more complex business rules than just basic field validation.
     * For demonstration, it performs basic checks.
     * @param dto The DTO to validate.
     * @return true if the DTO is valid and can proceed with an update, false otherwise.
     * @throws IllegalArgumentException if validation fails with specific details.
     */
    public boolean validateAndPrepareUpdate(PointOfRestDTO dto) {
        System.out.println("[Service] Validating and preparing update for Point of Rest ID: " + dto.id);
        if (dto == null) {
            throw new IllegalArgumentException("PointOfRestDTO cannot be null.");
        }
        if (dto.id == null || dto.id.trim().isEmpty()) {
            throw new IllegalArgumentException("Point of Rest ID cannot be empty.");
        }
        if (dto.name == null || dto.name.trim().isEmpty()) {
            throw new IllegalArgumentException("Point of Rest name cannot be empty.");
        }
        if (dto.address == null || dto.address.trim().isEmpty()) {
            throw new IllegalArgumentException("Point of Rest address cannot be empty.");
        }
        // Additional business logic validation could go here
        System.out.println("[Service] Point of Rest DTO validated successfully.");
        return true;
    }

    /**
     * Finalizes the update of a Point of Rest after validation and confirmation.
     * Converts the DTO to a domain entity, updates it, and persists it.
     * @param dto The DTO containing the finalized updated details.
     * @return true if the update was successful, false otherwise.
     */
    public boolean finalizePointOfRestUpdate(PointOfRestDTO dto) {
        System.out.println("[Service] Finalizing update for Point of Rest ID: " + dto.id);
        // Retrieve the existing entity to ensure we're updating the correct one and preserve uneditable fields
        PointOfRest existingPoint = null;
        try {
            existingPoint = pointOfRestRepository.findById(dto.id);
        } catch (ConnectionException e) {
            System.err.println("[Service] Connection error retrieving existing Point of Rest for update: " + e.getMessage());
            erroredUseCase.handleError("ETOUR server connection lost while finalizing Point of Rest update.");
            return false;
        }

        if (existingPoint == null) {
            System.err.println("[Service] Error: Point of Rest with ID " + dto.id + " not found for update.");
            return false;
        }

        // Interaction: PointOfRest.updateDetails(dto)
        existingPoint.updateDetails(dto);

        try {
            // Interaction: Service -> IRepository: save(PointOfRest entity)
            pointOfRestRepository.save(existingPoint);
            System.out.println("[Service] Point of Rest ID " + dto.id + " updated and saved successfully.");
            return true;
        } catch (ConnectionException e) {
            System.err.println("[Service] Connection error saving Point of Rest: " + e.getMessage());
            // Interaction: Service -> ErroredUseCase: handleError(errorMessage)
            erroredUseCase.handleError("ETOUR server connection lost while saving Point of Rest update.");
            return false;
        } catch (Exception e) {
            System.err.println("[Service] Error saving Point of Rest ID " + dto.id + ": " + e.getMessage());
            return false;
        }
    }
}