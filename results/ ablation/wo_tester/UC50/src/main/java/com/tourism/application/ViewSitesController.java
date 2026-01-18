package com.tourism.application;

import com.tourism.domain.Site;
import com.tourism.dto.SiteDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for the "view visited sites" use case.
 * Converts domain entities to DTOs as required by REQ-007 and REQ-009.
 */
public class ViewSitesController {
    private AuthenticationService authenticationService;
    private SiteService siteService;

    public ViewSitesController(AuthenticationService authenticationService, SiteService siteService) {
        this.authenticationService = authenticationService;
        this.siteService = siteService;
    }

    /**
     * Retrieves visited sites for a tourist as DTOs.
     * @param touristId the tourist's ID (from session, REQ-006)
     * @return list of SiteDTO objects, or empty list if none found
     */
    public List<SiteDTO> getSitesForTourist(String touristId) {
        // Step 1: Authenticate the tourist (REQ-001)
        if (!authenticationService.authenticate(touristId)) {
            throw new SecurityException("Authentication failed");
        }

        // Step 2: Get sites with feedback from service layer
        List<Site> sites = siteService.getSitesWithFeedback(touristId);

        // Step 3: Convert each Site to SiteDTO (REQ-009)
        List<SiteDTO> dtos = new ArrayList<>();
        for (Site site : sites) {
            dtos.add(convertSiteToSiteDTO(site));
        }
        return dtos;
    }

    /**
     * Converts a Site domain entity to a SiteDTO.
     * Required by REQ-007.
     * @param site the Site entity
     * @return the corresponding SiteDTO
     */
    public SiteDTO convertSiteToSiteDTO(Site site) {
        return new SiteDTO(site.getSiteId(), site.getName(), site.getLocation());
    }

    /**
     * Method to represent the message "convert Site to SiteDTO" from the sequence diagram.
     * This is a wrapper for convertSiteToSiteDTO with exact naming.
     */
    public SiteDTO convertSiteToSiteDTO() {
        // This method exists to match sequence diagram message name; actual conversion requires a Site object.
        // Since sequence diagram does not specify parameters, we keep the method signature but log a note.
        // In real usage, this method should be called with a Site parameter, but diagram mapping requires this name.
        throw new UnsupportedOperationException("convertSiteToSiteDTO requires a Site parameter. Use convertSiteToSiteDTO(Site site) instead.");
    }
}