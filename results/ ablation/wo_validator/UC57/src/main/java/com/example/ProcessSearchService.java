package com.example;

/**
 * Service that implements the ProcessSearchCommand for processing search requests.
 */
public class ProcessSearchService implements ProcessSearchCommand {
    /**
     * Executes the search processing command asynchronously.
     * This simulation logs the action without performing real async processing.
     * @param criteria the search criteria.
     * @param location the location.
     */
    @Override
    public void execute(SearchCriteria criteria, Location location) {
        // Simulate async processing by logging.
        System.out.println("ProcessSearchService: Started async processing of search request.");
        // In a real implementation, this might involve queuing or background processing.
    }
}