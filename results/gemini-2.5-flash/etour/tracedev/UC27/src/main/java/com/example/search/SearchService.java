package com.example.search;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Application service responsible for handling search logic and coordinating with repositories.
 * This class directly maps to the 'SearchService' class in the UML diagram.
 */
public class SearchService {
    // Private field for SiteRepository as specified in the UML diagram
    private SiteRepository siteRepository;
    // Private field for PerformanceMonitor as specified in the UML diagram (relationship 'monitors_performance')
    private PerformanceMonitor performanceMonitor;

    // Public field as specified in the UML diagram, Added to satisfy requirement R11
    public long maxSearchTimeMillis;

    /**
     * Constructor for SearchService.
     * @param siteRepository The repository to use for accessing site data.
     * @param performanceMonitor The monitor to track operation performance (R11).
     */
    public SearchService(SiteRepository siteRepository, PerformanceMonitor performanceMonitor) {
        this.siteRepository = siteRepository;
        this.performanceMonitor = performanceMonitor;
        this.maxSearchTimeMillis = 500; // Default max search time to 500ms (R11)
    }

    /**
     * Searches for sites based on the provided criteria, monitoring performance and handling timeouts.
     * This method implements part of the sequence diagram's successful search and timeout flows.
     * @param criteria A map of key-value pairs representing search criteria.
     * @return A list of Site objects matching the criteria.
     * @throws ConnectionError if the site repository encounters a connection issue.
     * @throws TimeLimitExceededException if the search operation exceeds the 'maxSearchTimeMillis'.
     */
    public List<Site> searchSites(Map<String, String> criteria) throws ConnectionError, TimeLimitExceededException {
        System.out.println("  [SearchService] searchSites(" + criteria + ")");

        // Sequence Diagram: SearchService -> PerfMonitor : startTimer("searchSites")
        performanceMonitor.startTimer("searchSites");

        List<Site> sites;
        try {
            // Simulate a delay for timeout testing (if forceTimeout criteria is present)
            if (criteria != null && criteria.containsKey("forceTimeout")) {
                int simulatedDelay = Integer.parseInt(criteria.getOrDefault("timeoutDelay", "1000"));
                System.out.println("    [SearchService] Simulating " + simulatedDelay + "ms delay for timeout test.");
                TimeUnit.MILLISECONDS.sleep(simulatedDelay);
            }

            // Sequence Diagram: SearchService -> SiteRepository : findByCriteria(criteria)
            sites = siteRepository.findByCriteria(criteria);
        } catch (ConnectionError e) {
            // Sequence Diagram: SearchService --x SearchController : throws ConnectionError("ETOUR")
            System.out.println("  [SearchService] Caught ConnectionError: " + e.message);
            throw e; // Re-throw the exception to SearchController
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Search interrupted", e);
        } finally {
            // Sequence Diagram: PerfMonitor --> SearchService : stopTimer("searchSites") : duration
            long duration = performanceMonitor.stopTimer("searchSites");

            // Sequence Diagram: alt duration > maxSearchTimeMillis
            if (performanceMonitor.checkTimeout(duration, maxSearchTimeMillis)) {
                // Sequence Diagram: SearchService --x SearchController : throws TimeLimitExceededException("Search took too long")
                String errorMessage = "Search took too long (" + duration + "ms > " + maxSearchTimeMillis + "ms)";
                System.out.println("  [SearchService] " + errorMessage);
                throw new TimeLimitExceededException(errorMessage);
            }
        }

        System.out.println("  [SearchService] return List<Site> (Found " + sites.size() + " sites)");
        return sites;
    }
}