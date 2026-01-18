import java.util.*;
import java.io.*;
import java.time.LocalDateTime;

/**
 * InsertTag - A complete Java program implementing the InsertTag use case.
 * This program simulates a system for inserting new tag searches with validation,
 * duplicate checking, and error handling.
 * 
 * The system includes:
 * 1. Tag class representing a tag entity
 * 2. TagManager class for managing tags and operations
 * 3. UI simulation for form input and interaction
 * 4. Validation and error handling
 * 5. Connection management with server ETOUR
 * 
 * @author System Developer
 * @version 1.0
 */
public class InsertTag {
    
    /**
     * Represents a Tag entity in the system.
     * Each tag has an ID, name, description, creation date, and agency information.
     */
    static class Tag {
        private String id;
        private String name;
        private String description;
        private LocalDateTime createdAt;
        private String agencyId;
        
        public Tag(String id, String name, String description, String agencyId) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.agencyId = agencyId;
            this.createdAt = LocalDateTime.now();
        }
        
        public String getId() { return id; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public String getAgencyId() { return agencyId; }
        
        @Override
        public String toString() {
            return "Tag{id='" + id + "', name='" + name + "', description='" + description + 
                   "', agencyId='" + agencyId + "', createdAt=" + createdAt + "}";
        }
    }
    
    /**
     * Custom exception for when a tag already exists in the system.
     * This corresponds to the ExistingErrorTag use case.
     */
    static class ExistingTagException extends Exception {
        public ExistingTagException(String message) {
            super(message);
        }
    }
    
    /**
     * Custom exception for when data is invalid or insufficient.
     * This corresponds to the Errored use case.
     */
    static class InvalidDataException extends Exception {
        public InvalidDataException(String message) {
            super(message);
        }
    }
    
    /**
     * Custom exception for server connection interruptions.
     * This handles the ETOUR server connection interruption scenario.
     */
    static class ServerConnectionException extends Exception {
        public ServerConnectionException(String message) {
            super(message);
        }
    }
    
    /**
     * Manages all tag operations including storage, validation, and retrieval.
     * Implements singleton pattern to ensure single instance across the system.
     */
    static class TagManager {
        private static TagManager instance;
        private Map<String, Tag> tags; // Using name as key for duplicate checking
        private boolean serverConnected;
        
        private TagManager() {
            tags = new HashMap<>();
            serverConnected = true; // Assume connected by default
        }
        
        public static synchronized TagManager getInstance() {
            if (instance == null) {
                instance = new TagManager();
            }
            return instance;
        }
        
        /**
         * Checks if the server connection is available.
         * Simulates random connection failures for demonstration.
         * 
         * @throws ServerConnectionException if connection to server ETOUR is interrupted
         */
        private void checkServerConnection() throws ServerConnectionException {
            // Simulate random connection failure (10% chance for demonstration)
            if (Math.random() < 0.1) {
                serverConnected = false;
                throw new ServerConnectionException("Connection to server ETOUR interrupted");
            }
            serverConnected = true;
        }
        
        /**
         * Validates tag data according to business rules.
         * 
         * @param name The tag name to validate
         * @param description The tag description to validate
         * @param agencyId The agency ID to validate
         * @throws InvalidDataException if data is invalid or insufficient
         */
        private void validateTagData(String name, String description, String agencyId) 
                throws InvalidDataException {
            
            // Check for null or empty values
            if (name == null || name.trim().isEmpty()) {
                throw new InvalidDataException("Tag name cannot be empty");
            }
            
            if (description == null || description.trim().isEmpty()) {
                throw new InvalidDataException("Tag description cannot be empty");
            }
            
            if (agencyId == null || agencyId.trim().isEmpty()) {
                throw new InvalidDataException("Agency ID cannot be empty");
            }
            
            // Validate name length
            if (name.length() > 50) {
                throw new InvalidDataException("Tag name cannot exceed 50 characters");
            }
            
            // Validate description length
            if (description.length() > 200) {
                throw new InvalidDataException("Tag description cannot exceed 200 characters");
            }
            
            // Validate name format (alphanumeric and spaces only)
            if (!name.matches("^[a-zA-Z0-9\\s]+$")) {
                throw new InvalidDataException("Tag name can only contain alphanumeric characters and spaces");
            }
        }
        
        /**
         * Inserts a new tag into the system with full validation.
         * 
         * @param name The tag name
         * @param description The tag description
         * @param agencyId The agency ID (assumed logged in)
         * @return The created Tag object
         * @throws ExistingTagException if tag already exists
         * @throws InvalidDataException if data is invalid
         * @throws ServerConnectionException if server connection fails
         */
        public Tag insertTag(String name, String description, String agencyId) 
                throws ExistingTagException, InvalidDataException, ServerConnectionException {
            
            // Step 1: Check server connection
            checkServerConnection();
            
            // Step 2: Validate input data
            validateTagData(name, description, agencyId);
            
            // Step 3: Check for duplicate tag (case-insensitive)
            String normalizedName = name.trim().toLowerCase();
            synchronized (this) {
                if (tags.containsKey(normalizedName)) {
                    throw new ExistingTagException("Tag '" + name + "' already exists in the system");
                }
                
                // Step 4: Create and store the new tag
                String tagId = "TAG_" + System.currentTimeMillis() + "_" + 
                              (int)(Math.random() * 1000);
                Tag newTag = new Tag(tagId, name.trim(), description.trim(), agencyId.trim());
                tags.put(normalizedName, newTag);
                
                return newTag;
            }
        }
        
