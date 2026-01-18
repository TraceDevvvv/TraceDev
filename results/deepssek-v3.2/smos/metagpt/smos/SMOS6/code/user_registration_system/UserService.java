import java.util.*;
import java.io.*;

/**
 * UserService class that handles CRUD operations for users, validation, and archiving.
 * This class manages the user repository and provides business logic for user operations.
 */
public class UserService {
    // In-memory storage for users (simulating database/archive)
    private final Map<String, User> usersArchive;
    private static final String ARCHIVE_FILE = "users_archive.txt";
    
    /**
     * Constructor initializes the user archive
     */
    public UserService() {
        usersArchive = new HashMap<>();
        loadUsersFromArchive(); // Load existing users from archive file if exists
    }
    
    /**
     * Creates a new user after validation
     * @param user the user object to create
     * @param confirmPassword the confirmed password for validation
     * @return true if user was successfully created, false otherwise
     * @throws IllegalArgumentException if validation fails
     */
    public boolean createUser(User user, String confirmPassword) throws IllegalArgumentException {
        // Validate user data
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        
        // Check if all fields are valid
        if (!user.validate()) {
            String validationErrors = user.getValidationErrors();
            throw new IllegalArgumentException("Validation failed:\n" + validationErrors);
        }
        
        // Check if passwords match
        if (!user.confirmPassword(confirmPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        
        // Check if login already exists (must be unique)
        if (usersArchive.containsKey(user.getLogin())) {
            throw new IllegalArgumentException("Login '" + user.getLogin() + "' already exists");
        }
        
        // Check if email already exists (should be unique)
        for (User existingUser : usersArchive.values()) {
            if (existingUser.getEmail().equalsIgnoreCase(user.getEmail())) {
                throw new IllegalArgumentException("Email '" + user.getEmail() + "' is already registered");
            }
        }
        
        // Add user to archive
        usersArchive.put(user.getLogin(), user);
        
        // Save to persistent archive
        saveUsersToArchive();
        
        return true;
    }
    
    /**
     * Retrieves a user by login
     * @param login the user's login identifier
     * @return the User object if found, null otherwise
     */
    public User getUser(String login) {
        return usersArchive.get(login);
    }
    
    /**
     * Retrieves all users in the system
     * @return list of all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(usersArchive.values());
    }
    
    /**
     * Updates an existing user
     * @param login the login of user to update
     * @param updatedUser the updated user object
     * @return true if update was successful, false if user not found
     * @throws IllegalArgumentException if validation fails
     */
    public boolean updateUser(String login, User updatedUser) throws IllegalArgumentException {
        if (!usersArchive.containsKey(login)) {
            return false;
        }
        
        // Validate updated user data
        if (!updatedUser.validate()) {
            String validationErrors = updatedUser.getValidationErrors();
            throw new IllegalArgumentException("Validation failed:\n" + validationErrors);
        }
        
        // Check if email is being changed to one that already exists (excluding current user)
        User currentUser = usersArchive.get(login);
        if (!currentUser.getEmail().equalsIgnoreCase(updatedUser.getEmail())) {
            for (User existingUser : usersArchive.values()) {
                if (!existingUser.getLogin().equals(login) && 
                    existingUser.getEmail().equalsIgnoreCase(updatedUser.getEmail())) {
                    throw new IllegalArgumentException("Email '" + updatedUser.getEmail() + "' is already registered by another user");
                }
            }
        }
        
        // Update user in archive
        usersArchive.put(login, updatedUser);
        
        // Save to persistent archive
        saveUsersToArchive();
        
        return true;
    }
    
    /**
     * Deletes a user from the system
     * @param login the login of user to delete
     * @return true if deletion was successful, false if user not found
     */
    public boolean deleteUser(String login) {
        if (!usersArchive.containsKey(login)) {
            return false;
        }
        
        usersArchive.remove(login);
        
        // Save to persistent archive
        saveUsersToArchive();
        
        return true;
    }
    
    /**
     * Checks if a user exists by login
     * @param login the login to check
     * @return true if user exists, false otherwise
     */
    public boolean userExists(String login) {
        return usersArchive.containsKey(login);
    }
    
    /**
     * Validates user credentials for login
     * @param login the login identifier
     * @param password the password to validate
     * @return true if credentials are valid, false otherwise
     */
    public boolean validateCredentials(String login, String password) {
        User user = usersArchive.get(login);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }
    
    /**
     * Saves all users to file archive (simulating database persistence)
     * This handles the "enter the new user in the archive" requirement
     */
    private void saveUsersToArchive() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVE_FILE))) {
            for (User user : usersArchive.values()) {
                // Note: In a real system, you would NEVER store passwords in plain text
                // This is for demonstration only. Real system would use hashed passwords.
                writer.println(user.getName() + "," + 
                              user.getSurname() + "," + 
                              user.getEmail() + "," + 
                              user.getCell() + "," + 
                              user.getLogin() + "," + 
                              user.getPassword());
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not save users to archive file: " + e.getMessage());
        }
    }
    
    /**
     * Loads users from file archive (simulating database persistence)
     */
    private void loadUsersFromArchive() {
        File archiveFile = new File(ARCHIVE_FILE);
        if (!archiveFile.exists()) {
            return; // No archive file exists yet
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    User user = new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
                    usersArchive.put(user.getLogin(), user);
                }
            }
        } catch (IOException e) {
            System.err.println("Warning: Could not load users from archive file: " + e.getMessage());
        }
    }
    
    /**
     * Simulates the "viewing the list of users" functionality
     * @return formatted string containing all users
     */
    public String viewAllUsersFormatted() {
        if (usersArchive.isEmpty()) {
            return "No users in the system.";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("======= LIST OF USERS =======\n");
        int count = 1;
        for (User user : usersArchive.values()) {
            sb.append(count++).append(". ")
              .append("Login: ").append(user.getLogin())
              .append(", Name: ").append(user.getName())
              .append(" ").append(user.getSurname())
              .append(", Email: ").append(user.getEmail())
              .append(", Cell: ").append(user.getCell())
              .append("\n");
        }
        sb.append("=============================");
        return sb.toString();
    }
    
    /**
     * Checks if the system has any users
     * @return true if there are users in the system, false otherwise
     */
    public boolean hasUsers() {
        return !usersArchive.isEmpty();
    }
    
    /**
     * Clears all users from the archive (for testing purposes)
     * Note: Use with caution - this will delete all user data
     */
    public void clearArchive() {
        usersArchive.clear();
        File archiveFile = new File(ARCHIVE_FILE);
        if (archiveFile.exists()) {
            archiveFile.delete();
        }
    }
}