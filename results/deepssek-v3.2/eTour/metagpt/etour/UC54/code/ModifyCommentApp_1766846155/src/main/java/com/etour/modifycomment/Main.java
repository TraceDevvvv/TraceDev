package com.etour.modifycomment;

/**
 * Main class for the ModifyComment application.
 * This class demonstrates the complete "ModifyComment" use case with sample data
 * and provides a console interface for user interaction.
 * 
 * The application simulates:
 * 1. A tourist viewing site details
 * 2. Selecting a comment to modify
 * 3. Entering new comment content
 * 4. Validating and confirming the change
 * 5. Executing the modification
 * 6. Notifying about alterations
 * 
 * It also handles the interruption scenario (connection to server ETOUR).
 */
public class Main {
    
    /**
     * Main entry point of the application.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("       ETOUR - Modify Comment Application      ");
        System.out.println("===============================================\n");
        
        try {
            // Initialize the application with sample data
            ApplicationController controller = new ApplicationController();
            controller.runApplication();
            
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /**
     * ApplicationController class that manages the overall application flow.
     * This inner class orchestrates the setup and execution of the ModifyComment use case.
     */
    private static class ApplicationController {
        private Site site;
        private Tourist tourist;
        private CommentModificationService service;
        
        /**
         * Runs the complete ModifyComment application.
         */
        public void runApplication() {
            try {
                // Step 1: Setup application with sample data
                System.out.println("Initializing application...");
                setupSampleData();
                
                // Step 2: Simulate tourist authentication
                System.out.println("\n=== Tourist Authentication ===");
                if (!authenticateTourist()) {
                    System.out.println("Authentication failed. Application cannot proceed.");
                    return;
                }
                
                // Step 3: Display site information
                displaySiteInformation();
                
                // Step 4: Run the ModifyComment use case
                runModifyCommentUseCase();
                
                // Step 5: Demonstrate error handling and edge cases
                demonstrateEdgeCases();
                
            } finally {
                // Step 6: Cleanup resources
                cleanup();
            }
        }
        
        /**
         * Sets up sample data for the demonstration.
         * Creates a sample site, tourist, and comments.
         */
        private void setupSampleData() {
            // Create sample tourist
            tourist = new Tourist("T001", "traveler123", "John Smith");
            System.out.println("Created tourist: " + tourist.getFullName() + " (ID: " + tourist.getId() + ")");
            
            // Create sample site
            site = new Site("S001", "Eiffel Tower", 
                    "Iconic iron tower on the Champ de Mars in Paris, France.");
            System.out.println("Created site: " + site.getName() + " (ID: " + site.getId() + ")");
            
            // Create sample comments for the tourist
            createSampleComments();
            
            // Create the comment modification service
            service = new CommentModificationService(site, tourist);
            System.out.println("Comment modification service initialized.");
        }
        
        /**
         * Creates sample comments for the demonstration.
         */
        private void createSampleComments() {
            // Create sample comments
            Comment comment1 = new Comment("C001", 
                    "This was an amazing experience! The view from the top was breathtaking.", 
                    tourist.getId(), site.getId());
            
            Comment comment2 = new Comment("C002", 
                    "The queues were very long, but it was worth the wait. Highly recommended!", 
                    tourist.getId(), site.getId());
            
            Comment comment3 = new Comment("C003", 
                    "Great historical site. The lighting at night is beautiful.", 
                    "T002", site.getId()); // Comment by another tourist
            
            // Add comments to site
            site.addComment(comment1);
            site.addComment(comment2);
            site.addComment(comment3);
            
            // Register comments with tourist
            tourist.addComment(comment1.getId());
            tourist.addComment(comment2.getId());
            
            System.out.println("Created " + site.getTotalComments() + " sample comments for the site.");
            
            // Display comment summary
            System.out.println("Tourist " + tourist.getFullName() + " has " + 
                    tourist.getCommentIds().size() + " comments on this site.");
        }
        
        /**
         * Simulates tourist authentication.
         * 
         * @return true if authentication successful, false otherwise
         */
        private boolean authenticateTourist() {
            // In a real application, this would involve proper authentication
            // For demonstration, we'll use a simple token
            String authToken = "VALID_TOKEN_T001";
            
            System.out.println("Authenticating tourist " + tourist.getUsername() + "...");
            boolean authenticated = tourist.authenticate(authToken);
            
            if (authenticated) {
                System.out.println("✓ Authentication successful!");
                return true;
            } else {
                System.out.println("✗ Authentication failed. Please check your credentials.");
                return false;
            }
        }
        
        /**
         * Displays site information to the tourist.
         */
        private void displaySiteInformation() {
            System.out.println("\n=== Site Details ===");
            System.out.println("Site: " + site.getName());
            System.out.println("Description: " + site.getDescription());
            System.out.println("Average Rating: " + site.getAverageRating() + "/5.0");
            System.out.println("Total Comments: " + site.getTotalComments());
            
            // Display tourist's comments on this site
            var touristComments = site.getCommentsByTourist(tourist.getId());
            if (!touristComments.isEmpty()) {
                System.out.println("\nYour comments on this site:");
                for (int i = 0; i < touristComments.size(); i++) {
                    Comment comment = touristComments.get(i);
                    System.out.println((i + 1) + ". [" + comment.getCreatedAt().toLocalDate() + 
                            "] " + comment.getContent());
                }
            }
        }
        
