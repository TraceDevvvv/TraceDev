package com.example.application.usecases;

import com.example.application.model.Site;
import com.example.application.repositories.SiteRepository;

/**
 * Use case for retrieving site details by site id.
 * Implements the business logic for the site details retrieval.
 */
public class GetSiteDetailsUseCase {
    private SiteRepository siteRepository;

    /**
     * Constructor.
     *
     * @param siteRepository the repository to use for data access
     */
    public GetSiteDetailsUseCase(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    /**
     * Executes the use case: find site by id.
     *
     * @param siteId the id of the site to retrieve
     * @return the Site object, or null if not found or error occurs
     */
    public Site execute(String siteId) {
        // Delegate to repository; any business validation could be added here.
        return siteRepository.findById(siteId);
    }
}