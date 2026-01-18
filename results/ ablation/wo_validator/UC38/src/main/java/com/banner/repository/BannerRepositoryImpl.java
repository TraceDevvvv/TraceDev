package com.banner.repository;

import com.banner.model.Banner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of BannerRepository.
 */
public class BannerRepositoryImpl implements BannerRepository {
    private Map<String, Banner> bannerCollection = new HashMap<>();

    @Override
    public void save(Banner banner) {
        bannerCollection.put(banner.getId(), banner);
    }

    @Override
    public List<Banner> findByPointOfRestaurantId(String pointOfRestaurantId) {
        List<Banner> result = new ArrayList<>();
        for (Banner banner : bannerCollection.values()) {
            if (banner.getPointOfRestaurantId().equals(pointOfRestaurantId)) {
                result.add(banner);
            }
        }
        return result;
    }

    @Override
    public int countByPointOfRestaurantId(String pointOfRestaurantId) {
        int count = 0;
        for (Banner banner : bannerCollection.values()) {
            if (banner.getPointOfRestaurantId().equals(pointOfRestaurantId)) {
                count++;
            }
        }
        return count;
    }
}