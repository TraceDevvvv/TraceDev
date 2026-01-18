'''
Manages operations related to banner ads.
This class simulates fetching and updating banner data, including
simulating potential connection issues with an external "ETOUR server".
'''
package managers;
import models.Banner;
import exceptions.EtourConnectionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 * Manages operations related to banner ads.
 * This class simulates fetching and updating banner data, including
 * simulating potential connection issues with an external "ETOUR server".
 */
public class BannerManager {
    // Maps PointOfInterest ID to a list of its banners
    private Map<Integer, List<Banner>> bannersByPoi;
    // Maps banner ID to the Banner object for quick lookup
    private Map<Integer, Banner> allBanners;
    private Random random; // Used to simulate random network errors
    /**
     * Constructs a new BannerManager and populates it with sample banner data.
     */
    public BannerManager() {
        bannersByPoi = new HashMap<>();
        allBanners = new HashMap<>();
        random = new Random();
        // Simulate loading initial banner data
        loadSampleBanners();
    }
    /**
     * Loads predefined sample banner data into the manager's memory.
     * This method adds banners associated with different Points of Interest.
     * Note: For the application to correctly display initial image paths,
     * please ensure that the 'images' directory exists in the project root
     * and contains dummy image files corresponding to the paths below (e.g., banner1_italian.jpg).
     */
    private void loadSampleBanners() {
        // Banners for PoI 101 (Italian Restaurant Venice)
        addBanner(new Banner(201, "Summer Sale Banner", 101, "images/banner1_italian.jpg"));
        addBanner(new Banner(202, "New Menu Banner", 101, "images/banner2_italian.png"));
        // Banners for PoI 102 (Coffee Shop Downtown Hub)
        addBanner(new Banner(203, "Happy Hour Banner", 102, "images/banner3_coffee.gif"));
        // Banners for PoI 103 (Museum of Modern Art City Center)
        addBanner(new Banner(204, "Exhibition Banner", 103, "images/banner4_museum.jpeg"));
        addBanner(new Banner(205, "Membership Drive", 103, "images/banner5_museum_promo.png"));
        // Banners for PoI 104 (Fashion Boutique Central)
        addBanner(new Banner(206, "Winter Collection", 104, "images/banner6_fashion.jpg"));
    }
    /**
     * Helper method to add a banner to both internal data structures.
     * @param banner The Banner object to add.
     */
    private void addBanner(Banner banner) {
        bannersByPoi.computeIfAbsent(banner.getPoiId(), k -> new ArrayList<>()).add(banner);
        allBanners.put(banner.getId(), banner);
    }
    /**
     * Retrieves a list of banners associated with a specific Point of Interest.
     * Simulates potential network connectivity issues.
     *
     * @param poiId The ID of the Point of Interest.
     * @return A list of Banner objects for the given PoI.
     * @throws EtourConnectionException if a simulated connection error occurs.
     */
    public List<Banner> getBannersForPoi(int poiId) throws EtourConnectionException {
        // Simulate network latency and potential ETOUR server connection errors (10% chance)
        if (random.nextInt(10) == 0) {
            throw new EtourConnectionException("Failed to retrieve banners due to ETOUR server connection issues.");
        }
        // Return a copy of the list to prevent external modification of internal data.
        return new ArrayList<>(bannersByPoi.getOrDefault(poiId, new ArrayList<>()));
    }
    /**
     * Updates the image path for a specific banner. Simulates potential connection issues.
     * This corresponds to use case steps 7 and 8 (confirmation and bookmarking).
     *
     * @param bannerId     The ID of the banner to update.
     * @param newImagePath The new file path to the image.
     * @return true if the banner was found and updated successfully, false otherwise.
     * @throws EtourConnectionException if a simulated connection error occurs during the update.
     */
    public boolean updateBannerImage(int bannerId, String newImagePath) throws EtourConnectionException {
        // Simulate network latency and potential ETOUR server connection errors (10% chance)
        if (random.nextInt(10) == 0) {
            throw new EtourConnectionException("Failed to update banner image due to ETOUR server connection issues.");
        }
        Banner banner = allBanners.get(bannerId);
        if (banner != null) {
            banner.setImagePath(newImagePath);
            // In a real application, this would persist the change to a database.
            System.out.println("DEBUG: Banner '" + banner.getName() + "' (ID: " + bannerId + ") updated with image: " + newImagePath);
            return true;
        }
        return false;
    }
}