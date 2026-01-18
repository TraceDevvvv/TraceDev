package com.restaurant.banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class to handle banner operations.
 */
public class BannerService {
    private List<Banner> banners;

    public BannerService() {
        banners = new ArrayList<>();
        // Initialize with some dummy data for demonstration
        banners.add(new Banner(1, "Summer Sale", "http://example.com/summer.jpg", java.time.LocalDateTime.now(), 101));
        banners.add(new Banner(2, "New Menu", "http://example.com/menu.jpg", java.time.LocalDateTime.now(), 101));
        banners.add(new Banner(3, "Weekend Special", "http://example.com/weekend.jpg", java.time.LocalDateTime.now(), 102));
    }

    /**
     * Retrieves all banners for a specific restaurant.
     */
    public List<Banner> getBannersByRestaurantId(int restaurantId) {
        List<Banner> result = new ArrayList<>();
        for (Banner banner : banners) {
            if (banner.getRestaurantId() == restaurantId) {
                result.add(banner);
            }
        }
        return result;
    }

    /**
     * Deletes a banner by its ID and restaurant ID.
     * Returns true if deletion was successful, false otherwise.
     */
    public boolean deleteBanner(int bannerId, int restaurantId) {
        Banner toRemove = null;
        for (Banner banner : banners) {
            if (banner.getId() == bannerId && banner.getRestaurantId() == restaurantId) {
                toRemove = banner;
                break;
            }
        }
        if (toRemove != null) {
            banners.remove(toRemove);
            return true;
        }
        return false;
    }
}