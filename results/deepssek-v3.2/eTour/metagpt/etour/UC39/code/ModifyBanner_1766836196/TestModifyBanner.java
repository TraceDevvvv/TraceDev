package com.example.banner;

/**
 * TestModifyBanner.java
 * Simple test class to verify the functionality of the ModifyBanner program.
 * This class includes a main method that runs a series of tests on Banner,
 * BannerManager, and ImageValidator classes.
 */
public class TestModifyBanner {
    /**
     * Main method to run tests.
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("=== Starting ModifyBanner Tests ===\n");
        
        // Test Banner class
        testBanner();
        
        // Test BannerManager class
        testBannerManager();
        
        // Test ImageValidator class
        testImageValidator();
        
        System.out.println("\n=== All tests completed ===");
    }
    
    /**
     * Tests the Banner class: instantiation, getters, setters, and toString.
     */
    private static void testBanner() {
        System.out.println("--- Testing Banner Class ---");
        Banner banner = new Banner("test1", "/images/test.jpg");
        System.out.println("Created banner: " + banner);
        
        // Test getters
        if (!"test1".equals(banner.getId())) {
            System.out.println("ERROR: Banner ID mismatch.");
        } else {
            System.out.println("Banner ID getter: OK");
        }
        if (!"/images/test.jpg".equals(banner.getImagePath())) {
            System.out.println("ERROR: Banner image path mismatch.");
        } else {
            System.out.println("Banner image path getter: OK");
        }
        
        // Test setters
        banner.setId("test2");
        banner.setImagePath("/images/new.png");
        if (!"test2".equals(banner.getId()) || !"/images/new.png".equals(banner.getImagePath())) {
            System.out.println("ERROR: Banner setters failed.");
        } else {
            System.out.println("Banner setters: OK");
        }
        System.out.println("Updated banner: " + banner);
        System.out.println();
    }
    
    /**
     * Tests BannerManager: singleton, list banners, update banner image.
     */
    private static void testBannerManager() {
        System.out.println("--- Testing BannerManager Class ---");
        BannerManager manager = BannerManager.getInstance();
        
        // Display initial banners
        System.out.println("Initial banners:");
        manager.displayBanners();
        
        // Test getBannerById
        Banner banner = manager.getBannerById("1");
        if (banner == null) {
            System.out.println("ERROR: Could not find banner with id 1.");
        } else {
            System.out.println("Found banner: " + banner);
        }
        
        // Test updateBannerImage with valid image
        try {
            boolean success = manager.updateBannerImage("1", "/images/updated.jpg");
            if (success) {
                System.out.println("Banner update successful.");
                Banner updated = manager.getBannerById("1");
                System.out.println("Updated banner image path: " + updated.getImagePath());
            } else {
                System.out.println("ERROR: Banner update failed.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: Unexpected exception: " + e.getMessage());
        }
        
        // Test update with invalid banner id
        boolean success = manager.updateBannerImage("99", "/images/ghost.jpg");
        if (!success) {
            System.out.println("Expected failure for non-existent banner: OK");
        } else {
            System.out.println("ERROR: Should have failed for non-existent banner.");
        }
        
        System.out.println();
    }
    
    /**
     * Tests ImageValidator with various inputs.
     */
    private static void testImageValidator() {
        System.out.println("--- Testing ImageValidator Class ---");
        ImageValidator validator = new ImageValidator();
        
        // Test valid image
        try {
            validator.validate("/images/photo.jpg", 1024 * 1024, 800, 600);
            System.out.println("Valid image validation: OK");
        } catch (ImageValidator.ImageValidationException e) {
            System.out.println("ERROR: Valid image should pass: " + e.getMessage());
        }
        
        // Test invalid extension
        try {
            validator.validate("/images/document.pdf", 1024 * 1024, 800, 600);
            System.out.println("ERROR: Invalid extension should fail.");
        } catch (ImageValidator.ImageValidationException e) {
            System.out.println("Invalid extension caught: " + e.getMessage());
        }
        
        // Test oversized file
        try {
            validator.validate("/images/large.jpg", 10 * 1024 * 1024, 800, 600);
            System.out.println("ERROR: Oversized file should fail.");
        } catch (ImageValidator.ImageValidationException e) {
            System.out.println("Oversized file caught: " + e.getMessage());
        }
        
        // Test undersized dimensions
        try {
            validator.validate("/images/small.jpg", 1024, 50, 50);
            System.out.println("ERROR: Undersized dimensions should fail.");
        } catch (ImageValidator.ImageValidationException e) {
            System.out.println("Undersized dimensions caught: " + e.getMessage());
        }
        
        // Test oversized dimensions
        try {
            validator.validate("/images/huge.jpg", 1024, 3000, 2000);
            System.out.println("ERROR: Oversized dimensions should fail.");
        } catch (ImageValidator.ImageValidationException e) {
            System.out.println("Oversized dimensions caught: " + e.getMessage());
        }
        
        System.out.println();
    }
}
