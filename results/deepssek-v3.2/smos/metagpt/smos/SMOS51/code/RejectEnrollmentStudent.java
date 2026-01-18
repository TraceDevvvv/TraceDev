import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a student registration request.
 */
class StudentRegistrationRequest {
    private int requestId;
    private String studentName;
    private String studentEmail;
    private String submissionDate;
    
    public StudentRegistrationRequest(int requestId, String studentName, String studentEmail, String submissionDate) {
        this.requestId = requestId;
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.submissionDate = submissionDate;
    }
    
    public int getRequestId() {
        return requestId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public String getStudentEmail() {
        return studentEmail;
    }
    
    public String getSubmissionDate() {
        return submissionDate;
    }
    
    @Override
    public String toString() {
        return String.format("Request ID: %d | Name: %s | Email: %s | Submitted: %s",
                requestId, studentName, studentEmail, submissionDate);
    }
}

/**
 * Represents an administrator who can manage student registration requests.
 */
class Administrator {
    private String adminId;
    private String name;
    
    public Administrator(String adminId, String name) {
        this.adminId = adminId;
        this.name = name;
    }
    
    public String getAdminId() {
        return adminId;
    }
    
    public String getName() {
        return name;
    }
}

/**
 * Manages the student registration system including viewing and rejecting requests.
 */
class RegistrationSystem {
    private List<StudentRegistrationRequest> pendingRequests;
    private List<StudentRegistrationRequest> rejectedRequests;
    private Administrator currentAdmin;
    
    /**
     * Initializes the registration system with a list of sample pending requests.
     */
    public RegistrationSystem(Administrator admin) {
        this.currentAdmin = admin;
        this.pendingRequests = new ArrayList<>();
        this.rejectedRequests = new ArrayList<>();
        initializeSampleRequests();
    }
    
    /**
     * Initializes some sample registration requests for demonstration.
     */
    private void initializeSampleRequests() {
        pendingRequests.add(new StudentRegistrationRequest(1, "John Doe", "john@example.com", "2024-01-15"));
        pendingRequests.add(new StudentRegistrationRequest(2, "Jane Smith", "jane@example.com", "2024-01-16"));
        pendingRequests.add(new StudentRegistrationRequest(3, "Bob Johnson", "bob@example.com", "2024-01-17"));
        pendingRequests.add(new StudentRegistrationRequest(4, "Alice Brown", "alice@example.com", "2024-01-18"));
    }
    
    /**
     * Displays all pending registration requests.
     * This simulates the "View Registration Requests" use case mentioned as a precondition.
     */
    public void displayPendingRequests() {
        if (pendingRequests.isEmpty()) {
            System.out.println("\nNo pending registration requests.");
            return;
        }
        
        System.out.println("\n=== PENDING REGISTRATION REQUESTS ===");
        System.out.println("Administrator: " + currentAdmin.getName());
        System.out.println("--------------------------------------");
        for (int i = 0; i < pendingRequests.size(); i++) {
            System.out.println((i + 1) + ". " + pendingRequests.get(i));
        }
        System.out.println("--------------------------------------");
    }
    
    /**
     * Rejects a student registration request.
     * @param requestId The ID of the request to reject
     * @return true if rejection was successful, false otherwise
     */
    public boolean rejectRegistrationRequest(int requestId) {
        // Find the request with the given ID
        StudentRegistrationRequest requestToReject = null;
        for (StudentRegistrationRequest request : pendingRequests) {
            if (request.getRequestId() == requestId) {
                requestToReject = request;
                break;
            }
        }
        
        // If request not found, return false
        if (requestToReject == null) {
            System.out.println("Error: Registration request with ID " + requestId + " not found.");
            return false;
        }
        
        // Remove from pending requests and add to rejected requests
        pendingRequests.remove(requestToReject);
        rejectedRequests.add(requestToReject);
        
        System.out.println("\n✅ Registration request #" + requestId + " rejected successfully.");
        System.out.println("Student: " + requestToReject.getStudentName());
        System.out.println("Email: " + requestToReject.getStudentEmail());
        System.out.println("Rejected by Administrator: " + currentAdmin.getName());
        
        // Simulate interrupting connection to SMOS server as mentioned in postconditions
        interruptSMOSServerConnection();
        
        return true;
    }
    
    /**
     * Simulates interrupting connection to SMOS server as specified in postconditions.
     */
    private void interruptSMOSServerConnection() {
        System.out.println("⚠️  Connection to SMOS server interrupted for security procedures.");
        System.out.println("   (Simulated behavior as per system requirements)");
    }
    
    /**
     * Gets the number of pending requests.
     * @return Count of pending requests
     */
    public int getPendingRequestCount() {
        return pendingRequests.size();
    }
    
    /**
     * Gets a copy of pending requests for display purposes.
     * @return List of pending requests
     */
    public List<StudentRegistrationRequest> getPendingRequests() {
        return new ArrayList<>(pendingRequests);
    }
}

/**
 * Main application class for the RejectEnrollmentStudent system.
 */
public class RejectEnrollmentStudent {
    
    /**
     * Main method - entry point of the application.
     */
    public static void main(String[] args) {
        System.out.println("=== STUDENT REGISTRATION MANAGEMENT SYSTEM ===");
        System.out.println("Use Case: RejectEnrollmentStudent");
        System.out.println("Actor: Administrator\n");
        
        // Create an administrator
        Administrator admin = new Administrator("ADMIN001", "Mike Johnson");
        System.out.println("Logged in as: " + admin.getName() + " (ID: " + admin.getAdminId() + ")");
        
        // Initialize the registration system
        RegistrationSystem system = new RegistrationSystem(admin);
        Scanner scanner = new Scanner(System.in);
        
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. View Registration Requests (Precondition)");
            System.out.println("2. Reject a Registration Request");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                switch (choice) {
                    case 1:
                        // View Registration Requests (precondition for Reject functionality)
                        system.displayPendingRequests();
                        break;
                        
                    case 2:
                        // Reject a registration request
                        if (system.getPendingRequestCount() == 0) {
                            System.out.println("No pending requests to reject.");
                            break;
                        }
                        
                        System.out.println("\n=== REJECT REGISTRATION REQUEST ===");
                        system.displayPendingRequests();
                        
                        System.out.print("\nEnter the Request ID to reject (or 0 to cancel): ");
                        int requestId = Integer.parseInt(scanner.nextLine().trim());
                        
                        if (requestId == 0) {
                            System.out.println("Operation cancelled.");
                            break;
                        }
                        
                        boolean success = system.rejectRegistrationRequest(requestId);
                        
                        if (success) {
                            // Display updated list of pending requests as per requirements
                            System.out.println("\n=== UPDATED LIST OF PENDING REQUESTS ===");
                            system.displayPendingRequests();
                        }
                        break;
                        
                    case 3:
                        running = false;
                        System.out.println("\nThank you for using the Student Registration Management System.");
                        System.out.println("Goodbye!");
                        break;
                        
                    default:
                        System.out.println("Invalid option. Please choose 1-3.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number.");
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
}