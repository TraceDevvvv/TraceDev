package com.agency.modifycomment.service;

import com.agency.modifycomment.model.Site;
import com.agency.modifycomment.repository.SiteRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing business logic related to Site objects.
 * It acts as an intermediary between the controller and the repository,
 * providing methods for site-related operations.
 */
public class SiteService {
    private final SiteRepository siteRepository;

    /**
     * Constructs a new SiteService with the given SiteRepository.
     *
     * @param siteRepository The repository for accessing site data.
     */
    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    /**
     * Retrieves a list of all available sites.
     *
     * @return A List of Site objects.
     */
    public List<Site> getAllSites() {
        return siteRepository.findAll();
    }

    /**
     * Finds a site by its unique identifier.
     *
     * @param siteId The ID of the site to find.
     * @return An Optional containing the Site if found, or an empty Optional if not found.
     */
    public Optional<Site> getSiteById(String siteId) {
        return siteRepository.findById(siteId);
    }

    /**
     * Adds a new site to the system.
     *
     * @param site The Site object to add.
     * @return The added Site object.
     * @throws IllegalArgumentException if the site or site ID is null.
     */
    public Site addSite(Site site) {
        if (site == null || site.getId() == null) {
            throw new IllegalArgumentException("Site and Site ID cannot be null.");
        }
        return siteRepository.save(site);
    }

    /**
     * Updates an existing site.
     *
     * @param site The Site object with updated information.
     * @return The updated Site object.
     * @throws IllegalArgumentException if the site or site ID is null, or if the site does not exist.
     */
    public Site updateSite(Site site) {
        if (site == null || site.getId() == null) {
            throw new IllegalArgumentException("Site and Site ID cannot be null.");
        }
        if (!siteRepository.existsById(site.getId())) {
            throw new IllegalArgumentException("Site with ID " + site.getId() + " does not exist.");
        }
        return siteRepository.save(site);
    }

    /**
     * Deletes a site by its ID.
     *
     * @param siteId The ID of the site to delete.
     * @return true if the site was successfully deleted, false otherwise.
     */
    public boolean deleteSite(String siteId) {
        return siteRepository.deleteById(siteId);
    }
}