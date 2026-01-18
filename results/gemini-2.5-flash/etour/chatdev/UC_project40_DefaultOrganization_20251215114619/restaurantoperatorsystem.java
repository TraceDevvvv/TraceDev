'''
Simulates the backend system for a Point of Restaurant, managing banners.
Handles authentication and banner removal operations.
Includes simulated server connectivity issues.
'''
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
class RestaurantOperatorSystem {
    private List<Banner> banners;
    private boolean isAuthenticated;
    private Random random; // For simulating server connection issues
    /**
     * Constructs a new RestaurantOperatorSystem and initializes with some dummy banners.
     */
    public RestaurantOperatorSystem() {
        this.banners = new ArrayList<>();
        // Add some initial banners for demonstration
        banners.add(new Banner("B001", "Summer Special Ad", "Enjoy 20% off on all summer dishes!"));
        banners.add(new Banner("B002", "New Dessert Menu", "Try our exquisite new dessert range!"));
        banners.add(new Banner("B003", "Happy Hour Promo", "Happy hour on drinks from 5 PM to 7 PM."));
        banners.add(new Banner("B004", "Weekend Brunch", "Delicious brunch every Saturday and Sunday."));
        this.isAuthenticated = false; // Initially not authenticated
        this.random = new Random();
    }
    /**
     * Simulates the authentication process for a Point Of Restaurant Operator.
     *
     * @param username The username provided by the operator.
     * @param password The password provided by the operator.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticate(String username, String password) {
        // Simplified authentication: any non-empty username/password will work for this demo
        // In a real system, this would involve database lookups and secure credential handling.
        this.isAuthenticated = "operator".equals(username) && "pass".equals(password);
        return this.isAuthenticated;
    }
    /**
     * Checks if the operator is currently authenticated.
     *
     * @return true if authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        return isAuthenticated;
    }
    /**
     * Retrieves the list of banners currently associated with the point of restaurant.
     *
     * @return A list of Banner objects.
     */
    public List<Banner> getBanners() {
        return new ArrayList<>(banners); // Return a copy to prevent external modification
    }
    /**
     * Removes a specified banner from the system.
     * Simulates server connection interruption as an edge case.
     *
     * @param banner The Banner object to be removed.
     * @return 0 for successful removal, 1 for server connection interruption, -1 if banner not found.
     */
    public int removeBanner(Banner banner) {
        // Simulate server connection interruption (e.g., 10% chance of failure)
        if (!isServerConnected()) {
            return 1; // Interruption of the connection to the server ETOUR
        }
        if (banners.remove(banner)) {
            return 0; // Successful elimination
        } else {
            return -1; // Banner not found (shouldn't happen if selected from the list)
        }
    }
    /**
     * Simulates the server connection status.
     * Approximately 10% chance to return false, simulating an interruption.
     *
     * @return true if the server is considered connected, false otherwise.
     */
    private boolean isServerConnected() {
        return random.nextInt(10) != 0; // 90% chance of success
    }
}