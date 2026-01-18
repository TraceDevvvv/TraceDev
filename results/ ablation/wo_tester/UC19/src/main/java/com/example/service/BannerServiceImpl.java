package com.example.service;

import com.example.model.Banner;
import com.example.repository.BannerRepository;
import java.util.List;

/**
 * Implementation of BannerService using BannerRepository.
 */
public class BannerServiceImpl implements BannerService {
    private BannerRepository bannerRepository;

    public BannerServiceImpl(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public List<Banner> getBannersByRefreshmentPoint(String pointId) {
        // Delegates to repository to find banners by refreshment point ID
        return bannerRepository.findByRefreshmentPointId(pointId);
    }

    @Override
    public boolean deleteBanner(String bannerId) {
        // Delegates to repository to delete banner by ID
        return bannerRepository.deleteById(bannerId);
    }

    @Override
    public Banner getBannerDetails(String bannerId) {
        // Delegates to repository to find banner by ID, returns null if not found
        return bannerRepository.findById(bannerId).orElse(null);
    }
}