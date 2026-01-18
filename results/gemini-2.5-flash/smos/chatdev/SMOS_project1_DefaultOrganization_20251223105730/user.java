'''
Represents a simple User entity with a username and password.
This class is used by the AuthService to store and compare user credentials.
'''
import java.util.Arrays; // Import Arrays for char[] comparison and copying
public class User {
    private String username;
    private char[] password; // Change password to char array for security
    /**
     * Constructs a new User object.
     *
     * @param username The username for the user.
     * @param password The password for the user as a char array.
     */
    public User(String username, char[] password) {
        this.username = username;
        // Copy the char array to prevent external modifications to the internal array
        this.password = Arrays.copyOf(password, password.length);
    }
    /**
     * Gets the username of the user.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }
    /**
     * Gets a copy of the password of the user as a char array.
     * It returns a copy to prevent direct external modification of the internal password array.
     *
     * @return A copy of the password as a char array.
     */
    public char[] getPasswordChars() {
        return Arrays.copyOf(password, password.length);
    }
    /**
     * Overrides the equals method to compare User objects based on their username and password.
     * Sensitive password comparison is done using Arrays.equals for char arrays.
     *
     * @param obj The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        // Compare char arrays using Arrays.equals for content comparison
        return username.equals(user.username) && Arrays.equals(password, user.password);
    }
    /**
     * Overrides the hashCode method consistent with the equals method.
     * Uses Arrays.hashCode for char array to generate hash code.
     *
     * @return The hash code for this User object.
     */
    @Override
    public int hashCode() {
        int result = username.hashCode();
        // Use Arrays.hashCode for char array to generate hash code
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }
    /**
     * Clears the password char array from memory when the user object is no longer needed.
     * This is a security measure to minimize the time sensitive data resides in memory.
     */
    public void clearPassword() {
        if (this.password != null) {
            Arrays.fill(this.password, ' '); // Overwrite with blank characters
            this.password = null; // Dereference the array
        }
    }
}