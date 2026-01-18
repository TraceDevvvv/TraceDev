'''
Utility class for password hashing operations.
'''
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class PasswordHasher {
    /**
     * Hashes a password using SHA-256 for secure storage.
     * @param password The plain text password.
     * @return Hashed password as hexadecimal string.
     * @throws NoSuchAlgorithmException If SHA-256 algorithm is not available.
     */
    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}