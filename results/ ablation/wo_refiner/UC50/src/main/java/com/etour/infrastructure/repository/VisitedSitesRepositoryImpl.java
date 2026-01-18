package com.etour.infrastructure.repository;

import com.etour.application.dto.SiteDto;
import com.etour.application.repository.VisitedSitesRepository;
import com.etour.infrastructure.ConnectionManager;
import com.etour.infrastructure.ExternalDataSource;
import com.etour.infrastructure.exception.ConnectionError;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.concurrent.Callable;

/**
 * Repository implementation that fetches visited sites data from an external source.
 */
public class VisitedSitesRepositoryImpl implements VisitedSitesRepository {
    private ExternalDataSource dataSource;
    private ConnectionManager connectionManager;

    public VisitedSitesRepositoryImpl(ExternalDataSource dataSource, ConnectionManager connectionManager) {
        this.dataSource = dataSource;
        this.connectionManager = connectionManager;
    }

    @Override
    public List<SiteDto> findSitesByTouristId(String touristId) {
        // Check connection availability (REQ-010)
        if (!connectionManager.isConnectionAvailable()) {
            throw new ConnectionError("No connection available");
        }

        try {
            // Use retry logic for the fetch operation (REQ-010)
            Callable<Object> fetchOperation = () -> dataSource.fetchVisitedSites(touristId);
            @SuppressWarnings("unchecked")
            List<Map<String, Object>> rawData = (List<Map<String, Object>>) connectionManager.retryOnFailure(fetchOperation, 3);
            return transformToSiteDtoList(rawData);
        } catch (ConnectionError e) {
            throw e; // Propagate the connection error
        } catch (Exception e) {
            throw new ConnectionError("Failed to fetch visited sites: " + e.getMessage());
        }
    }

    /**
     * Transforms raw data from the external source into a list of SiteDto objects.
     * @param rawData List of maps containing raw site data
     * @return List of SiteDto
     */
    private List<SiteDto> transformToSiteDtoList(List<Map<String, Object>> rawData) {
        List<SiteDto> siteDtos = new ArrayList<>();
        for (Map<String, Object> rawSite : rawData) {
            String id = (String) rawSite.get("id");
            String name = (String) rawSite.get("name");
            String location = (String) rawSite.get("location");
            siteDtos.add(new SiteDto(id, name, location));
        }
        return siteDtos;
    }
}