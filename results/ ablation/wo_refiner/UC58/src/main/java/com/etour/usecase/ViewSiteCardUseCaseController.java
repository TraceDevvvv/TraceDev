package com.etour.usecase;

import com.etour.domain.Site;
import com.etour.dto.ErrorDTO;
import com.etour.dto.SiteDTO;
import com.etour.mapper.SiteMapper;
import com.etour.repository.SiteRepository;
import java.util.Optional;

/**
 * Use case controller for viewing a site card.
 * Implements business logic including location validation and error handling.
 */
public class ViewSiteCardUseCaseController {
    private SiteRepository siteRepository;
    private SiteMapper siteMapper;

    public ViewSiteCardUseCaseController(SiteRepository siteRepository, SiteMapper siteMapper) {
        this.siteRepository = siteRepository;
        this.siteMapper = siteMapper;
    }

    /**
     * Executes the use case to retrieve site details.
     * Requirement R4: Validates location before proceeding.
     * Requirements R7-R8: Returns Optional<SiteDTO> to avoid nulls.
     *
     * @param siteId the site ID
     * @return an Optional containing the SiteDTO if found and location valid, empty otherwise
     */
    public Optional<SiteDTO> execute(String siteId) {
        // Requirement R4: Validate location before proceeding.
        // For simulation, we check if the current area is valid.
        // In a real scenario, the area would be retrieved from the user's context.
        String currentArea = "Paris"; // Example current area
        boolean locationValid = validateAccess(currentArea);
        if (!locationValid) {
            return Optional.empty();
        }

        try {
            Optional<Site> siteOpt = siteRepository.findById(siteId);
            if (siteOpt.isPresent()) {
                SiteDTO dto = siteMapper.toDTO(siteOpt.get());
                return Optional.of(dto);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            // Requirement R9: Handle errors and return ErrorDTO (though note: method returns Optional<SiteDTO>).
            // In the sequence diagram, handleError returns ErrorDTO, but execute returns Optional<SiteDTO>.
            // We assume that errors are propagated as exceptions and handled by the caller via handleError.
            handleError(e);
            throw e; // Rethrow to be caught by the presentation layer.
        }
    }

    /**
     * Validates if the user has access based on location.
     * Requirement R4: Validate location before displaying site card.
     *
     * @param area the current area
     * @return true if access is allowed, false otherwise
     */
    boolean validateAccess(String area) {
        // For simplicity, assume access is granted only if area is "Paris".
        return "Paris".equals(area);
    }

    /**
     * Handles exceptions and creates an ErrorDTO.
     * Requirement R9: Error handling.
     *
     * @param exception the exception
     * @return the ErrorDTO
     */
    protected ErrorDTO handleError(Exception exception) {
        return ErrorDTO.fromException(exception);
    }
}