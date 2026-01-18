package com.example;

/**
 * Command interface for processing search requests.
 */
public interface ProcessSearchCommand {
    /**
     * Executes the search processing command.
     * @param criteria the search criteria.
     * @param location the location.
     */
    void execute(SearchCriteria criteria, Location location);
}