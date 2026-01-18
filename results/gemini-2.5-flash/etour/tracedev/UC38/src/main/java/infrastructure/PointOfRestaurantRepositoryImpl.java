package infrastructure;

import domain.PointOfRestaurant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of IPointOfRestaurantRepository for demonstration purposes.
 * Stores PointOfRestaurant entities in a HashMap. Can simulate network unavailability.
 */
public class PointOfRestaurantRepositoryImpl implements IPointOfRestaurantRepository {
    private final Map<String, PointOfRestaurant> porStore = new ConcurrentHashMap<>();
    private boolean simulateNetworkError = false;

    /**
     * Constructor. Initializes with some sample data.
     */
    public PointOfRestaurantRepositoryImpl() {
        // Pre-populate with a sample PointOfRestaurant
        porStore.put("por123", new PointOfRestaurant("por123", "Taste of Italy", 3));
        porStore.put("por456", new PointOfRestaurant("por456", "Burger Hub", 1)); // Max 1 banner for testing 'cannot add banner'
    }

    /**
     * Sets a flag to simulate network unavailability for testing purposes.
     * @param simulateNetworkError If true, repository operations may throw NetworkUnavailableException.
     */
    public void setSimulateNetworkError(boolean simulateNetworkError) {
        this.simulateNetworkError = simulateNetworkError;
    }

    private void checkNetworkStatus() throws NetworkUnavailableException {
        if (simulateNetworkError) {
            throw new NetworkUnavailableException("Simulated network unavailable for PointOfRestaurantRepository.");
        }
    }

    @Override
    public PointOfRestaurant findById(String id) throws NetworkUnavailableException {
        checkNetworkStatus();
        PointOfRestaurant foundPor = porStore.get(id);
        System.out.println("[PoRRepository] Found PoR: " + (foundPor != null ? foundPor.getId() : "null"));
        return foundPor;
    }

    @Override
    public void update(PointOfRestaurant pointOfRestaurant) throws NetworkUnavailableException {
        checkNetworkStatus();
        if (porStore.containsKey(pointOfRestaurant.getId())) {
            porStore.put(pointOfRestaurant.getId(), pointOfRestaurant);
            System.out.println("[PoRRepository] Updated PoR: " + pointOfRestaurant.getId() + " Current Banners: " + pointOfRestaurant.getCurrentBannerCount());
        } else {
            System.err.println("[PoRRepository] Error: Attempted to update non-existent PoR: " + pointOfRestaurant.getId());
        }
    }
}