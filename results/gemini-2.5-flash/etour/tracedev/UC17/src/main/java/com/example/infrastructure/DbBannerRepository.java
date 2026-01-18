package com.example.infrastructure;

import com.example.domain.Banner;
import com.example.domain.TurningPoint;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of IBannerRepository, simulating a database.
 * Part of the Infrastructure Layer.
 */
public class DbBannerRepository implements IBannerRepository {

    // In-memory "database" for demonstration purposes
    // Note: In a real app, this would interact with a persistent store.
    // For this simulation, we'll maintain a map of banners.
    // Assuming banners are uniquely identifiable across all turning points for simplicity here.
    private final Map<String, Banner> banners = new HashMap<>();

    // We also need access to turning points to ensure consistency if a banner belongs to one.
    // For this mock, we'll assume DbTurningPointRepository has already created these.
    // A more robust solution would involve a shared mock data source or proper DB setup.
    private final DbTurningPointRepository turningPointRepository;

    public DbBannerRepository(DbTurningPointRepository turningPointRepository) {
        this.turningPointRepository = turningPointRepository;
        initializeMockBannersFromTurningPoints();
    }

    // Helper to populate banners from the TurningPointRepository's mock data
    private void initializeMockBannersFromTurningPoints() {
        for (TurningPoint tp : turningPointRepository.findAll()) {
            for (Banner banner : tp.getBanners()) {
                this.banners.put(banner.getId(), banner);
            }
        }
        System.out.println("DbBannerRepository: Initialized with " + this.banners.size() + " banners from TurningPointRepository.");
    }

    /**
     * Finds a Banner by its unique identifier.
     * @param id The ID of the banner.
     * @return The Banner if found, null otherwise.
     */
    @Override
    public Banner findById(String id) {
        System.out.println("BR: Searching for Banner with ID: " + id);
        return banners.get(id);
    }

    /**
     * Saves or updates a Banner entity.
     * For this mock, it just updates the in-memory map.
     * @param banner The Banner object to save.
     */
    @Override
    public void save(Banner banner) {
        System.out.println("BR: Saving banner with ID: " + banner.getId() + ". Last Modified: " + banner.getLastModifiedDate());
        // In a real application, this would persist the banner to a database.
        // For mock, simply replace/add in the map.
        banners.put(banner.getId(), banner);

        // Also ensure consistency in the TurningPoint's banner list if this was an update
        // (though in this specific sequence, the banner object is updated directly,
        // and its reference within TurningPoint should also reflect the changes if it was mutable).
        // If TurningPoint held immutable copies, this would need more complex logic.
        // Given that `TurningPoint.getBanners()` returns an unmodifiable list but the Banner objects
        // themselves are mutable, modifying a Banner object obtained from a TurningPoint
        // will reflect in the TurningPoint's internal list as well.
        System.out.println("BR: Banner " + banner.getId() + " saved/updated in repository.");
    }
}