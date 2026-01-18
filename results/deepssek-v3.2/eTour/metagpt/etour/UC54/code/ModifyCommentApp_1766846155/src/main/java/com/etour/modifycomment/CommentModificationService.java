package com.etour.modifycomment;

import java.util.Scanner;

/**
 * Service class that handles the business logic for modifying comments.
 * This class implements the "ModifyComment" use case flow:
 * 1. Choose to change comment on feedback
 * 2. Verify data entered and ask for confirmation; if invalid, activate error case
 * 3. Confirm change
 * 4. Edit commentary on selected feedback
 * 5. Notify alterations
 * 
 * The service also handles connection interruptions to server ETOUR.
 */
public class CommentModificationService {
    private final Site site;
    private final Tourist tourist;
    private boolean isConnectedToServer;
    private final Scanner scanner;
    
    /**
     * Constructor for CommentModificationService.
     * 
     * @param site The site where comments are being modified
     * @param tourist The tourist who is modifying comments
     */
    public CommentModificationService(Site site, Tourist tourist) {
        if (site == null) {
            throw new IllegalArgumentException("Site cannot be null");
        }
        if (tourist == null) {
            throw new IllegalArgumentException("Tourist cannot be null");
        }
        
        this.site = site;
        this.tourist = tourist;
        this.isConnectedToServer = true; // Assume connected initially
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main method to execute the comment modification process.
     * This method orchestrates the entire flow of the use case.
     * 
     * @return true if comment was successfully modified, false otherwise
     */
    public boolean executeModifyCommentFlow() {
        System.out.println("=== Modify Comment Process ===");
        System.out.println("Site: " + site.getName());
        System.out.println("Tourist: " + tourist.getFullName());
        
        // Check connection to server ETOUR
        if (!checkServerConnection()) {
            System.out.println("Error: Connection to server ETOUR interrupted.");
            return false;
        }
        
        // Step 1: Choose to change comment on feedback
        Comment commentToModify = selectCommentToModify();
        if (commentToModify == null) {
            System.out.println("No comment selected for modification.");
            return false;
        }
        
        // Step 2: Verify data entered and ask for confirmation
        String newContent = getNewCommentContent();
        if (!validateNewContent(newContent)) {
            System.out.println("Error: Invalid comment content provided.");
            return false;
        }
        
        // Ask for confirmation
        if (!askForConfirmation(commentToModify, newContent)) {
            System.out.println("Comment modification cancelled by user.");
            return false;
        }
        
        // Step 3: Confirm change (implied by user confirmation)
        // Step 4: Edit commentary on selected feedback
        boolean modificationSuccessful = performCommentModification(commentToModify, newContent);
        
        // Exit: System shall notify alterations
        if (modificationSuccessful) {
            notifyAlterations(commentToModify);
        }
        
        return modificationSuccessful;
    }
    
    /**
     * Selects a comment to modify from the tourist's comments on this site.
     * This implements Step 1 of the use case flow.
     * 
     * @return The selected comment to modify, or null if no valid selection
     */
    private Comment selectCommentToModify() {
        System.out.println("\n--- Step 1: Select Comment to Modify ---");
        
        // Get all comments by this tourist on this site
        var touristComments = site.getCommentsByTourist(tourist.getId());
        
        if (touristComments.isEmpty()) {
            System.out.println("You have no comments on this site to modify.");
            return null;
        }
        
        System.out.println("Your comments on this site:");
        for (int i = 0; i < touristComments.size(); i++) {
            Comment comment = touristComments.get(i);
            System.out.printf("%d. [%s] %s%n", 
                i + 1, 
                comment.getCreatedAt().toString(), 
                comment.getContent().length() > 50 ? 
                    comment.getContent().substring(0, 47) + "..." : 
                    comment.getContent());
        }
        
        System.out.print("\nEnter the number of the comment you want to modify (or 0 to cancel): ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine().trim());
            
            if (choice == 0) {
                System.out.println("Operation cancelled.");
                return null;
            }
            
            if (choice < 1 || choice > touristComments.size()) {
                System.out.println("Invalid selection. Please enter a number between 1 and " + touristComments.size());
                return null;
            }
            
            Comment selectedComment = touristComments.get(choice - 1);
            System.out.println("Selected comment: " + selectedComment.getContent());
            return selectedComment;
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return null;
        }
    }
    
    /**
     * Gets new comment content from the user.
     * This is part of Step 2 of the use case flow.
     * 
     * @return The new comment content entered by the user
     */
    private String getNewCommentContent() {
        System.out.println("\n--- Step 2: Enter New Comment Content ---");
        System.out.println("Please enter the new content for your comment:");
        System.out.print("> ");
        
        return scanner.nextLine().trim();
    }
    
    /**
     * Validates the new comment content.
     * This implements the validation part of Step 2 of the use case flow.
     * If data is invalid, the system should activate the error case.
     * 
     * @param newContent The new comment content to validate
     * @return true if content is valid, false otherwise
     */
    private boolean validateNewContent(String newContent) {
        if (newContent == null || newContent.trim().isEmpty()) {
            System.out.println("Error: Comment content cannot be empty.");
            return false;
        }
        
        if (newContent.length() < 5) {
            System.out.println("Error: Comment must be at least 5 characters long.");
            return false;
        }
        
        if (newContent.length() > 1000) {
            System.out.println("Error: Comment cannot exceed 1000 characters.");
            return false;
        }
        
        // Check for inappropriate content (basic example)
        String[] inappropriateWords = {"spam", "advertisement", "offensive"};
        String lowerContent = newContent.toLowerCase();
        for (String word : inappropriateWords) {
            if (lowerContent.contains(word)) {
                System.out.println("Error: Comment contains inappropriate content.");
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Asks the user for confirmation before modifying the comment.
     * This implements the confirmation part of Step 2 of the use case flow.
     * 
     * @param comment The comment to be modified
     * @param newContent The new content for the comment
     * @return true if user confirms, false otherwise
     */
    private boolean askForConfirmation(Comment comment, String newContent) {
        System.out.println("\n--- Confirm Modification ---");
        System.out.println("Original comment: " + comment.getContent());
        System.out.println("New comment: " + newContent);
        System.out.println("\nAre you sure you want to modify this comment? (yes/no)");
        System.out.print("> ");
        
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }
    
    /**
     * Performs the actual comment modification.
     * This implements Step 4 of the use case flow.
     * 
     * @param comment The comment to modify
     * @param newContent The new content for the comment
     * @return true if modification was successful, false otherwise
     */
    private boolean performCommentModification(Comment comment, String newContent) {
        System.out.println("\n--- Step 4: Modifying Comment ---");
        
        // Check server connection again before modification
        if (!checkServerConnection()) {
            System.out.println("Error: Connection lost during modification process.");
            return false;
        }
        
        try {
            // Check if tourist has permission to modify this comment
            if (!tourist.canModifyComment(comment)) {
                System.out.println("Error: You do not have permission to modify this comment.");
                return false;
            }
            
            // Perform the modification
            boolean success = comment.modifyContent(newContent);
            
            if (success) {
                System.out.println("Comment successfully modified.");
                return true;
            } else {
                System.out.println("Comment was not modified (content unchanged).");
                return false;
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error during modification: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error during modification: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Notifies the user about the alterations made to the comment.
     * This implements the exit condition of the use case.
     * 
     * @param modifiedComment The comment that was modified
     */
    private void notifyAlterations(Comment modifiedComment) {
        System.out.println("\n=== Notification of Alterations ===");
        System.out.println("Your comment has been successfully updated.");
        System.out.println("Comment ID: " + modifiedComment.getId());
        System.out.println("Modified at: " + modifiedComment.getModifiedAt());
        System.out.println("New content: " + modifiedComment.getContent());
        System.out.println("====================================\n");
    }
    
    /**
     * Checks the connection to the server ETOUR.
     * This handles the interruption condition mentioned in the use case.
     * 
     * @return true if connected to server, false otherwise
     */
    private boolean checkServerConnection() {
        // In a real implementation, this would check actual server connectivity
        // For this simulation, we'll use a simple flag that can be toggled
        return isConnectedToServer;
    }
    
    /**
     * Simulates a server connection interruption.
     * This method can be used to test the interruption handling.
     */
    public void simulateServerDisconnection() {
        this.isConnectedToServer = false;
        System.out.println("Simulated server disconnection.");
    }
    
    /**
     * Re-establishes server connection.
     */
    public void reconnectToServer() {
        this.isConnectedToServer = true;
        System.out.println("Reconnected to server.");
    }
    
    /**
     * Gets the current server connection status.
     * 
     * @return true if connected to server, false otherwise
     */
    public boolean isConnectedToServer() {
        return isConnectedToServer;
    }
    
    /**
     * Closes the scanner resource.
     * This should be called when the service is no longer needed.
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    /**
     * Alternative method for modifying a comment without interactive prompts.
     * This can be used for programmatic comment modification.
     * 
     * @param commentId The ID of the comment to modify
     * @param newContent The new content for the comment
     * @param requireConfirmation Whether to require confirmation (simulated)
     * @return true if modification was successful, false otherwise
     */
    public boolean modifyComment(String commentId, String newContent, boolean requireConfirmation) {
        // Check server connection
        if (!checkServerConnection()) {
            System.out.println("Error: Connection to server ETOUR interrupted.");
            return false;
        }
        
        // Get the comment
        Comment comment = site.getComment(commentId);
        if (comment == null) {
            System.out.println("Error: Comment not found.");
            return false;
        }
        
        // Check permissions
        if (!tourist.canModifyComment(comment)) {
            System.out.println("Error: Tourist does not have permission to modify this comment.");
            return false;
        }
        
        // Validate new content
        if (!validateNewContent(newContent)) {
            System.out.println("Error: Invalid comment content.");
            return false;
        }
        
        // Simulate confirmation if required
        if (requireConfirmation) {
            System.out.println("Simulating confirmation for comment modification...");
            // In a real implementation, this would involve user interaction
        }
        
        // Perform modification
        boolean success = comment.modifyContent(newContent);
        
        // Notify if successful
        if (success) {
            notifyAlterations(comment);
        }
        
        return success;
    }
}