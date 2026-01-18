package com.example.uml;

import java.util.List;

/**
 * BannerService implements IBannerService and delegates data access to IBannerRepository.
 */
public class BannerService implements IBannerService {
    private IBannerRepository bannerRepository;

    public BannerService(IBannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    @Override
    public List<Banner> getBannersByRefreshmentPoint(String refreshmentPointId) {
        return bannerRepository.findByRefreshmentPointId(refreshmentPointId);
    }

    @Override
    public boolean deleteBanner(String bannerId) {
        try {
            return bannerRepository.delete(bannerId);
        } catch (RuntimeException e) {
            // Handle connection interruption as per sequence diagram.
            System.err.println("Deletion failed due to: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean confirmBannerExists(String bannerId) {
        Banner banner = bannerRepository.findById(bannerId);
        return banner != null;
    }
}