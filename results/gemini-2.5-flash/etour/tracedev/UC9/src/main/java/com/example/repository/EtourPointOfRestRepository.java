package com.example.repository;

import com.example.dto.PointOfRestDto;
import com.example.exceptions.DataAccessException;
import com.example.exceptions.EtourConnectionException;
import com.example.infrastructure.EtourApiAdapter;
import com.example.model.PointOfRest;
import com.example.model.SearchCriteria;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of IPointOfRestRepository that uses EtourApiAdapter to fetch data.
 * This class is responsible for translating between the domain entities (PointOfRest)
 * and the DTOs used by the external adapter (PointOfRestDto).
 */
public class EtourPointOfRestRepository implements IPointOfRestRepository {

    private final EtourApiAdapter etourApiAdapter;

    /**
     * Constructs an EtourPointOfRestRepository with a dependency on EtourApiAdapter.
     *
     * @param etourApiAdapter The adapter to interact with the ETOUR API.
     */
    public EtourPointOfRestRepository(EtourApiAdapter etourApiAdapter) {
        this.etourApiAdapter = etourApiAdapter;
    }

    @Override
    public List<PointOfRest> findByCriteria(SearchCriteria criteria) throws DataAccessException {
        System.out.println("Repository: Finding points by criteria: " + criteria);
        try {
            // Convert domain criteria to API query parameters
            // Call the adapter's getPointsOfRest method, aligning with sequence diagram
            List<PointOfRestDto> dtos = etourApiAdapter.getPointsOfRest(criteria.toQueryParams());
            // Map DTOs received from the adapter to domain entities
            return dtos.stream()
                    .map(this::mapDtoToEntity)
                    .collect(Collectors.toList());
        } catch (EtourConnectionException e) {
            // Wrap lower-level external service exception into a data access exception
            throw new DataAccessException("Failed to fetch data from ETOUR due to connection issues.", e);
        }
    }

    /**
     * Maps a PointOfRestDto object received from the adapter to a domain PointOfRest entity.
     *
     * @param dto The DTO to map.
     * @return The corresponding PointOfRest entity.
     */
    private PointOfRest mapDtoToEntity(PointOfRestDto dto) {
        // Simple one-to-one mapping
        return new PointOfRest(
                dto.etourId,
                dto.etourName,
                dto.etourLocation,
                dto.etourDescription,
                dto.etourType
        );
    }
}