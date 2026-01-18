import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Main class for the DeleteUser use case implementation.
 * This program simulates a user management system where an administrator can delete users.
 */
public class DeleteUser {
    
    /**
     * Represents a user in the system with basic attributes.
     */
    static class User {
        private String id;
        private String name;
        private String email;
        private boolean isActive;
        
        public User(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.isActive = true;
        }
        
        public String getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public boolean isActive() { return isActive; }
        public void setActive(boolean active) { isActive = active; }
        
        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Email: " + email + ", Status: " + (isActive ? "Active" : "Deleted");
        }
    }
    
    /**
     * Represents an administrator with authentication capabilities.
     */
    static class Administrator {
        private String username;
        private String password;
        private boolean isLoggedIn;
        
        public Administrator(String username, String password) {
            this.username = username;
            this.password = password;
            this.isLoggedIn = false;
        }
        
        public boolean login(String username, String password) {
            if (this.username.equals(username) && this.password.equals(password)) {
                isLoggedIn = true;
                return true;
            }
            return false;
        }
        
        public void logout() {
            isLoggedIn = false;
        }
        
        public boolean isLoggedIn() {
            return isLoggedIn;
        }
    }
    
    /**
     * Manages the user archive (database simulation).
     */
    static class UserArchive {
        private Map<String, User> users;
        
        public UserArchive() {
            users = new HashMap<>();
            // Initialize with some sample users
            addUser(new User("001", "Alice Johnson", "alice@example.com"));
            addUser(new User("002", "Bob Smith", "bob@example.com"));
            addUser(new User("003", "Charlie Brown", "charlie@example.com"));
        }
        
        public void addUser(User user) {
            users.put(user.getId(), user);
        }
        
        /**
         * Deletes a user from the archive by marking them as inactive.
         * @param userId The ID of the user to delete
         * @return true if deletion was successful, false otherwise
         */
        public boolean deleteUser(String userId) {
            User user = users.get(userId);
            if (user != null && user.isActive()) {
                user.setActive(false);
                return true;
            }
            return false;
        }
        
        /**
         * Gets a user by ID for viewing details.
         * @param userId The ID of the user to retrieve
         * @return The user object or null if not found
         */
        public User getUser(String userId) {
            User user = users.get(userId);
            if (user != null && user.isActive()) {
                return user;
            }
            return null;
        }
        
        /**
         * Gets all active users in the system.
         * @return List of active users
         */
        public List<User> getActiveUsers() {
            List<User> activeUsers = new ArrayList<>();
            for (User user : users.values()) {
                if (user.isActive()) {
                    activeUsers.add(user);
                }
            }
            return activeUsers;
        }
        
        /**
         * Gets all users (both active and deleted) for administrative purposes.
         * @return List of all users
         */
        public List<User> getAllUsers() {
            return new ArrayList<>(users.values());
        }
    }
    
    /**
     * Simulates connection to SMOS server.
     */
    static class SMOSServerConnection {
        private boolean isConnected;
        
        public SMOSServerConnection() {
            isConnected = true; // Start as connected
        }
        
        public boolean isConnected() {
            return isConnected;
        }
        
        /**
         * Interrupts the connection to SMOS server as per postcondition.
         */
        public void interruptConnection() {
            isConnected = false;
            System.out.println("SMOS server connection interrupted.");
        }
        
        /**
         * Reconnects to SMOS server (for simulation purposes).
         */
        public void reconnect() {
            isConnected = true;
            System.out.println("SMOS server reconnected.");
        }
    }
    
    /**
     * Main system class that orchestrates the DeleteUser use case.
     */
    static class UserManagementSystem {
        private UserArchive archive;
        private Administrator admin;
        private SMOSServerConnection serverConnection;
        private Scanner scanner;
        private User currentUserViewing; // Tracks which user is currently being viewed
        
        public UserManagementSystem() {
            archive = new UserArchive();
            admin = new Administrator("admin", "admin123");
            serverConnection = new SMOSServerConnection();
            scanner = new Scanner(System.in);
            currentUserViewing = null;
        }
        
        /**
         * Simulates the 'viewdetTailsente' use case mentioned in preconditions.
         * Allows administrator to view details of a specific user.
         */
        public void viewUserDetails() {
            if (!admin.isLoggedIn()) {
                System.out.println("Error: Administrator must be logged in to view user details.");
                return;
            }
            
            System.out.println("\n=== View User Details ===");
            System.out.print("Enter user ID to view details: ");
            String userId = scanner.nextLine().trim();
            
            User user = archive.getUser(userId);
            if (user != null) {
                currentUserViewing = user;
                System.out.println("User Details:");
                System.out.println(user);
                System.out.println("\nOptions:");
                System.out.println("1. Delete this user");
                System.out.println("2. Return to main menu");
            } else {
                System.out.println("User not found or has been deleted.");
                currentUserViewing = null;
            }
        }
        
        /**
         * Executes the DeleteUser use case as described in the requirements.
         * This method handles the complete sequence of events.
         */
        public void executeDeleteUser() {
            // Check preconditions
            if (!admin.isLoggedIn()) {
                System.out.println("Error: Administrator must be logged in to delete users.");
                return;
            }
            
            if (currentUserViewing == null) {
                System.out.println("Error: No user is currently being viewed. Please use 'View User Details' first.");
                return;
            }
            
            System.out.println("\n=== Delete User ===");
            System.out.println("You are about to delete the following user:");
            System.out.println(currentUserViewing);
            System.out.print("Are you sure you want to delete this user? (yes/no): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if (!confirmation.equals("yes")) {
                System.out.println("Deletion cancelled.");
                return;
            }
            
            // Event 1: Delete the user from the archive
            System.out.println("\nDeleting user from archive...");
            boolean deletionSuccessful = archive.deleteUser(currentUserViewing.getId());
            
            if (deletionSuccessful) {
                System.out.println("User deleted successfully.");
                
                // Event 2: Display the list of updated users
                System.out.println("\nUpdated User List:");
                displayUpdatedUsers();
                
                // Postcondition: Connection to the SMOS server interrupted
                serverConnection.interruptConnection();
                
                // Reset current viewing user
                currentUserViewing = null;
            } else {
                System.out.println("Error: Failed to delete user. User may have already been deleted.");
            }
        }
        
        /**
         * Displays the updated list of users after deletion.
         */
        private void displayUpdatedUsers() {
            List<User> activeUsers = archive.getActiveUsers();
            
            if (activeUsers.isEmpty()) {
                System.out.println("No active users in the system.");
            } else {
                System.out.println("Active Users (" + activeUsers.size() + "):");
                for (User user : activeUsers) {
                    System.out.println("  - " + user.getName() + " (ID: " + user.getId() + ")");
                }
            }
        }
        
        /**
         * Main menu for the user management system.
         */
        public void showMainMenu() {
            while (true) {
                System.out.println("\n=== User Management System ===");
                System.out.println("1. Login as Administrator");
                System.out.println("2. View User Details (precondition for deletion)");
                System.out.println("3. Delete Current User");
                System.out.println("4. Display All Users");
                System.out.println("5. Check SMOS Connection Status");
                System.out.println("6. Exit");
                System.out.print("Select an option: ");
                
                try {
                    int choice = Integer.parseInt(scanner.nextLine().trim());
                    
                    switch (choice) {
                        case 1:
                            loginAdministrator();
                            break;
                        case 2:
                            viewUserDetails();
                            break;
                        case 3:
                            executeDeleteUser();
                            break;
                        case 4:
                            displayAllUsers();
                            break;
                        case 5:
                            checkSMOSConnection();
                            break;
                        case 6:
                            System.out.println("Exiting system. Goodbye!");
                            scanner.close();
                            return;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
        }
        
        private void loginAdministrator() {
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Password: ");
            String password = scanner.nextLine().trim();
            
            if (admin.login(username, password)) {
                System.out.println("Login successful! Administrator privileges granted.");
            } else {
                System.out.println("Login failed. Invalid credentials.");
            }
        }
        
        private void displayAllUsers() {
            List<User> allUsers = archive.getAllUsers();
            System.out.println("\n=== All Users in Archive ===");
            for (User user : allUsers) {
                System.out.println(user);
            }
        }
        
        private void checkSMOSConnection() {
            System.out.println("SMOS Server Connection: " + 
                (serverConnection.isConnected() ? "Connected" : "Interrupted"));
        }
        
        /**
         * Cleanup method to close resources.
         */
        public void cleanup() {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
    
    /**
     * Main method to run the DeleteUser use case simulation.
     */
    public static void main(String[] args) {
        System.out.println("=== Delete User Use Case Simulation ===");
        System.out.println("This program simulates the DeleteUser use case as described in the requirements.");
        System.out.println("Follow the menu to execute the use case sequence.\n");
        
        UserManagementSystem system = new UserManagementSystem();
        
        try {
            system.showMainMenu();
        } finally {
            system.cleanup();
        }
    }
}