        /**
         * Checks if a tag with the given name already exists.
         * 
         * @param tagName The tag name to check
         * @return true if tag exists, false otherwise
         */
        public boolean tagExists(String tagName) {
            return tags.containsKey(tagName.trim().toLowerCase());
        }
        
        /**
         * Gets all tags in the system (for demonstration purposes).
         * 
         * @return List of all tags
         */
        public List<Tag> getAllTags() {
            return new ArrayList<>(tags.values());
        }
        
        /**
         * Gets the count of tags in the system.
         * 
         * @return Number of tags
         */
        public int getTagCount() {
            return tags.size();
        }
        
        /**
         * Resets the tag manager (for testing purposes).
         */
        public void reset() {
            tags.clear();
        }
        
        /**
         * Simulates server reconnection.
         */
        public void reconnectServer() {
            serverConnected = true;
            System.out.println("Server ETOUR reconnected successfully");
        }
    }
    
    /**
     * Simulates the UI form for entering tag information.
     * In a real application, this would be a GUI or web form.
     */
    static class TagForm {
        private Scanner scanner;
        
        public TagForm() {
            scanner = new Scanner(System.in);
        }
        
        /**
         * Displays the form and collects user input for a new tag.
         * 
         * @param agencyId The logged-in agency ID
         * @return An array containing [name, description] or null if cancelled
         */
        public String[] showForm(String agencyId) {
            System.out.println("\n=== Insert New Tag Search ===");
            System.out.println("Agency: " + agencyId);
            System.out.println("-----------------------------");
            
            System.out.print("Enter tag name: ");
            String name = scanner.nextLine();
            
            // Allow cancellation
            if (name.equalsIgnoreCase("cancel")) {
                return null;
            }
            
            System.out.print("Enter tag description: ");
            String description = scanner.nextLine();
            
            return new String[]{name, description};
        }
        
        /**
         * Closes the form resources.
         */
        public void close() {
            scanner.close();
        }
    }
    
    /**
     * Main application class that orchestrates the InsertTag use case flow.
     */
    static class InsertTagApplication {
        private TagManager tagManager;
        private TagForm tagForm;
        private String currentAgencyId;
        
        public InsertTagApplication(String agencyId) {
            this.tagManager = TagManager.getInstance();
            this.tagForm = new TagForm();
            this.currentAgencyId = agencyId;
        }
        
        /**
         * Executes the complete InsertTag use case flow.
         */
        public void run() {
            System.out.println("=== Tag Management System ===");
            System.out.println("Agency " + currentAgencyId + " logged in successfully.");
            
            boolean continueRunning = true;
            
            while (continueRunning) {
                try {
                    // Step 1: Access the functionality of inserting new tag search
                    accessInsertFunctionality();
                    
                    // Step 2: Show the form for entering a tag
                    String[] formData = tagForm.showForm(currentAgencyId);
                    
                    if (formData == null) {
                        System.out.println("Operation cancelled by user.");
                        continue;
                    }
                    
                    String tagName = formData[0];
                    String tagDescription = formData[1];
                    
                    // Step 3 & 4: Submit and process the tag
                    processTagSubmission(tagName, tagDescription);
                    
                } catch (ExistingTagException e) {
                    handleExistingTagError(e);
                } catch (InvalidDataException e) {
                    handleInvalidDataError(e);
                } catch (ServerConnectionException e) {
                    handleServerConnectionError(e);
                } catch (Exception e) {
                    System.out.println("Unexpected error: " + e.getMessage());
                    e.printStackTrace();
                }
                
                // Ask if user wants to continue
                System.out.print("\nDo you want to insert another tag? (yes/no): ");
                Scanner tempScanner = new Scanner(System.in);
                String response = tempScanner.nextLine();
                if (!response.equalsIgnoreCase("yes")) {
                    continueRunning = false;
                }
            }
            
            // Display final statistics
            displayStatistics();
            tagForm.close();
            System.out.println("\n=== Application terminated ===");
        }
        
        /**
         * Step 1: Access the functionality of inserting new tag search.
         */
        private void accessInsertFunctionality() {
            System.out.println("\nAccessing tag insertion functionality...");
            // In a real system, this might involve loading UI components,
            // checking permissions, or initializing resources
        }
        
