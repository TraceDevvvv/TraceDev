import java.util.*;
import java.io.*;

/**
 * Main class demonstrating the complete ModifyBanner use case.
 * This program creates sample data, initializes an AgencyOperator,
 * and runs the banner modification flow as described in the use case.
 * 
 * The program demonstrates:
 * 1. Creating sample rest points and banners
 * 2. Agency operator login
 * 3. Banner selection and image modification flow
 * 4. Image validation and error handling
 * 5. Successful banner update notification
 */
public class Main {
    
    /**
     * Main method - entry point of the program.
     * Creates sample data and runs the banner modification demonstration.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== ModifyBanner Use Case Demonstration ===\n");
        
        // Create sample rest points with banners
        List<RestPoint> restPoints = createSampleRestPoints();
        
        // Create and initialize agency operator
        AgencyOperator operator = new AgencyOperator("OP001", "John Doe");
        
        // Simulate login (Entry condition: The agency has logged)
        System.out.println("=== Entry Condition: Agency Login ===");
        boolean loginSuccess = operator.login("agency", "password123");
        
        if (!loginSuccess) {
            System.out.println("Login failed. Exiting program.");
            return;
        }
        
        // Set available rest points (from SearchRefreshmentPoint use case)
        operator.setAvailableRestPoints(restPoints);
        
        System.out.println("\n=== Starting ModifyBanner Flow ===");
        System.out.println("This demonstrates the complete flow of changing a banner image.\n");
        
        // Execute the banner modification flow
        operator.executeModifyBannerFlow();
        
        // Demonstrate error case with invalid image
        System.out.println("\n\n=== Demonstrating Error Case ===");
        demonstrateErrorCase(operator, restPoints);
        
        // Demonstrate server interruption scenario
        System.out.println("\n\n=== Demonstrating Server Interruption ===");
        demonstrateServerInterruption();
        
        // Show final state of banners
        System.out.println("\n=== Final Banner Status ===");
        displayFinalBannerStatus(restPoints);
        
        // Clean up
        operator.close();
        
        System.out.println("\n=== Program Complete ===");
        System.out.println("The ModifyBanner use case has been successfully demonstrated.");
    }
    
    /**
     * Creates sample rest points with banners for demonstration.
     * 
     * @return List of rest points with sample banners
     */
    private static List<RestPoint> createSampleRestPoints() {
        List<RestPoint> restPoints = new ArrayList<>();
        
        // Create first rest point
        RestPoint cafe = new RestPoint("RP001", "Downtown Cafe", "123 Main Street");
        cafe.addBanner(new Banner("B001", "RP001", "/images/banners/cafe_banner1.jpg"));
        cafe.addBanner(new Banner("B002", "RP001", "/images/banners/cafe_banner2.png"));
        cafe.addBanner(new Banner("B003", "RP001", "/images/banners/cafe_promo.gif"));
        restPoints.add(cafe);
        
        // Create second rest point
        RestPoint mall = new RestPoint("RP002", "City Center Mall", "456 Oak Avenue");
        mall.addBanner(new Banner("B004", "RP002", "/images/banners/mall_ad1.jpg"));
        mall.addBanner(new Banner("B005", "RP002", "/images/banners/mall_ad2.png"));
        restPoints.add(mall);
        
        // Create third rest point
        RestPoint park = new RestPoint("RP003", "Central Park", "789 Pine Road");
        park.addBanner(new Banner("B006", "RP003", "/images/banners/park_event.jpg"));
        restPoints.add(park);
        
        System.out.println("Created " + restPoints.size() + " sample rest points:");
        for (RestPoint point : restPoints) {
            System.out.println("  - " + point.getPointName() + 
                             " (" + point.getBannerCount() + " banners)");
        }
        
        return restPoints;
    }
    
    /**
     * Demonstrates the error case when an invalid image is selected.
     * 
     * @param operator The agency operator
     * @param restPoints List of rest points
     */
    private static void demonstrateErrorCase(AgencyOperator operator, List<RestPoint> restPoints) {
        System.out.println("Simulating error case with invalid image...");
        
        // Create a test operator for error demonstration
        AgencyOperator testOperator = new AgencyOperator("OP002", "Test Operator");
        testOperator.login("agency", "password123");
        
        // Create a test rest point with a single banner
        RestPoint testPoint = new RestPoint("RP999", "Test Location", "Test Address");
        Banner testBanner = new Banner("B999", "RP999", "/images/banners/current.jpg");
        testPoint.addBanner(testBanner);
        
        List<RestPoint> testPoints = new ArrayList<>();
        testPoints.add(testPoint);
        testOperator.setAvailableRestPoints(testPoints);
        
        System.out.println("\nWhen an invalid image is selected (e.g., non-existent file,");
        System.out.println("wrong format, or too large), the system will:");
        System.out.println("1. Detect the invalid image");
        System.out.println("2. Enable the Errored use case");
        System.out.println("3. Preserve the current banner image");
        System.out.println("4. Log the error for monitoring");
        
        // Demonstrate ImageValidator separately
        System.out.println("\n--- ImageValidator Demonstration ---");
        ImageValidator validator = new ImageValidator();
        
        // Test with various invalid cases
        String[] invalidImages = {
            "/nonexistent/image.jpg",           // Non-existent file
            "/etc/passwd",                      // Wrong file type
            "test.txt",                         // Wrong extension
            "/dev/zero"                         // Special file
        };
        
        for (String invalidImage : invalidImages) {
            ImageValidator.ValidationResult result = validator.validateImage(invalidImage);
            System.out.println("\nValidating: " + invalidImage);
            System.out.println("Result: " + (result.isValid() ? "VALID" : "INVALID"));
            if (!result.isValid()) {
                System.out.println("Errors: " + result.getErrors());
            }
        }
        
        testOperator.close();
    }
    
