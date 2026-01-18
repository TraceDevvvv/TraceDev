package com.example.application;

import com.example.domain.ISpecification;
import com.example.domain.RefreshmentPoint;
import com.example.domain.RefreshmentPointSearchSpecification;
import com.example.infrastructure.IRefreshmentPointRepository;
import com.example.dto.RefreshmentPointDTO;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles the search query by applying specification and mapping results.
 */
public class SearchRefreshmentPointQueryHandler {
    private IRefreshmentPointRepository repository;

    public SearchRefreshmentPointQueryHandler(IRefreshmentPointRepository repository) {
        this.repository = repository;
    }

    /**
     * Handles the search query as per sequence diagram.
     * @param query the search criteria
     * @return result containing list of DTOs
     */
    public SearchRefreshmentPointResult handle(SearchRefreshmentPointQuery query) {
        // Construct specification from query
        RefreshmentPointSearchSpecification spec = new RefreshmentPointSearchSpecification(query);

        // Use specification via ISpecification interface (as shown in sequence diagram)
        ISpecification<RefreshmentPoint> iSpec = spec; // upcast to interface

        // Retrieve points using repository pattern with specification
        List<RefreshmentPoint> points = repository.findAll(iSpec);

        // Map domain entities to DTOs
        List<RefreshmentPointDTO> pointDTOs = points.stream()
                .map(point -> new RefreshmentPointDTO(
                        point.getId(),
                        point.getName(),
                        point.getCategory(),
                        point.getCoordinates().getLatitude(),
                        point.getCoordinates().getLongitude(),
                        point.getAmenities()))
                .collect(Collectors.toList());

        // Construct result
        return new SearchRefreshmentPointResult(pointDTOs);
    }
}