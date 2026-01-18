package com.example.pointofrest;

/**
 * Application service for PointOfRest-related business logic.
 * It orchestrates data retrieval and transformation from the repository layer
 * to provide data suitable for the presentation layer (DTOs).
 */
public class PointOfRestApplicationService {

    private IPointOfRestRepository pointOfRestRepository;

    /**
     * Constructor for PointOfRestApplicationService.
     *
     * @param pointOfRestRepository The repository to fetch PointOfRest data.
     */
    public PointOfRestApplicationService(IPointOfRestRepository pointOfRestRepository) {
        this.pointOfRestRepository = pointOfRestRepository;
    }

    /**
     * Retrieves the detailed information for a specific PointOfRest,
     * converting it into a DTO suitable for display.
     *
     * @param id The unique identifier of the PointOfRest.
     * @return A PointOfRestDetailsDto containing the details.
     * @throws Exception if the PointOfRest cannot be found or a repository error occurs.
     */
    public PointOfRestDetailsDto getPointOfRestDetails(String id) throws Exception {
        System.out.println("[PointOfRestApplicationService] Getting details for PointOfRest ID: " + id);
        try {
            // Retrieve PointOfRest entity from the repository
            PointOfRest pointOfRest = pointOfRestRepository.findById(id);

            // Convert the entity to a DTO
            PointOfRestDetailsDto dto = convertToDto(pointOfRest);
            System.out.println("[PointOfRestApplicationService] Converted PointOfRest to DTO for ID: " + id);
            return dto;
        } catch (Exception e) {
            System.err.println("[PointOfRestApplicationService] Error getting details for ID " + id + ": " + e.getMessage());
            throw e; // Re-throw the exception
        }
    }

    /**
     * Converts a PointOfRest entity to a PointOfRestDetailsDto.
     * This private helper method handles the mapping logic.
     *
     * @param pointOfRest The PointOfRest entity.
     * @return A PointOfRestDetailsDto.
     */
    private PointOfRestDetailsDto convertToDto(PointOfRest pointOfRest) {
        if (pointOfRest == null) {
            return null;
        }
        return new PointOfRestDetailsDto(
            pointOfRest.id,
            pointOfRest.name,
            pointOfRest.address,
            pointOfRest.type,
            pointOfRest.description,
            pointOfRest.contactInfo
        );
    }
}