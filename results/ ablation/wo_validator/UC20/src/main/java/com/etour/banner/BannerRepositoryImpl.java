package com.etour.banner;

import java.util.ArrayList;
import java.util.List;

/**
 * BannerRepositoryImpl - simple in‑memory implementation of BannerRepository.
 */
public class BannerRepositoryImpl implements BannerRepository {
    private List<Banner> banners = new ArrayList<>();

    @Override
    public void save(Banner banner) {
        banners.add(banner);
        System.out.println("BannerRepository: saved banner with ID " + banner.getId());
    }

    @Override
    public List<Banner> findByRestPointId(String restPointId) {
        List<Banner> result = new ArrayList<>();
        for (Banner b : banners) {
            if (restPointId.equals(b.getAssociatedRestPointId())) {
                result.add(b);
            }
        }
        return result;
    }

    @Override
    public int countByRestPointId(String restPointId) {
        int count = 0;
        for (Banner b : banners) {
            if (restPointId.equals(b.getAssociatedRestPointId())) {
                count++;
            }
        }
        System.out.println("BannerRepository: count for rest point " + restPointId + " = " + count);
        return count;
    }
}