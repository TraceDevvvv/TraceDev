
package com.example;

import java.util.Arrays;
import java.util.List;

/**
 * Main class to demonstrate the functionality.
 * Includes sample implementations of repositories and a runnable example.
 */
public class Main {
    public static void main(String[] args) {
        // Create sample data
        RefreshmentPoint point1 = new RefreshmentPoint(1, "Point A", "Location A");
        Banner banner1 = new Banner(101, "Banner 1", "/images/banner1.jpg", 1);
        Banner banner2 = new Banner(102, "Banner 2", "/images/banner2.jpg", 1);
        point1.setBanners(Arrays.asList(banner1, banner2));

        // Create repository implementations (stubs for demonstration)
        RefreshmentPointRepository refreshmentPointRepo = new RefreshmentPointRepository() {
            private List<RefreshmentPoint> points = Arrays.asList(point1);

            @Override
            public List<RefreshmentPoint> findAll() {
                return points;
            }

            @Override
            public RefreshmentPoint findById(int id) {
                return points.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
            }
        };

        BannerRepository bannerRepo = new BannerRepository() {
            private List<Banner> banners = Arrays.asList(banner1, banner2);

            @Override
            public List<Banner> findAllBanners() {
                return banners;
            }

            @Override
            public RefreshmentPoint findRefreshmentPointById(int id) {
                return refreshmentPointRepo.findById(id);
            }

            @Override
            public List<Banner> findByRefreshmentPointId(int refreshmentPointId) {
                return banners.stream()
                        .filter(b -> b.getRefreshmentPointId() == refreshmentPointId)
                        .toList();
            }

            @Override
            public Banner findBannerById(int bannerId) {
                return banners.stream().filter(b -> b.getId() == bannerId).findFirst().orElse(null);
            }

            @Override
            public Banner update(Banner banner) {
                System.out.println("Updating banner with ID: " + banner.getId());
                // In a real implementation, persist the changes.
                return banner;
            }
        };

        ImageValidator validator = new ImageValidator();
        ChangeBannerImageController controller = new ChangeBannerImageController(refreshmentPointRepo, bannerRepo, validator);

        // Demonstrate getting refreshment points
        List<RefreshmentPoint> points = controller.getRefreshmentPoints();
        System.out.println("Refreshment Points: " + points.size());

        // Demonstrate getting banners for a point
        List<Banner> banners = controller.getBannersForPoint(1);
        System.out.println("Banners for point 1: " + banners.size());

        // Demonstrate changing a banner image (with valid image data)
        byte[] validImageData = new byte[1024]; // 1 KB image
        boolean success = controller.changeBannerImage(101, validImageData);
        System.out.println("Change banner image result: " + success);

        // Demonstrate with invalid image (too large)
        byte[] largeImageData = new byte[10 * 1024 * 1024]; // 10 MB image
        boolean success2 = controller.changeBannerImage(102, largeImageData);
        System.out.println("Change banner image with large data result: " + success2);
    }
}
