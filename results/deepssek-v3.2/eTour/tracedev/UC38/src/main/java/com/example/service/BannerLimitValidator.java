package com.example.service;

import com.example.repository.PointRepository;
import com.example.repository.BannerRepository;

/**
 * Validates banner limits for points of restaurant.
 */
public class BannerLimitValidator {
    private int maxBanners = 10;
    private BannerRepository bannerRepository;
    private PointRepository pointRepository;

    public BannerLimitValidator(BannerRepository bannerRepository, PointRepository pointRepository) {
        this.bannerRepository = bannerRepository;
        this.pointRepository = pointRepository;
    }

    public boolean validate(String pointId, int currentCount) {
        return !isLimitExceeded(currentCount);
    }

    public boolean isLimitExceeded(int currentCount) {
        return currentCount >= maxBanners;
    }

    public int getMaxBannersForPoint(String pointId) {
        var point = pointRepository.findByPointId(pointId);
        return point != null ? point.getMaxBanners() : maxBanners;
    }
}