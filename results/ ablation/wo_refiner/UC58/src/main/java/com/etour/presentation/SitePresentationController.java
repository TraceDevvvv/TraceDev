package com.etour.presentation;

import com.etour.dto.ErrorDTO;
import com.etour.dto.SiteDTO;
import com.etour.usecase.ViewSiteCardUseCaseController;
import java.util.Optional;

/**
 * Presentation controller that coordinates between UI and use case.
 * Requirement R9: Handle server disconnection and return ErrorDTO.
 */
public class SitePresentationController {
    private ViewSiteCardUseCaseController useCaseController;

    public SitePresentationController(ViewSiteCardUseCaseController useCaseController) {
        this.useCaseController = useCaseController;
    }

    /**
     * Displays the site card for the given site ID.
     *
     * @param siteId the site ID
     * @return the SiteDTO if successful, null otherwise
     */
    public SiteDTO displaySiteCard(String siteId) {
        try {
            Optional<SiteDTO> siteDTOOpt = useCaseController.execute(siteId);
            return siteDTOOpt.orElse(null);
        } catch (Exception e) {
            // Requirement R9: Handle server disconnection and return ErrorDTO.
            // In the sequence diagram, this method returns ErrorDTO, but here we return SiteDTO.
            // We assume that errors are thrown as exceptions and caught by the UI.
            // The UI will call handleServerDisconnection separately.
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles server disconnection and returns an ErrorDTO.
     * Requirement R9: Explicit traceability link.
     *
     * @return the ErrorDTO for disconnection
     */
    public ErrorDTO handleServerDisconnection() {
        return new ErrorDTO("CONN-ERR", "Server connection interrupted", java.time.LocalDateTime.now());
    }
}