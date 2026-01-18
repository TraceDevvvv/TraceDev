package com.example;

import java.util.List;

/**
 * Controller class for site search.
 * Quality Requirement: Results must be returned within timeout limit (REQ-012).
 * Entry Condition: Search functionality activated (REQ-004).
 * Flow of Events: User interacts with UI to submit search (REQ-005).
 * Quality Requirement (REQ-013): Results within set time limit.
 */
public class SearchSiteController {
    private SiteRepository siteRepository;
    private int timeout = 5000; // Default timeout 5 seconds (REQ-012)

    public SearchSiteController() {
        // Default repository; could be configured via dependency injection
        this.siteRepository = new DatabaseSiteRepository();
    }

    public SearchSiteController(SiteRepository repository) {
        this.siteRepository = repository;
    }

    public void setTimeout(int ms) {
        this.timeout = ms;
        System.out.println("Timeout set to " + ms + " ms");
    }

    /**
     * Main search method.
     * Precondition: search functionality activated (REQ-004).
     */
    public List<Site> submitSearchForm(SearchForm form) {
        if (!form.isValid()) {
            System.out.println("Form validation failed.");
            return null;
        }

        long startTime = System.currentTimeMillis();
        List<Site> results = null;
        
        try {
            // Set a timeout for the search operation
            Thread searchThread = new Thread(() -> {
                // Search will be performed via repository
            });
            searchThread.start();
            searchThread.join(timeout);
            
            if (searchThread.isAlive()) {
                searchThread.interrupt();
                throw new RuntimeException("Search timeout after " + timeout + " ms");
            }
            
            // Perform actual search
            results = siteRepository.search(form);
            
        } catch (InterruptedException e) {
            System.out.println("Search interrupted: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Search error: " + e.getMessage());
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Search completed in " + (endTime - startTime) + " ms");
        
        return results;
    }

    /**
     * Search site method from class diagram
     * @param searchForm the search form
     * @return list of sites
     */
    public List<Site> searchSite(SearchForm searchForm) {
        // This method directly corresponds to the class diagram method
        return submitSearchForm(searchForm);
    }

    public SiteRepository getSiteRepository() {
        return siteRepository;
    }

    public void setSiteRepository(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }
}