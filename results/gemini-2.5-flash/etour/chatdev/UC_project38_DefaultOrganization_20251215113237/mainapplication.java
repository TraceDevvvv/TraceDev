/*
 * Main entry point for the Banner Management System application.
 * It initializes the GUI for the Restaurant Operator.
 */
import javax.swing.SwingUtilities;
public class MainApplication {
    public static void main(String[] args) {
        // Ensure that the GUI is created and updated on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Create a sample restaurant for demonstration purposes
            // This restaurant will manage its banners
            Restaurant sampleRestaurant = new Restaurant("My Awesome Restaurant", 3); // Max 3 banners
            // Create the BannerService responsible for business logic
            BannerService bannerService = new BannerService(sampleRestaurant);
            // Create and display the main GUI for the restaurant operator
            new RestaurantOperatorGUI(bannerService).setVisible(true);
        });
    }
}