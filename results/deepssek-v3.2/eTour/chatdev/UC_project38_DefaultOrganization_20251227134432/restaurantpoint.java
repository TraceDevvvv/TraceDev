/**
 * Model class representing a restaurant point with banners
 */
import java.util.ArrayList;
import java.util.List;
public class RestaurantPoint {
    private int id;
    private String name;
    private List<Banner> banners;
    private final int MAX_BANNERS = 5; // Maximum banners allowed per restaurant point
    private boolean authenticated;
    public RestaurantPoint(int id, String name) {
        this.id = id;
        this.name = name;
        this.banners = new ArrayList<>();
        this.authenticated = false;
    }
    // Authenticate the operator
    public boolean authenticate(String username, String password) {
        // Simulate authentication - in real app, this would check against a database
        this.authenticated = "admin".equals(username) && "password".equals(password);
        return this.authenticated;
    }
    // Check if operator is authenticated
    public boolean isAuthenticated() {
        return authenticated;
    }
    // Add a banner if maximum not exceeded
    public boolean addBanner(Banner banner) {
        if (banners.size() >= MAX_BANNERS) {
            return false;
        }
        banners.add(banner);
        return true;
    }
    // Get current number of banners
    public int getBannerCount() {
        return banners.size();
    }
    // Get maximum allowed banners
    public int getMaxBanners() {
        return MAX_BANNERS;
    }
    // Getters
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Banner> getBanners() {
        return new ArrayList<>(banners); // Return copy to prevent external modification
    }
}