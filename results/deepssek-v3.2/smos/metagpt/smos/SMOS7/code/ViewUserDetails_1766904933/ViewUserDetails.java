import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This program implements the ViewUserDetails use case for an Administrator.
 * It simulates viewing detailed information about a user after the administrator
 * has logged in and selected a user from the list.
 * 
 * The program includes:
 * 1. User class to represent user data
 * 2. UserService class to manage user data and simulate server connection
 * 3. Main class to run the program and handle user interaction
 * 
 * Postcondition: Connection to the SMOS server is interrupted after displaying details.
 */
public class ViewUserDetails {
    
    /**
     * Represents a user in the system with all required details.
     */
    static class User {
        private String name;
        private String surname;
        private String email;
        private String cell;
        private String login;
        private String password;
        
        public User(String name, String surname, String email, String cell, String login, String password) {
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.cell = cell;
            this.login = login;
            this.password = password;
        }
        
        // Getters for all fields
        public String getName() { return name; }
        public String getSurname() { return surname; }
        public String getEmail() { return email; }
        public String getCell() { return cell; }
        public String getLogin() { return login; }
        public String getPassword() { return password; }
        
        /**
         * Returns a formatted string with all user details.
         */
        public String getDetails() {
            return String.format(
                "Name: %s\nSurname: %s\nE-mail: %s\nCell: %s\nLogin: %s\nPassword: %s",
                name, surname, email, cell, login, password
            );
        }
    }
    
    /**
     * Service class to manage users and simulate server operations.
     */
    static class UserService {
        private List<User> users;
        private boolean serverConnected;
        
        public UserService() {
            this.users = new ArrayList<>();
            this.serverConnected = true; // Initially connected
            
            // Initialize with some sample users for demonstration
            initializeSampleUsers();
        }
        
        /**
         * Creates sample users for demonstration purposes.
         */
        private void initializeSampleUsers() {
            users.add(new User("John", "Doe", "john.doe@example.com", "123-456-7890", "johndoe", "password123"));
            users.add(new User("Jane", "Smith", "jane.smith@example.com", "987-654-3210", "janesmith", "securepass"));
            users.add(new User("Admin", "User", "admin@system.com", "555-123-4567", "admin", "admin123"));
        }
        
        /**
         * Simulates viewing the list of users (ViewingLanciutenti use case).
         * Returns a list of user logins for selection.
         */
        public List<String> getUsersList() {
            if (!serverConnected) {
                throw new IllegalStateException("Cannot get users list: SMOS server connection interrupted");
            }
            
            List<String> userList = new ArrayList<>();
            for (User user : users) {
                userList.add(user.getLogin());
            }
            return userList;
        }
        
        /**
         * Gets detailed information for a specific user by login.
         * Simulates the "Details" button click.
         * 
         * @param login The login of the user to view
         * @return User object with all details
         * @throws IllegalArgumentException if user not found
         */
        public User getUserDetails(String login) {
            if (!serverConnected) {
                throw new IllegalStateException("Cannot get user details: SMOS server connection interrupted");
            }
            
            for (User user : users) {
                if (user.getLogin().equals(login)) {
                    return user;
                }
            }
            
            throw new IllegalArgumentException("User with login '" + login + "' not found");
        }
        
        /**
         * Simulates the SMOS server connection interruption as per postconditions.
         */
        public void interruptServerConnection() {
            this.serverConnected = false;
            System.out.println("SMOS server connection interrupted.");
        }
        
        /**
         * Checks if the server is currently connected.
         */
        public boolean isServerConnected() {
            return serverConnected;
        }
    }
    
    /**
     * Main class to run the ViewUserDetails use case simulation.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        
        System.out.println("=== View User Details Use Case ===");
        System.out.println("Precondition: Administrator is logged in");
        
        try {
            // Simulate the "ViewingLanciutenti" use case - viewing list of users
            System.out.println("\nViewing list of users in the system...");
            List<String> usersList = userService.getUsersList();
            
            System.out.println("\nAvailable users:");
            for (int i = 0; i < usersList.size(); i++) {
                System.out.println((i + 1) + ". " + usersList.get(i));
            }
            
            // Simulate user clicking on "Details" button
            System.out.print("\nEnter the number of the user to view details (1-" + usersList.size() + "): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            // Validate user choice
            if (choice < 1 || choice > usersList.size()) {
                System.out.println("Invalid choice. Please run the program again.");
                return;
            }
            
            String selectedLogin = usersList.get(choice - 1);
            
            // Get and display user details
            System.out.println("\n=== User Details ===");
            User selectedUser = userService.getUserDetails(selectedLogin);
            System.out.println(selectedUser.getDetails());
            
            // Postcondition: Interrupt SMOS server connection
            System.out.println("\n=== Postcondition ===");
            userService.interruptServerConnection();
            
            // Verify server is disconnected
            if (!userService.isServerConnected()) {
                System.out.println("SMOS server connection successfully interrupted as per postconditions.");
            }
            
            // Demonstrate that further operations would fail
            System.out.println("\n=== Verification ===");
            try {
                userService.getUsersList();
                System.out.println("ERROR: Server should be disconnected!");
            } catch (IllegalStateException e) {
                System.out.println("Expected error: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
            System.out.println("\nProgram completed.");
        }
    }
}