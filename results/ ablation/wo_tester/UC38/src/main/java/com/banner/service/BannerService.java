package com.banner.service;

import com.banner.repository.BannerRepository;
import com.banner.validation.ImageValidator;
import com.banner.dto.InsertBannerRequest;
import com.banner.dto.InsertBannerResponse;

/**
 * Service for banner operations as per class diagram.
 */
public class BannerService {
    private BannerRepository bannerRepository;
    private ImageValidator imageValidator;

    public BannerService(BannerRepository bannerRepository, ImageValidator imageValidator) {
        this.bannerRepository = bannerRepository;
        this.imageValidator = imageValidator;
    }

    public InsertBannerResponse insertBanner(InsertBannerRequest request) {
        // Implementation would delegate to interactor or perform business logic
        // For now, provide a placeholder that matches sequence diagram flow
        // In a real system this would coordinate validation, counting, etc.
        return new InsertBannerResponse(false, null, "Not implemented via BannerService");
    }

    private boolean checkMaxBanners(String pointOfRestId) {
        // Implementation as per class diagram
        int currentCount = bannerRepository.countByPointOfRestId(pointOfRestId);
        return currentCount < 10; // Assume max 10 banners
    }
}