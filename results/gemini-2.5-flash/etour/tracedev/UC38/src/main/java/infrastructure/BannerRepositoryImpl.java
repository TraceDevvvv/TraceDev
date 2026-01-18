package infrastructure;

import domain.Banner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * In-memory implementation of IBannerRepository for demonstration purposes.
 * Stores banners in a HashMap. Can simulate network unavailability.
 */
public class BannerRepositoryImpl implements IBannerRepository {
    private final Map<String, Banner> bannerStore = new ConcurrentHashMap<>();
    private boolean simulateNetworkError = false;

    /**
     * Sets a flag to simulate network unavailability for testing purposes.
     * @param simulateNetworkError If true, repository operations may throw NetworkUnavailableException.
     */
    public void setSimulateNetworkError(boolean simulateNetworkError) {
        this.simulateNetworkError = simulateNetworkError;
    }

    private void checkNetworkStatus() throws NetworkUnavailableException {
        if (simulateNetworkError) {
            throw new NetworkUnavailableException("Simulated network unavailable for BannerRepository.");
        }
    }

    @Override
    public Banner save(Banner banner) throws NetworkUnavailableException {
        checkNetworkStatus();
        bannerStore.put(banner.getId(), banner);
        System.out.println("[BannerRepository] Saved: " + banner.getId());
        return banner;
    }

    @Override
    public List<Banner> findByPointOfRestaurantId(String pointOfRestaurantId) throws NetworkUnavailableException {
        checkNetworkStatus();
        return bannerStore.values().stream()
                .filter(b -> b.getPointOfRestaurantId().equals(pointOfRestaurantId))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String bannerId) throws NetworkUnavailableException {
        checkNetworkStatus();
        bannerStore.remove(bannerId);
        System.out.println("[BannerRepository] Deleted: " + bannerId);
    }

    @Override
    public Banner findById(String bannerId) throws NetworkUnavailableException {
        checkNetworkStatus();
        return bannerStore.get(bannerId);
    }
}