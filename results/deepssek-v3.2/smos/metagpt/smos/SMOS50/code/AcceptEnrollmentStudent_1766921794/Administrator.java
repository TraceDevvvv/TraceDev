package com.smos.enrollment;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Administrator class representing an admin user who can manage student enrollment requests.
 * Handles accepting/rejecting requests and managing SMOS server connection.
 */
public class Administrator {
    private String adminId;
    private String name;
    private String email;
    private boolean isConnectedToSMOS;
    private List<RegistrationRequest> pendingRequests;
    private List<RegistrationRequest> allRequests;
    
    /**
     * Default constructor
     */
    public Administrator() {
        this.isConnectedToSMOS = true; // Assume connected by default
        this.pendingRequests = new ArrayList<>();
        this.allRequests = new ArrayList<>();
    }
    
    /**
     * Parameterized constructor for creating an administrator
     * @param adminId Unique identifier for the administrator
     * @param name Full name of the administrator
     * @param email Email address of the administrator
     */
    public Administrator(String adminId, String name, String email) {
        this.adminId = adminId;
        this.name = name;
        this.email = email;
        this.isConnectedToSMOS = true; // Assume connected by default
        this.pendingRequests = new ArrayList<>();
        this.allRequests = new ArrayList<>();
    }
    
    // Getters and Setters
    
    public String getAdminId() {
        return adminId;
    }
    
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isConnectedToSMOS() {
        return isConnectedToSMOS;
    }
    
    public void setConnectedToSMOS(boolean isConnectedToSMOS) {
        this.isConnectedToSMOS = isConnectedToSMOS;
    }
    
    public List<RegistrationRequest> getPendingRequests() {
        return new ArrayList<>(pendingRequests); // Return copy to prevent external modification
    }
    
    public List<RegistrationRequest> getAllRequests() {
        return new ArrayList<>(allRequests); // Return copy to prevent external modification
    }
    
    /**
     * Adds a registration request to the system
     * @param request RegistrationRequest to add
     */
    public void addRegistrationRequest(RegistrationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Registration request cannot be null");
        }
        
        if (!request.isValid()) {
            throw new IllegalArgumentException("Invalid registration request data");
        }
        
        allRequests.add(request);
        
