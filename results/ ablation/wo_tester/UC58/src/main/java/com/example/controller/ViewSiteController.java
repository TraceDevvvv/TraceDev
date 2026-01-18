package com.example.controller;

import com.example.service.SiteServiceInterface;
import com.example.dto.SiteDetailsDTO;
import com.example.view.SiteDetailsView;

/**
 * Controller for handling requests to view site details.
 */
public class ViewSiteController {
    private SiteServiceInterface siteService;
    private SiteDetailsView siteDetailsView;

    public ViewSiteController(SiteServiceInterface siteService, SiteDetailsView siteDetailsView) {
        this.siteService = siteService;
        this.siteDetailsView = siteDetailsView;
    }

    /**
     * Handles a request to view site details for a given site ID.
     * @param id the site ID
     * @return SiteDetailsDTO containing the site details
     */
    public SiteDetailsDTO handleViewSiteRequest(String id) {
        try {
            SiteDetailsDTO dto = siteService.getSiteDetails(id);
            siteDetailsView.displaySiteDetails(dto);
            return dto;
        } catch (RuntimeException e) {
            // Assuming runtime exceptions are thrown for connection errors
            siteDetailsView.displayErrorMessage(e.getMessage());
            return null;
        }
    }
}