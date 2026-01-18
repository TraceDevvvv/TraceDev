package ViewFeedback_1765890483;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Manages a collection of Site objects.
 * Provides functionalities to add new sites and retrieve sites by their ID.
 */
public class SiteManager {
    private final List<Site> sites;

    /**
     * Constructs a new SiteManager.
     * Initializes an empty list to store sites.
     */
    public SiteManager() {
        this.sites = new ArrayList<>();
    }

    /**
     * Adds a new site to the manager.
     *
     * @param site The Site object to be added.
     */
    public void addSite(Site site) {
        if (site != null) {
            this.sites.add(site);
        }
    }

    /**
     * Retrieves a site by its unique ID.
     *
     * @param siteId The ID of the site to retrieve.
     * @return An Optional containing the Site if found, or an empty Optional if not found.
     */
    public Optional<Site> getSiteById(String siteId) {
        return sites.stream()
                .filter(site -> site.getId().equals(siteId))
                .findFirst();
    }

    /**
     * Returns an unmodifiable list of all sites currently managed.
     *
     * @return A List of Site objects.
     */
    public List<Site> getAllSites() {
        return Collections.unmodifiableList(sites);
    }
}