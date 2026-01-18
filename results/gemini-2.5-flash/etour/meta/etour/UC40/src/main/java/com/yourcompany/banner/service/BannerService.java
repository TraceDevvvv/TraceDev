package com.yourcompany.banner.service;

import com.yourcompany.banner.model.Banner;
import com.yourcompany.banner.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Banner-related business logic.
 * This class orchestrates operations between the controller and the repository,
 * applying business rules and handling transactions.
 */
@Service // Marks this class as a Spring service component.
public class BannerService {

    private final BannerRepository bannerRepository;

    /**
     * Constructor for BannerService, injecting BannerRepository.
     * Spring's dependency injection automatically provides an instance of BannerRepository.
     *
     * @param bannerRepository The repository for Banner entities.
     */
    @Autowired
    public BannerService(BannerRepository bannerRepository) {
        this.bannerRepository = bannerRepository;
    }

    /**
     * Retrieves a list of all banners associated with a specific restaurant.
     * This is used to display banners to the operator for selection.
     *
     * @param restaurantId The ID of the restaurant whose banners are to be retrieved.
     * @return A list of Banner objects.
     */
    public List<Banner> getBannersByRestaurantId(Long restaurantId) {
        // In a real application, additional checks might be needed here,
        // e.g., ensuring the authenticated operator has access to this restaurantId.
        return bannerRepository.findByRestaurantId(restaurantId);
    }

    /**
     * Deletes a banner by its ID.
     * This method includes transactional management to ensure data integrity.
     *
     * @param bannerId The ID of the banner to be deleted.
     * @param restaurantId The ID of the restaurant that owns the banner. This is used for authorization.
     * @return true if the banner was successfully deleted, false if not found or not authorized.
     * @throws SecurityException if the banner does not belong to the specified restaurant.
     */
    @Transactional // Ensures that the entire method runs within a single database transaction.
    public boolean deleteBanner(Long bannerId, Long restaurantId) {
        // First, find the banner to ensure it exists and belongs to the specified restaurant.
        Optional<Banner> bannerOptional = bannerRepository.findById(bannerId);

        if (bannerOptional.isEmpty()) {
            // Banner not found, nothing to delete.
            return false;
        }

        Banner banner = bannerOptional.get();

        // Security check: Ensure the banner belongs to the restaurant of the authenticated operator.
        // This prevents an operator from deleting banners belonging to other restaurants.
        if (!banner.getRestaurantId().equals(restaurantId)) {
            // Log this unauthorized attempt for auditing purposes.
            System.err.println("Unauthorized attempt to delete banner " + bannerId +
                               " by restaurant " + restaurantId + ". Banner belongs to " +
                               banner.getRestaurantId());
            throw new SecurityException("Unauthorized: Banner does not belong to the specified restaurant.");
        }

        // If the banner exists and belongs to the restaurant, proceed with deletion.
        bannerRepository.delete(banner);
        return true;
    }

    /**
     * Retrieves a banner by its ID.
     * Useful for displaying confirmation messages with banner details.
     *
     * @param bannerId The ID of the banner to retrieve.
     * @return An Optional containing the Banner if found, or empty if not.
     */
    public Optional<Banner> getBannerById(Long bannerId) {
        return bannerRepository.findById(bannerId);
    }
}