    /**
     * Demonstrates handling of server interruption (ETOUR connection loss).
     */
    private static void demonstrateServerInterruption() {
        System.out.println("Simulating server ETOUR connection interruption...");
        
        System.out.println("\nIn a real scenario, the system would:");
        System.out.println("1. Detect connection loss to server ETOUR");
        System.out.println("2. Display appropriate error message to the user");
        System.out.println("3. Log the interruption for system monitoring");
        System.out.println("4. Allow retry or save state for recovery");
        System.out.println("5. Ensure data integrity is maintained");
        
        System.out.println("\nError handling mechanisms in place:");
        System.out.println("- Transaction rollback for incomplete operations");
        System.out.println("- Automatic reconnection attempts");
        System.out.println("- User notification with recovery options");
        System.out.println("- Error logging to banner_errors.log file");
    }
    
    /**
     * Displays the final status of all banners after demonstration.
     * 
     * @param restPoints List of rest points with banners
     */
    private static void displayFinalBannerStatus(List<RestPoint> restPoints) {
        System.out.println("Current banner status across all rest points:");
        
        int totalBanners = 0;
        int validBanners = 0;
        
        for (RestPoint point : restPoints) {
            System.out.println("\n" + point.getPointName() + " (" + point.getPointId() + "):");
            
            List<Banner> banners = point.getBanners();
            totalBanners += banners.size();
            
            for (Banner banner : banners) {
                boolean isValid = banner.validateImage();
                if (isValid) validBanners++;
                
                System.out.println("  - Banner " + banner.getBannerId() + 
                                 ": " + banner.getImagePath() +
                                 " [" + (isValid ? "VALID" : "INVALID") + "]");
            }
        }
        
        System.out.println("\nSummary:");
        System.out.println("Total banners: " + totalBanners);
        System.out.println("Valid banners: " + validBanners);
        System.out.println("Invalid banners: " + (totalBanners - validBanners));
    }
    
    /**
     * Helper method to create a test file for demonstration purposes.
     * In a real application, this would be an actual image file.
     * 
     * @param filePath Path where to create the test file
     * @param sizeInKB Size of the test file in KB
     * @return true if file was created successfully, false otherwise
     */
    private static boolean createTestFile(String filePath, int sizeInKB) {
        try {
            File testFile = new File(filePath);
            testFile.getParentFile().mkdirs();
            
            byte[] data = new byte[1024]; // 1KB chunks
            Arrays.fill(data, (byte) 'X');
            
            try (FileOutputStream fos = new FileOutputStream(testFile)) {
                for (int i = 0; i < sizeInKB; i++) {
                    fos.write(data);
                }
            }
            
            // Rename to have proper extension for testing
            File renamedFile = new File(filePath + ".jpg");
            testFile.renameTo(renamedFile);
            
            return true;
        } catch (IOException e) {
            System.err.println("Failed to create test file: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Demonstrates the complete successful flow with a simulated image update.
     * This method shows what happens when a valid image is selected.
     */
    private static void demonstrateSuccessfulFlow() {
        System.out.println("\n=== Successful Flow Demonstration ===");
        
        // Create a sample banner
        Banner demoBanner = new Banner("DEMO001", "DEMORP", "/images/demo/current_banner.jpg");
        
        System.out.println("Initial banner state:");
        System.out.println("  ID: " + demoBanner.getBannerId());
        System.out.println("  Current image: " + demoBanner.getImagePath());
        System.out.println("  Created: " + demoBanner.getCreatedAt());
        
        // Simulate successful image update
        String newImagePath = "/images/demo/new_banner.jpg";
        System.out.println("\nUpdating to new image: " + newImagePath);
        
        boolean success = demoBanner.updateImage(newImagePath);
        
        if (success) {
            System.out.println("\nUpdate successful!");
            System.out.println("  New image: " + demoBanner.getImagePath());
            System.out.println("  Updated at: " + demoBanner.getUpdatedAt());
            System.out.println("  Image valid: " + demoBanner.validateImage());
            
            // Exit condition: Notify successful modification
            System.out.println("\n=== Exit Condition Met ===");
            System.out.println("The system has successfully modified the banner.");
            System.out.println("Notification sent: Banner " + demoBanner.getBannerId() + 
                             " updated successfully.");
        } else {
            System.out.println("\nUpdate failed. Image may be invalid.");
        }
    }
}