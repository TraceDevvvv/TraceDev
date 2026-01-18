'''
Authentication Service for validating agency operator credentials.
This service handles the login verification as required by the use case.
'''
public class AuthenticationService {
    // Use eager initialization for thread safety
    private static final AuthenticationService instance = new AuthenticationService();
    // In a real application, these would be stored securely in a database
    private static final String VALID_USERNAME = "agency_operator";
    private static final String VALID_PASSWORD = "secure_pass_123";
    private AuthenticationService() {
        // Private constructor for singleton
    }
    /**
     * Get the singleton instance of AuthenticationService.
     * @return the singleton instance
     */
    public static AuthenticationService getInstance() {
        return instance;
    }
    /**
     * Authenticate an agency operator.
     * @param username the username to validate
     * @param password the password to validate
     * @return true if credentials are valid, false otherwise
     */
    public boolean authenticate(String username, String password) {
        // In production, this would query a secure database
        // For this demo, we use hardcoded credentials
        return VALID_USERNAME.equals(username) && 
               VALID_PASSWORD.equals(password);
    }
}