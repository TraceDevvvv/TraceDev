package com.example.presentation;

import com.example.application.model.Site;
import com.example.application.usecases.GetSiteDetailsUseCase;

/**
 * Controller in MVC pattern.
 * Handles requests from the view and coordinates with the use case.
 */
public class SiteDetailsController {
    private GetSiteDetailsUseCase getSiteDetailsUseCase;

    /**
     * Constructor.
     *
     * @param getSiteDetailsUseCase the use case instance to delegate business logic
     */
    public SiteDetailsController(GetSiteDetailsUseCase getSiteDetailsUseCase) {
        this.getSiteDetailsUseCase = getSiteDetailsUseCase;
    }

    /**
     * Called when a site is selected in the view.
     *
     * @param siteId the id of the selected site
     * @return the Site object, or null if retrieval failed
     */
    public Site onSiteSelected(String siteId) {
        return getSiteDetailsUseCase.execute(siteId);
    }
}