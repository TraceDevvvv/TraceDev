'''
Main class to run the ModificaBanner application.
This is the entry point for the GUI program.
'''
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Ensure GUI updates are performed on the Event Dispatch Thread (EDT) for thread safety.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Initialize the BannerService with some dummy data to simulate existing banners.
                BannerService bannerService = new BannerService();
                // Add some sample banners. In a real application, these would come from a database.
                bannerService.addBanner(new Banner("B001", "Seasonal Special", "images/default_banner_1.png"));
                bannerService.addBanner(new Banner("B002", "Weekend Offer", "images/default_banner_2.jpg"));
                bannerService.addBanner(new Banner("B003", "New Menu Item", "images/default_banner_3.gif"));
                // Create and display the main GUI window.
                new ModificaBannerGUI(bannerService).setVisible(true);
            }
        });
    }
}