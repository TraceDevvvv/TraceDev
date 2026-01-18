package com.etour.repositories;

import com.etour.adapters.SearchQuery;
import com.etour.entities.CulturalObject;
import com.etour.external.ExternalDataSource;
import com.etour.usecases.SearchCulturalObjectsUseCase;
import java.util.List;

/**
 * Implementation of the cultural object repository.
 * Depends on an external data source to fetch data.
 */
public class CulturalObjectRepositoryImpl implements CulturalObjectRepository {
    private final ExternalDataSource dataSource;

    public CulturalObjectRepositoryImpl(ExternalDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CulturalObject> findByCriteria(SearchQuery query) {
        try {
            // Delegate to data source (step 8 in sequence diagram)
            return dataSource.fetchCulturalObjects(query);
        } catch (SearchCulturalObjectsUseCase.ServerConnectionException e) {
            // Re-throw the connection exception to be handled by use case
            throw e;
        } catch (Exception e) {
            // Wrap other exceptions
            throw new RuntimeException("Repository error while fetching cultural objects", e);
        }
    }

    @Override
    public int countByCriteria(SearchQuery query) {
        try {
            // Delegate to data source
            return dataSource.countCulturalObjects(query);
        } catch (SearchCulturalObjectsUseCase.ServerConnectionException e) {
            // Re-throw the connection exception
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Repository error while counting cultural objects", e);
        }
    }
}