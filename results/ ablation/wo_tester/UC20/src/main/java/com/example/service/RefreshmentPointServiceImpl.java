package com.example.service;

import com.example.entity.RefreshmentPoint;
import com.example.repository.RefreshmentPointRepository;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of refreshment point service.
 */
public class RefreshmentPointServiceImpl implements RefreshmentPointService {
    private RefreshmentPointRepository refreshmentPointRepository;

    public RefreshmentPointServiceImpl(RefreshmentPointRepository refreshmentPointRepository) {
        this.refreshmentPointRepository = refreshmentPointRepository;
    }

    @Override
    public List<RefreshmentPoint> searchRefreshmentPoints() {
        return refreshmentPointRepository.findAll();
    }

    @Override
    public RefreshmentPoint getRefreshmentPointById(String pointId) {
        Optional<RefreshmentPoint> pointOpt = refreshmentPointRepository.findById(pointId);
        return pointOpt.orElse(null);
    }

    @Override
    public boolean hasReachedBannerLimit(String pointId) {
        RefreshmentPoint point = getRefreshmentPointById(pointId);
        if (point != null) {
            return point.hasReachedMaxBannerLimit();
        }
        return false;
    }

    @Override
    public void incrementBannerCount(String pointId) {
        RefreshmentPoint point = getRefreshmentPointById(pointId);
        if (point != null && point.canAcceptMoreBanners()) {
            point.incrementBannerCount();
            // In a real implementation, we would save the updated point
        }
    }

    @Override
    public void resetSelection(String pointId) {
        // Implementation for resetting selection if needed
        System.out.println("Reset selection for point: " + pointId);
    }
}