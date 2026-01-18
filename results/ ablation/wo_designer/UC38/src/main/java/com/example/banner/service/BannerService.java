package com.example.banner.service;

import com.example.banner.model.Banner;
import com.example.banner.exception.BannerException;
import com.example.banner.validation.ImageValidator;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class handling banner business logic.
 */
public class BannerService {
    // Maximum number of banners allowed per restaurant
    private static final int MAX_BANNERS_PER_RESTAURANT = 10;

    // In-memory storage for banners (in a real application, use a database)
    private List<Banner> bannerList = new ArrayList<>();
    private long nextId = 1;

    /**
     * Inserts a new banner after performing all validations.
     * @param pointOfRestaurantId the restaurant ID
     * @param imageFile the image file to be associated
     * @return the created Banner object
     * @throws BannerException if validation fails or max banners reached
     */
    public Banner insertBanner(Long pointOfRestaurantId, File imageFile) throws BannerException {
        // Step 5: Validate image characteristics
        ImageValidator.validateImage(imageFile);

        // Step 6: Check maximum number of banners for the restaurant
        long count = bannerList.stream()
                .filter(b -> b.getPointOfRestaurantId().equals(pointOfRestaurantId))
                .count();
        if (count >= MAX_BANNERS_PER_RESTAURANT) {
            throw new BannerException("Maximum number of banners (" + MAX_BANNERS_PER_RESTAURANT + ") already reached for this restaurant.");
        }

        // Step 7 & 8: In a real app, we would ask for confirmation via UI.
        // Here we just simulate confirmation and proceed.

        // Create banner record
        Banner banner = new Banner();
        banner.setId(nextId++);
        banner.setPointOfRestaurantId(pointOfRestaurantId);
        // In a real application, the image would be stored in a file system or cloud storage,
        // and we would save the path or URL. For simplicity, we use the absolute path.
        banner.setImagePath(imageFile.getAbsolutePath());
        banner.setCreatedAt(LocalDateTime.now());

        // Step 9: Remember the banner (store it)
        bannerList.add(banner);

        return banner;
    }

    /**
     * Gets all banners for a given restaurant.
     * @param pointOfRestaurantId the restaurant ID
     * @return list of banners
     */
    public List<Banner> getBannersByRestaurant(Long pointOfRestaurantId) {
        return bannerList.stream()
                .filter(b -> b.getPointOfRestaurantId().equals(pointOfRestaurantId))
                .collect(Collectors.toList());
    }

    /**
     * Gets the current count of banners for a restaurant.
     * @param pointOfRestaurantId the restaurant ID
     * @return the count
     */
    public long getBannerCount(Long pointOfRestaurantId) {
        return bannerList.stream()
                .filter(b -> b.getPointOfRestaurantId().equals(pointOfRestaurantId))
                .count();
    }

    /**
     * For testing: clears all banners and resets ID counter.
     */
    public void clearAll() {
        bannerList.clear();
        nextId = 1;
    }
}