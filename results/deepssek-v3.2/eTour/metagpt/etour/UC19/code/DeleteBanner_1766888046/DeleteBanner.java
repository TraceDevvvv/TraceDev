import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Represents a banner advertisement
 */
class Banner {
    private int id;
    private String name;
    
    public Banner(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "Banner ID: " + id + ", Name: " + name;
    }
}

/**
 * Represents a refreshment point that contains banners
 */
class RefreshmentPoint {
    private int id;
    private String name;
    private List<Banner> banners;
    
    public RefreshmentPoint(int id, String name) {
        this.id = id;
        this.name = name;
        this.banners = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Banner> getBanners() {
        return new ArrayList<>(banners); // Return copy to prevent external modification
    }
    
    public void addBanner(Banner banner) {
        banners.add(banner);
    }
    
    public boolean removeBanner(int bannerId) {
        return banners.removeIf(banner -> banner.getId() == bannerId);
    }
    
    @Override
    public String toString() {
        return "Refreshment Point ID: " + id + ", Name: " + name;
    }
}

/**
 * Represents the agency operator who performs the deletion operation
 */
class AgencyOperator {
    private boolean loggedIn;
    
    public AgencyOperator() {
        this.loggedIn = false;
    }
    
    /**
     * Simulates agency login
     */
    public void login() {
        this.loggedIn = true;
        System.out.println("Agency operator logged in successfully.");
    }
    
    public boolean isLoggedIn() {
        return loggedIn;
    }
}

/**
 * Main class implementing the DeleteBanner use case
 */
public class DeleteBanner {
    private AgencyOperator operator;
    private List<RefreshmentPoint> refreshmentPoints;
    private Scanner scanner;
    
    public DeleteBanner() {
        this.operator = new AgencyOperator();
        this.refreshmentPoints = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initializeSampleData();
    }
    
    /**
     * Initialize sample data for testing
     */
    private void initializeSampleData() {
        // Create sample refreshment points
        RefreshmentPoint point1 = new RefreshmentPoint(1, "Downtown Cafe");
        RefreshmentPoint point2 = new RefreshmentPoint(2, "Airport Lounge");
        
        // Add banners to refreshment points
        point1.addBanner(new Banner(101, "Coffee Special"));
        point1.addBanner(new Banner(102, "Lunch Deal"));
        point2.addBanner(new Banner(201, "Travel Discount"));
        point2.addBanner(new Banner(202, "WiFi Promotion"));
        point2.addBanner(new Banner(203, "VIP Offer"));
        
        refreshmentPoints.add(point1);
        refreshmentPoints.add(point2);
    }
    
    /**
     * Main method to execute the DeleteBanner use case
     */
    public void executeDeleteBannerUseCase() {
        try {
            // Entry condition: Agency must be logged in
            if (!operator.isLoggedIn()) {
                System.out.println("Error: Agency operator must be logged in first.");
                return;
            }
            
            // Step 1: Receive list of refreshment points and select one
            System.out.println("\n=== Step 1: Select Refreshment Point ===");
            if (refreshmentPoints.isEmpty()) {
                System.out.println("No refreshment points available.");
                return;
            }
            
            System.out.println("Available Refreshment Points:");
            for (int i = 0; i < refreshmentPoints.size(); i++) {
                System.out.println((i + 1) + ". " + refreshmentPoints.get(i));
            }
            
            System.out.print("Select a refreshment point (1-" + refreshmentPoints.size() + "): ");
            int pointIndex = getValidatedInput(1, refreshmentPoints.size()) - 1;
            RefreshmentPoint selectedPoint = refreshmentPoints.get(pointIndex);
            
            // Step 2: View list of banners associated with the selected point
            System.out.println("\n=== Step 2: View Banners for " + selectedPoint.getName() + " ===");
            List<Banner> banners = selectedPoint.getBanners();
            
            if (banners.isEmpty()) {
                System.out.println("No banners associated with this refreshment point.");
                return;
            }
            
            System.out.println("Available Banners:");
            for (int i = 0; i < banners.size(); i++) {
                System.out.println((i + 1) + ". " + banners.get(i));
            }
            
            // Step 3: Select a banner from the list
            System.out.println("\n=== Step 3: Select Banner to Delete ===");
            System.out.print("Select a banner to delete (1-" + banners.size() + "): ");
            int bannerIndex = getValidatedInput(1, banners.size()) - 1;
            Banner selectedBanner = banners.get(bannerIndex);
            
            // Step 4: Display confirmation message
            System.out.println("\n=== Step 4: Confirm Deletion ===");
            System.out.println("You are about to delete: " + selectedBanner);
            System.out.print("Confirm deletion? (yes/no): ");
            
            // Step 5: Confirm the operation
            String confirmation = scanner.nextLine().trim().toLowerCase();
            if (!confirmation.equals("yes")) {
                System.out.println("Deletion cancelled.");
                return;
            }
            
            // Step 6: Remove the banner
            System.out.println("\n=== Step 6: Removing Banner ===");
            boolean removed = selectedPoint.removeBanner(selectedBanner.getId());
            
            if (removed) {
                // Exit condition: Notify successful elimination
                System.out.println("SUCCESS: Banner '" + selectedBanner.getName() + "' has been successfully deleted.");
            } else {
                System.out.println("ERROR: Failed to delete the banner. Banner not found.");
            }
            
        } catch (NoSuchElementException e) {
            System.out.println("ERROR: Connection to server ETOUR interrupted.");
        } catch (Exception e) {
            System.out.println("ERROR: An unexpected error occurred: " + e.getMessage());
        }
    }
    
    /**
     * Validates user input to ensure it's within the specified range
     */
    private int getValidatedInput(int min, int max) {
        while (true) {
            try {
                String input = scanner.nextLine();
                int value = Integer.parseInt(input.trim());
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.print("Invalid input. Please enter a number between " + min + " and " + max + ": ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }
    
    /**
     * Main method to run the program
     */
    public static void main(String[] args) {
        DeleteBanner deleteBanner = new DeleteBanner();
        
        // Simulate agency login (entry condition)
        deleteBanner.operator.login();
        
        // Execute the delete banner use case
        deleteBanner.executeDeleteBannerUseCase();
    }
}