        /**
         * Runs the main ModifyComment use case.
         */
        private void runModifyCommentUseCase() {
            System.out.println("\n\n" + "=".repeat(50));
            System.out.println("   MODIFY COMMENT USE CASE DEMONSTRATION   ");
            System.out.println("=".repeat(50));
            
            // Execute the complete ModifyComment flow
            boolean success = service.executeModifyCommentFlow();
            
            if (success) {
                System.out.println("Use case completed successfully!");
            } else {
                System.out.println("Use case did not complete successfully.");
            }
            
            // Show final state
            displayCommentSummary();
        }
        
        /**
         * Displays a summary of comments after modification.
         */
        private void displayCommentSummary() {
            System.out.println("\n=== Final Comment Summary ===");
            var touristComments = site.getCommentsByTourist(tourist.getId());
            
            if (touristComments.isEmpty()) {
                System.out.println("You have no comments on this site.");
            } else {
                System.out.println("Your current comments on this site:");
                for (Comment comment : touristComments) {
                    String modifiedInfo = comment.isModified() ? 
                            " (Modified: " + comment.getModifiedAt().toLocalTime() + ")" : 
                            " (Original)";
                    System.out.println("- " + comment.getContent() + modifiedInfo);
                }
            }
        }
        
        /**
         * Demonstrates edge cases and error handling.
         */
        private void demonstrateEdgeCases() {
            System.out.println("\n\n" + "=".repeat(50));
            System.out.println("   EDGE CASES AND ERROR HANDLING DEMO   ");
            System.out.println("=".repeat(50));
            
            // Test 1: Try to modify a comment with empty content
            System.out.println("\n--- Test 1: Empty Comment Content ---");
            testEmptyCommentContent();
            
            // Test 2: Try to modify a comment that doesn't belong to tourist
            System.out.println("\n--- Test 2: Unauthorized Comment Modification ---");
            testUnauthorizedModification();
            
            // Test 3: Server disconnection scenario
            System.out.println("\n--- Test 3: Server Connection Interruption ---");
            testServerDisconnection();
        }
        
        /**
         * Tests the error case for empty comment content.
         */
        private void testEmptyCommentContent() {
            Comment comment = site.getCommentsByTourist(tourist.getId()).get(0);
            System.out.println("Attempting to modify comment with empty content...");
            
            try {
                // This should throw an exception
                comment.modifyContent("");
                System.out.println("ERROR: Should have thrown exception for empty content!");
            } catch (IllegalArgumentException e) {
                System.out.println("✓ Correctly caught exception: " + e.getMessage());
            }
        }
        
        /**
         * Tests unauthorized comment modification attempt.
         */
        private void testUnauthorizedModification() {
            // Get a comment that doesn't belong to our tourist
            Comment otherTouristComment = site.getComment("C003");
            if (otherTouristComment != null) {
                System.out.println("Comment by another tourist: " + otherTouristComment.getContent());
                System.out.println("Checking if our tourist can modify it...");
                
                boolean canModify = tourist.canModifyComment(otherTouristComment);
                System.out.println("Tourist can modify comment: " + canModify + 
                        " (Expected: false)");
                
                if (!canModify) {
                    System.out.println("✓ Security check passed: Tourist cannot modify " +
                            "comments by other tourists.");
                }
            }
        }
        
        /**
         * Tests server disconnection scenario.
         */
        private void testServerDisconnection() {
            System.out.println("Current server connection status: " + 
                    (service.isConnectedToServer() ? "Connected" : "Disconnected"));
            
            System.out.println("Simulating server disconnection...");
            service.simulateServerDisconnection();
            
            System.out.println("Attempting to modify a comment after disconnection...");
            Comment comment = site.getCommentsByTourist(tourist.getId()).get(0);
            
            // This should fail due to server disconnection
            boolean success = service.modifyComment(comment.getId(), 
                    "Test content after disconnection", false);
            
            System.out.println("Modification successful: " + success + 
                    " (Expected: false due to server disconnection)");
            
            // Reconnect for cleanup
            System.out.println("Reconnecting to server for cleanup...");
            service.reconnectToServer();
        }
        
        /**
         * Cleans up resources used by the application.
         */
        private void cleanup() {
            System.out.println("\n=== Application Cleanup ===");
            
            if (service != null) {
                service.close();
                System.out.println("Comment modification service closed.");
            }
            
            System.out.println("\n" + "=".repeat(50));
            System.out.println("   APPLICATION TERMINATED SUCCESSFULLY   ");
            System.out.println("=".repeat(50));
            System.out.println("\nThank you for using ETOUR Comment System!");
        }
    }
}