        /**
         * Step 3 & 4: Process tag submission with validation and insertion.
         * 
         * @param name The tag name
         * @param description The tag description
         * @throws ExistingTagException if tag exists
         * @throws InvalidDataException if data invalid
         * @throws ServerConnectionException if server disconnected
         */
        private void processTagSubmission(String name, String description) 
                throws ExistingTagException, InvalidDataException, ServerConnectionException {
            
            System.out.println("\nProcessing tag submission...");
            System.out.println("Tag Name: " + name);
            System.out.println("Description: " + description);
            
            // Insert the tag (includes validation and duplicate checking)
            Tag newTag = tagManager.insertTag(name, description, currentAgencyId);
            
            // Success notification - Exit condition
            System.out.println("\n✓ SUCCESS: Tag '" + newTag.getName() + "' has been successfully inserted!");
            System.out.println("  Tag ID: " + newTag.getId());
            System.out.println("  Created at: " + newTag.getCreatedAt());
        }
        
        /**
         * Handles the ExistingErrorTag use case when tag already exists.
         * 
         * @param e The ExistingTagException
         */
        private void handleExistingTagError(ExistingTagException e) {
            System.out.println("\n✗ ERROR: " + e.getMessage());
            System.out.println("Activating ExistingErrorTag use case...");
            System.out.println("Please use a different tag name or modify the existing tag.");
        }
        
        /**
         * Handles the Errored use case when data is invalid.
         * 
         * @param e The InvalidDataException
         */
        private void handleInvalidDataError(InvalidDataException e) {
            System.out.println("\n✗ ERROR: " + e.getMessage());
            System.out.println("Activating Errored use case...");
            System.out.println("Please correct the following issues:");
            System.out.println("1. Ensure all fields are filled");
            System.out.println("2. Check field length restrictions");
            System.out.println("3. Verify data format requirements");
        }
        
        /**
         * Handles server ETOUR connection interruption.
         * 
         * @param e The ServerConnectionException
         */
        private void handleServerConnectionError(ServerConnectionException e) {
            System.out.println("\n✗ CRITICAL ERROR: " + e.getMessage());
            System.out.println("Server ETOUR connection interrupted!");
            System.out.println("Attempting to reconnect...");
            
            // Simulate reconnection attempt
            try {
                Thread.sleep(2000); // Simulate reconnection delay
                tagManager.reconnectServer();
                System.out.println("Please try your operation again.");
            } catch (InterruptedException ie) {
                System.out.println("Reconnection interrupted.");
            }
        }
        
        /**
         * Displays system statistics.
         */
        private void displayStatistics() {
            System.out.println("\n=== System Statistics ===");
            System.out.println("Total tags in system: " + tagManager.getTagCount());
            System.out.println("Current agency: " + currentAgencyId);
        }
    }
    
    /**
     * Main method - Entry point of the application.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Initializing InsertTag Application...");
        
        // In a real system, the agency ID would come from authentication
        // For this simulation, we'll use a default agency ID
        String agencyId = "AGENCY_001";
        
        // Check if agency is logged (entry condition)
        if (agencyId == null || agencyId.trim().isEmpty()) {
            System.out.println("ERROR: Agency must be logged in to use this functionality.");
            System.out.println("Please log in and try again.");
            return;
        }
        
        // Create and run the application
        InsertTagApplication app = new InsertTagApplication(agencyId);
        app.run();
    }
    
    /**
     * Utility method for testing the InsertTag functionality programmatically.
     * This demonstrates how the system handles various scenarios.
     */
    public static void runTestScenarios() {
        System.out.println("\n=== Running Test Scenarios ===");
        TagManager tm = TagManager.getInstance();
        tm.reset(); // Clear any existing data
        
        try {
            // Test 1: Valid tag insertion
            System.out.println("\nTest 1: Inserting valid tag...");
            Tag tag1 = tm.insertTag("Java Programming", "Tags related to Java programming language", "AGENCY_001");
            System.out.println("Success: " + tag1.getName() + " inserted");
            
            // Test 2: Duplicate tag (should throw ExistingTagException)
            System.out.println("\nTest 2: Attempting to insert duplicate tag...");
            try {
                tm.insertTag("Java Programming", "Another Java tag", "AGENCY_001");
            } catch (ExistingTagException e) {
                System.out.println("Expected error: " + e.getMessage());
            }
            
            // Test 3: Invalid data - empty name (should throw InvalidDataException)
            System.out.println("\nTest 3: Attempting to insert tag with empty name...");
            try {
                tm.insertTag("", "Description", "AGENCY_001");
            } catch (InvalidDataException e) {
                System.out.println("Expected error: " + e.getMessage());
            }
            
            // Test 4: Another valid tag
            System.out.println("\nTest 4: Inserting another valid tag...");
            Tag tag2 = tm.insertTag("Web Development", "Tags related to web technologies", "AGENCY_001");
            System.out.println("Success: " + tag2.getName() + " inserted");
            
            // Display all tags
            System.out.println("\nAll tags in system:");
            for (Tag tag : tm.getAllTags()) {
                System.out.println("  - " + tag.getName() + " (" + tag.getId() + ")");
            }
            
        } catch (Exception e) {
            System.out.println("Unexpected error in test: " + e.getMessage());
        }
    }
}