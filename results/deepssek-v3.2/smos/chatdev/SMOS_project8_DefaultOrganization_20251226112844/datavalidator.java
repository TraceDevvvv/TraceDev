/**
 * Utility class for validating user data such as names and emails.
 * Implements validation rules to ensure data integrity before updates.
 */
public class DataValidator {
    /**
     * Validates a user's name.
     * Rules: Name must be between 2 and 100 characters, and can only contain
     * letters, spaces, hyphens, apostrophes, or periods.
     * @param name The name to validate.
     * @return true if valid, false otherwise.
     */
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        String trimmedName = name.trim();
        if (trimmedName.length() < 2 || trimmedName.length() > 100) {
            return false;
        }
        // Regex: allows letters (including Unicode), spaces, hyphens, apostrophes, periods
        return trimmedName.matches("^[\\p{L} .'-]+$");
    }
    /**
     * Validates a user's email address.
     * Uses a standard regex pattern for basic email format validation.
     * @param email The email to validate.
     * @return true if valid, false otherwise.
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.trim().matches(emailRegex);
    }
}