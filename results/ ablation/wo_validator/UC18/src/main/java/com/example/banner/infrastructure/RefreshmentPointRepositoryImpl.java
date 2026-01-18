package com.example.banner.infrastructure;

import com.example.banner.application.IRefreshmentPointRepository;
import com.example.banner.domain.RefreshmentPoint;

/**
 * Infrastructure implementation of the refreshment point repository.
 * Uses the ConventionDataAdapter to fetch data.
 */
public class RefreshmentPointRepositoryImpl implements IRefreshmentPointRepository {
    private ConventionDataAdapter conventionDataAdapter;

    /**
     * Constructor.
     * @param adapter the data adapter
     */
    public RefreshmentPointRepositoryImpl(ConventionDataAdapter adapter) {
        this.conventionDataAdapter = adapter;
    }

    @Override
    public RefreshmentPoint findById(String id) {
        // Delegate to adapter to load data from external system
        return conventionDataAdapter.loadRefreshmentPointData(id);
    }

    @Override
    public void save(RefreshmentPoint refreshmentPoint) {
        // In a real system, this would persist the entity via adapter
        System.out.println("RefreshmentPointRepositoryImpl: Saved " + refreshmentPoint.getId());
    }
}