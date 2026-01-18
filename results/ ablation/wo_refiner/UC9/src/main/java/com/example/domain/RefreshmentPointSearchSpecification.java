package com.example.domain;

import com.example.application.SearchRefreshmentPointQuery;
import java.util.List;

/**
 * Specification that encapsulates search criteria for refreshment points.
 */
public class RefreshmentPointSearchSpecification implements ISpecification<RefreshmentPoint> {
    private SearchRefreshmentPointQuery query;

    public RefreshmentPointSearchSpecification(SearchRefreshmentPointQuery query) {
        this.query = query;
    }

    @Override
    public boolean isSatisfiedBy(RefreshmentPoint point) {
        // Apply search criteria from query.
        // Since repository (ETOURServerRepository) translates specification to external API criteria,
        // this method might not be called directly. However, it's implemented for completeness.
        boolean nameMatch = query.getName() == null || query.getName().isEmpty()
                || point.getName().toLowerCase().contains(query.getName().toLowerCase());
        boolean categoryMatch = query.getCategory() == null || query.getCategory().isEmpty()
                || point.getCategory().equalsIgnoreCase(query.getCategory());
        boolean amenitiesMatch = query.getAmenities() == null || query.getAmenities().isEmpty()
                || point.getAmenities().containsAll(query.getAmenities());
        // Note: maxDistance comparison would require current user coordinates, which are not provided.
        // Assumption: distance filtering is handled by external server.
        return nameMatch && categoryMatch && amenitiesMatch;
    }
}