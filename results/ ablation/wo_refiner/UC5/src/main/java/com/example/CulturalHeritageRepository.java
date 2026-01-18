package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository implementation that accesses ETOUR data source.
 * Implements error handling per requirement REQ-009.
 */
public class CulturalHeritageRepository implements ICulturalHeritageRepository {
    private ETOURDataSource dataSource;

    public CulturalHeritageRepository() {
        this.dataSource = new ETOURDataSource();
    }

    public CulturalHeritageRepository(ETOURDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CulturalHeritage> findAllByCriteria(String criteria) {
        try {
            cacheData();
            return dataSource.fetchCulturalHeritageData(criteria);
        } catch (Exception e) {
            handleRepositoryError(e);
            // Return empty list on error
            return new ArrayList<>();
        }
    }

    @Override
    public CulturalHeritage findById(String id) {
        // Implement retry logic as per sequence diagram and requirement REQ-009
        int retryCount = 0;
        int maxRetries = dataSource.getMaxRetries();
        RuntimeException lastException = null;

        while (retryCount <= maxRetries) {
            try {
                cacheData(); // simulate caching
                return dataSource.fetchCulturalHeritageById(id);
            } catch (RuntimeException e) {
                lastException = e;
                handleRepositoryError(e);
                retryCount++;
                if (retryCount > maxRetries) {
                    break;
                }
                System.out.println("Retry attempt " + retryCount + " for id: " + id);
                // Simulate delay before retry
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        // If all retries failed, throw a RepositoryException
        throw new RepositoryException("Failed to fetch cultural heritage after " + maxRetries + " retries", lastException);
    }

    /**
     * Simulates caching data.
     */
    private void cacheData() {
        // In a real implementation, this would cache data from the data source
        System.out.println("Caching data...");
    }

    /**
     * Handles repository-level errors.
     * Added to satisfy requirement REQ-009.
     */
    private void handleRepositoryError(Exception exception) {
        System.err.println("Repository error occurred: " + exception.getMessage());
        // Additional error handling logic would go here (logging, metrics, etc.)
    }

    public ETOURDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(ETOURDataSource dataSource) {
        this.dataSource = dataSource;
    }
}