package com.example.bannermanagement;

import java.util.List;

/**
 * Service class responsible for business logic related to banner management.
 * It orchestrates interactions with repository interfaces for data access.
 */
public class BannerManagementService {
    private final IRefreshmentPointRepository refreshmentPointRepository;
    private final IBannerRepository bannerRepository;

    /**
     * Constructs a BannerManagementService with injected repository dependencies.
     *
     * @param rpRepo The repository for RefreshmentPoint entities.
     * @param bRepo The repository for Banner entities.
     */
    public BannerManagementService(IRefreshmentPointRepository rpRepo, IBannerRepository bRepo) {
        this.refreshmentPointRepository = rpRepo;
        this.bannerRepository = bRepo;
    }

    /**
     * Retrieves a list of all available refreshment points.
     * @return A list of RefreshmentPoint objects.
     * @throws ETOURConnectionException if the underlying repository encounters a connection issue.
     */
    public List<RefreshmentPoint> getAvailableRefreshmentPoints() throws ETOURConnectionException {
        System.out.println("Service: Fetching all refreshment points.");
        return refreshmentPointRepository.findAll();
    }

    /**
     * Retrieves a list of banners for a specific refreshment point.
     * @param rpId The ID of the refreshment point.
     * @return A list of Banner objects associated with the given refreshment point.
     * @throws ETOURConnectionException if the underlying repository encounters a connection issue.
     */
    public List<Banner> getBannersForRefreshmentPoint(String rpId) throws ETOURConnectionException {
        System.out.println("Service: Fetching banners for refreshment point ID: " + rpId);
        return bannerRepository.findByRefreshmentPointId(rpId);
    }

    /**
     * Deletes a banner by its unique identifier.
     * @param bannerId The ID of the banner to delete.
     * @throws ETOURConnectionException if the underlying repository encounters a connection issue.
     */
    public void deleteBanner(String bannerId) throws ETOURConnectionException {
        System.out.println("Service: Attempting to delete banner with ID: " + bannerId);
        bannerRepository.delete(bannerId);
        System.out.println("Service: Banner " + bannerId + " deletion request sent.");
    }
}