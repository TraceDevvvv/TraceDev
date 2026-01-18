package com.example.repository;

import com.example.model.Banner;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * In-memory implementation of IBannerRepository for demonstration.
 */
public class InMemoryBannerRepository implements IBannerRepository {
    private List<Banner> banners = new ArrayList<>();

    @Override
    public void save(Banner banner) {
        // Generate an ID if not present.
        if (banner.getId() == null) {
            // Simple ID generation for demo.
            banner = new Banner("BANNER-" + System.currentTimeMillis(),
                    banner.getRestPointId(),
                    banner.getImageData(),
                    banner.getImageType(),
                    banner.getFileSize(),
                    banner.getWidth(),
                    banner.getHeight(),
                    banner.getUploadDate());
        }
        banners.add(banner);
    }

    @Override
    public List<Banner> findByRestPointId(String restPointId) {
        return banners.stream()
                .filter(b -> restPointId.equals(b.getRestPointId()))
                .collect(Collectors.toList());
    }

    @Override
    public int countByRestPointId(String restPointId) {
        return (int) banners.stream()
                .filter(b -> restPointId.equals(b.getRestPointId()))
                .count();
    }

    @Override
    public void logAvailability() {
        System.out.println("Banner repository availability logged.");
    }
}