        // If request is pending, add to pending list
        if (request.isPending()) {
            pendingRequests.add(request);
        }
    }
    
    /**
     * Loads sample registration requests for demonstration purposes
     * This simulates the "View Registration Requests" use case precondition
     */
    public void loadSampleRequests() {
        // Clear existing requests
        pendingRequests.clear();
        allRequests.clear();
        
        // Create sample students
        Student student1 = new Student("STU001", "John", "Doe", 
                                      "john.doe@example.com", "555-0101", "2023-10-15");
        Student student2 = new Student("STU002", "Jane", "Smith", 
                                      "jane.smith@example.com", "555-0102", "2023-10-16");
        Student student3 = new Student("STU003", "Robert", "Johnson", 
                                      "robert.j@example.com", "555-0103", "2023-10-17");
        Student student4 = new Student("STU004", "Emily", "Williams", 
                                      "emily.w@example.com", "555-0104", "2023-10-18");
        
        // Create sample registration requests
        RegistrationRequest request1 = new RegistrationRequest("REQ001", student1);
        RegistrationRequest request2 = new RegistrationRequest("REQ002", student2);
        RegistrationRequest request3 = new RegistrationRequest("REQ003", student3);
        RegistrationRequest request4 = new RegistrationRequest("REQ004", student4);
        
        // Add requests to system
        addRegistrationRequest(request1);
        addRegistrationRequest(request2);
        addRegistrationRequest(request3);
        addRegistrationRequest(request4);
        
        System.out.println("Loaded " + pendingRequests.size() + " pending registration requests.");
    }
    
    /**
     * Displays all pending registration requests
     * This simulates the "View Registration Requests" use case
     */
    public void displayPendingRequests() {
        if (pendingRequests.isEmpty()) {
            System.out.println("\nNo pending registration requests found.");
            return;
        }
        
        System.out.println("\n=== PENDING REGISTRATION REQUESTS ===");
        System.out.println("Total pending requests: " + pendingRequests.size());
        System.out.println("=====================================\n");
        
        for (int i = 0; i < pendingRequests.size(); i++) {
            RegistrationRequest request = pendingRequests.get(i);
            System.out.println("[" + (i + 1) + "] " + request.getSummary());
            System.out.println("    Student: " + request.getStudent().getFullName());
            System.out.println("    Email: " + request.getStudent().getEmail());
            System.out.println("    Request Date: " + request.getFormattedRequestDate());
            System.out.println();
        }
    }
    
    /**
     * Accepts a student enrollment request (Main use case functionality)
     * @param requestIndex Index of the request in the pending list (1-based)
     * @return true if request was successfully accepted, false otherwise
     * @throws IllegalStateException if SMOS server connection is interrupted
     */
    public boolean acceptEnrollmentRequest(int requestIndex) {
        // Check SMOS server connection
        if (!isConnectedToSMOS) {
            System.out.println("ERROR: Cannot process request - SMOS server connection is interrupted!");
            throw new IllegalStateException("SMOS server connection interrupted");
        }
        
        // Validate index
        if (requestIndex < 1 || requestIndex > pendingRequests.size()) {
            System.out.println("ERROR: Invalid request index. Please select a valid request.");
            return false;
        }
        
        // Get the request (convert to 0-based index)
        RegistrationRequest request = pendingRequests.get(requestIndex - 1);
        
        // Check if request is still pending
        if (!request.isPending()) {
            System.out.println("ERROR: Request " + request.getRequestId() + 
                             " is no longer pending. Current status: " + request.getStatus());
            return false;
        }
        
        try {
            // Step 1: Activate the new user in the system
            System.out.println("Processing request " + request.getRequestId() + "...");
            
            // Approve the request (this also activates the student)
            request.approveRequest(this.adminId);
            
            // Remove from pending list
            pendingRequests.remove(requestIndex - 1);
            
            System.out.println("✓ Successfully activated student: " + 
                             request.getStudent().getFullName());
            System.out.println("✓ Student ID: " + request.getStudent().getStudentId() + 
                             " is now active in the system.");
            
            // Step 2: Display the list of registrations yet to be activated
            System.out.println("\n=== REMAINING PENDING REQUESTS ===");
            if (pendingRequests.isEmpty()) {
                System.out.println("No pending requests remaining.");
            } else {
                for (int i = 0; i < pendingRequests.size(); i++) {
                    RegistrationRequest remainingRequest = pendingRequests.get(i);
                    System.out.println("[" + (i + 1) + "] " + remainingRequest.getSummary());
                }
            }
            
            return true;
            
        } catch (Exception e) {
            System.out.println("ERROR: Failed to accept enrollment request: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Rejects a student enrollment request
     * @param requestIndex Index of the request in the pending list (1-based)
     * @return true if request was successfully rejected, false otherwise
     */
    public boolean rejectEnrollmentRequest(int requestIndex) {
        // Check SMOS server connection
        if (!isConnectedToSMOS) {
            System.out.println("ERROR: Cannot process request - SMOS server connection is interrupted!");
            return false;
        }
        
        // Validate index
        if (requestIndex < 1 || requestIndex > pendingRequests.size()) {
            System.out.println("ERROR: Invalid request index. Please select a valid request.");
            return false;
        }
        
        RegistrationRequest request = pendingRequests.get(requestIndex - 1);
        
        try {
            // Reject the request
            request.rejectRequest(this.adminId);
            
            // Remove from pending list
            pendingRequests.remove(requestIndex - 1);
            
            System.out.println("✓ Successfully rejected enrollment request for: " + 
                             request.getStudent().getFullName());
            return true;
            
        } catch (Exception e) {
            System.out.println("ERROR: Failed to reject enrollment request: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Simulates interruption of SMOS server connection
     * This handles the postcondition about connection interruption
     */
    public void interruptSMOSConnection() {
        System.out.println("\n⚠ WARNING: SMOS server connection interrupted!");
        this.isConnectedToSMOS = false;
    }
    
    /**
     * Restores SMOS server connection
     */
    public void restoreSMOSConnection() {
        System.out.println("✓ SMOS server connection restored.");
        this.isConnectedToSMOS = true;
    }
    
    /**
     * Gets statistics about registration requests
     * @return String containing request statistics
     */
    public String getRequestStatistics() {
        int total = allRequests.size();
        int pending = pendingRequests.size();
        int approved = 0;
        int rejected = 0;
        
        for (RegistrationRequest request : allRequests) {
            if (request.isApproved()) {
                approved++;
            } else if (request.isRejected()) {
                rejected++;
            }
        }
        
        return String.format(
            "Request Statistics:\n" +
            "  Total Requests: %d\n" +
            "  Pending: %d\n" +
            "  Approved: %d\n" +
            "  Rejected: %d\n" +
            "  SMOS Connection: %s",
            total, pending, approved, rejected,
            isConnectedToSMOS ? "Connected" : "DISCONNECTED"
        );
    }
    
    /**
     * Interactive method to accept enrollment requests from console
     * This provides a user-friendly interface for the administrator
     */
    public void processRequestsInteractive() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n=== ENROLLMENT REQUEST PROCESSING ===");
        System.out.println("Logged in as: " + name + " (ID: " + adminId + ")");
        System.out.println("SMOS Server: " + (isConnectedToSMOS ? "Connected ✓" : "Disconnected ✗"));
        
        boolean continueProcessing = true;
        
        while (continueProcessing && !pendingRequests.isEmpty()) {
            // Display pending requests
            displayPendingRequests();
            
            System.out.print("\nEnter request number to accept (1-" + pendingRequests.size() + 
                           "), 'r' to reject, 's' for stats, 'i' to interrupt connection, 'q' to quit: ");
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("q")) {
                System.out.println("Exiting request processing...");
                continueProcessing = false;
            } else if (input.equals("s")) {
                System.out.println(getRequestStatistics());
            } else if (input.equals("i")) {
                interruptSMOSConnection();
            } else if (input.equals("r")) {
                // Reject a request
                System.out.print("Enter request number to reject: ");
                try {
                    int rejectIndex = Integer.parseInt(scanner.nextLine().trim());
                    rejectEnrollmentRequest(rejectIndex);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            } else {
                // Try to accept a request
                try {
                    int requestIndex = Integer.parseInt(input);
                    acceptEnrollmentRequest(requestIndex);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number or command.");
                } catch (IllegalStateException e) {
                    System.out.println("Operation failed due to SMOS server connection issue.");
                    System.out.print("Attempt to restore connection? (y/n): ");
                    String restore = scanner.nextLine().trim().toLowerCase();
                    if (restore.equals("y")) {
                        restoreSMOSConnection();
                    }
                }
            }
            
            // Add a separator for readability
            if (continueProcessing && !pendingRequests.isEmpty()) {
                System.out.println("\n" + "=".repeat(50));
            }
        }
        
        if (pendingRequests.isEmpty()) {
            System.out.println("\nAll registration requests have been processed!");
        }
        
        System.out.println("\nFinal Statistics:");
        System.out.println(getRequestStatistics());
        
        // Don't close scanner to avoid closing System.in
    }
    
    @Override
    public String toString() {
        return "Administrator ID: " + adminId + 
               "\nName: " + name + 
               "\nEmail: " + email + 
               "\nSMOS Connection: " + (isConnectedToSMOS ? "Active" : "Interrupted") +
               "\nPending Requests: " + pendingRequests.size() +
               "\nTotal Requests: " + allRequests.size();
    }
}