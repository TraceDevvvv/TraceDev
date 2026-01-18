/**
 * Handles user authentication for administrators
 * Simulates authentication against a user database
 */
public class UserAuthentication {
    /**
     * Authenticates a user as an administrator
     * @param username The username
     * @param password The password
     * @return true if authentication successful, false otherwise
     */
    public boolean login(String username, String password) {
        // In a real system, this would validate against a database
        // For this example, we'll use a simple hardcoded admin
        // Check for valid administrator credentials
        if (username == null || password == null) {
            return false;
        }
        // Simulate admin validation (in real app, this would be from database)
        boolean isValidAdmin = "admin".equals(username.trim()) && 
                              "admin123".equals(password);
        if (isValidAdmin) {
            System.out.println("Administrator '" + username + "' logged in successfully");
            return true;
        }
        System.out.println("Login failed for user: " + username);
        return false;
    }
}