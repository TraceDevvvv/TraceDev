/**
 * Data class representing user credentials for authentication.
 * Added to satisfy requirement REQ-001.
 */
public class Credentials {
    public String username; // The username for authentication
    public String password; // The password for authentication

    /**
     * Constructor for Credentials.
     * @param username The username.
     * @param password The password.
     */
    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credentials{" +
               "username='" + username + '\'' +
               ", password='[PROTECTED]'" +
               '}';
    }
}