package com.example.controller;

import com.example.model.Banner;
import com.example.model.RefreshmentPoint;
import com.example.repository.BannerRepository;
import com.example.service.DeleteBannerService;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller that handles banner deletion flow.
 * Coordinates between UI, service, and repository.
 */
public class DeleteBannerController {
    private BannerRepository bannerRepository;
    private DeleteBannerService deleteBannerService;

    public DeleteBannerController(BannerRepository bannerRepository, DeleteBannerService deleteBannerService) {
        this.bannerRepository = bannerRepository;
        this.deleteBannerService = deleteBannerService;
    }

    /**
     * Searches refreshment points based on criteria (simplified).
     * @param criteria search string
     * @return list of refreshment points
     */
    public List<RefreshmentPoint> searchRefreshmentPoints(String criteria) {
        // Simulate search: return dummy points
        List<RefreshmentPoint> points = new ArrayList<>();
        points.add(new RefreshmentPoint(101L, "Point Alpha", "Location A"));
        points.add(new RefreshmentPoint(102L, "Point Beta", "Location B"));
        return points;
    }

    /**
     * Retrieves banners for a given refreshment point.
     * @param pointId the refreshment point ID
     * @return list of banners
     */
    public List<Banner> getBannersForPoint(long pointId) {
        return bannerRepository.findAllByRefreshmentPointId(pointId);
    }

    /**
     * Deletes a banner by its ID.
     * Delegates to DeleteBannerService.
     * @param bannerId the banner ID
     * @return true if deletion successful, false otherwise
     */
    public boolean deleteBanner(long bannerId) {
        return deleteBannerService.deleteBanner(bannerId);
    }

    /**
     * Confirms deletion (re‑deletes the same banner for demonstration).
     * In a real scenario this might update UI or log the action.
     */
    public void confirmDeletion() {
        // This method is called after user confirmation.
        // For simplicity, we just print a message.
        System.out.println("Deletion confirmed by user.");
    }
}