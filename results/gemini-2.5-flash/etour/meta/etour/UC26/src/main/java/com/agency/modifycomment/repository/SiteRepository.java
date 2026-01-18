package com.agency.modifycomment.repository;

import com.agency.modifycomment.model.Site;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repository for managing Site objects.
 * This class simulates a data access layer for Site entities using an in-memory map.
 */
public class SiteRepository {
    // Using ConcurrentHashMap to simulate a data store for thread-safe operations
    private final ConcurrentHashMap<String, Site> sites = new ConcurrentHashMap<>();

    /**
     * Constructor to pre-populate some dummy data for demonstration purposes.
     */
    public SiteRepository() {
        // Pre-populate with some sample sites
        sites.put("site1", new Site("site1", "Googleplex"));
        sites.put("site2", new Site("site2", "Apple Park"));
        sites.put("site3", new Site("site3", "Microsoft Campus"));
    }

    /**
     * Finds a site by its ID.
     *
     * @param id The ID of the site to find.
     * @return An Optional containing the Site if found, or an empty Optional if not found.
     */
    public Optional<Site> findById(String id) {
        return Optional.ofNullable(sites.get(id));
    }

    /**
     * Returns a list of all sites.
     *
     * @return A List of all Site objects currently stored.
     */
    public List<Site> findAll() {
        return new ArrayList<>(sites.values());
    }

    /**
     * Saves a site. If a site with the same ID already exists, it will be updated.
     * Otherwise, a new site will be added.
     *
     * @param site The Site object to save.
     * @return The saved Site object.
     */
    public Site save(Site site) {
        if (site == null || site.getId() == null) {
            throw new IllegalArgumentException("Site and Site ID cannot be null.");
        }
        sites.put(site.getId(), site);
        return site;
    }

    /**
     * Deletes a site by its ID.
     *
     * @param id The ID of the site to delete.
     * @return true if the site was deleted, false otherwise.
     */
    public boolean deleteById(String id) {
        return sites.remove(id) != null;
    }

    /**
     * Checks if a site with the given ID exists.
     *
     * @param id The ID of the site to check.
     * @return true if a site with the ID exists, false otherwise.
     */
    public boolean existsById(String id) {
        return sites.containsKey(id);
    }
}