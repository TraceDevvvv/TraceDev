import java.util.regex.Pattern;

/**
 * ValidationUtils class provides static methods for validating user input.
 * This class handles validation for email format, phone number, password strength,
 * and other input validation as per the registration requirements.
 */
public class ValidationUtils {
    
    // Regular expression patterns for validation
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String PHONE_REGEX = "^\\+?[0-9]{10,15}$"; // Allows optional + and 10-15 digits
    private static final String USERNAME_REGEX = "^[a-zA-Z0-9_]{3,20}$"; // 3-20 chars, letters, numbers, underscore
    
    // Password requirements
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int MAX_PASSWORD_LENGTH = 30;
    
    /**
     * Validates a User object with all required validations
     * 
     * @param user the User object to validate
     * @return true if all validations pass, false otherwise
     */
    public static boolean validateUser(User user) {
        if (user == null) {
            System.out.println("Error: User object is null");
            return false;
        }
        
        // Check if all required fields are filled
        if (!user.hasRequiredFields()) {
            System.out.println("Error: All fields are required. Please fill in all fields.");
            return false;
        }
        
        // Validate individual fields
        if (!isValidName(user.getName())) {
            System.out.println("Error: Invalid name. Name must be 2-50 characters and contain only letters and spaces.");
            return false;
        }
        
        if (!isValidName(user.getSurname())) {
            System.out.println("Error: Invalid surname. Surname must be 2-50 characters and contain only letters and spaces.");
            return false;
        }
        
        if (!isValidMobilePhone(user.getMobilePhone())) {
            System.out.println("Error: Invalid mobile phone number. Please enter a valid phone number (10-15 digits, optional + prefix).");
            return false;
        }
        
        if (!isValidEmail(user.getEmail())) {
            System.out.println("Error: Invalid email format. Please enter a valid email address.");
            return false;
        }
        
        if (!isValidUsername(user.getUsername())) {
            System.out.println("Error: Invalid username. Username must be 3-20 characters and contain only letters, numbers, and underscores.");
            return false;
        }
        
        if (!isValidPassword(user.getPassword())) {
            System.out.println("Error: Invalid password. Password must be " + MIN_PASSWORD_LENGTH + "-" + 
                             MAX_PASSWORD_LENGTH + " characters long and contain at least one uppercase letter, " +
                             "one lowercase letter, one digit, and one special character.");
            return false;
        }
        
        // Check if passwords match
        if (!user.doPasswordsMatch()) {
            System.out.println("Error: Passwords do not match. Please ensure password and confirmation password are identical.");
            return false;
        }
        
        // All validations passed
        return true;
    }
    
    /**
     * Validates a name (first name or surname)
     * 
     * @param name the name to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        
        String trimmedName = name.trim();
        
        // Name should be between 2 and 50 characters
        if (trimmedName.length() < 2 || trimmedName.length() > 50) {
            return false;
        }
        
        // Name should contain only letters, spaces, hyphens, and apostrophes
        return trimmedName.matches("^[a-zA-Z\\s\\-'\\u00C0-\\u017F]+$");
    }
    
    /**
     * Validates an email address format
     * 
     * @param email the email to validate
     * @return true if valid email format, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        
        String trimmedEmail = email.trim().toLowerCase();
        
        // Check email format using regex
        if (!Pattern.matches(EMAIL_REGEX, trimmedEmail)) {
            return false;
        }
        
        // Additional validation: check for common email providers (optional but good practice)
        String[] validDomains = {".com", ".org", ".net", ".edu", ".gov", ".io", ".co"};
        boolean hasValidDomain = false;
        for (String domain : validDomains) {
            if (trimmedEmail.endsWith(domain)) {
                hasValidDomain = true;
                break;
            }
        }
        
        // Also accept country-specific domains
        if (!hasValidDomain && trimmedEmail.matches(".*\\.[a-z]{2,}$")) {
            hasValidDomain = true;
        }
        
        return hasValidDomain;
    }
    
    /**
     * Validates a mobile phone number
     * 
     * @param phone the phone number to validate
     * @return true if valid phone format, false otherwise
     */
    public static boolean isValidMobilePhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        
        String trimmedPhone = phone.trim();
        
        // Remove any spaces, dashes, or parentheses
        trimmedPhone = trimmedPhone.replaceAll("[\\s\\-\\(\\)]", "");
        
        // Check if it matches the phone regex pattern
        return Pattern.matches(PHONE_REGEX, trimmedPhone);
    }
    
    /**
     * Validates a username
     * 
     * @param username the username to validate
     * @return true if valid username format, false otherwise
     */
    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        
        String trimmedUsername = username.trim();
        
        // Check username format using regex
        return Pattern.matches(USERNAME_REGEX, trimmedUsername);
    }
    
    /**
     * Validates a password for strength requirements
     * 
     * @param password the password to validate
     * @return true if password meets strength requirements, false otherwise
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            return false;
        }
        
        String trimmedPassword = password;
        
        // Check length requirements
        if (trimmedPassword.length() < MIN_PASSWORD_LENGTH || trimmedPassword.length() > MAX_PASSWORD_LENGTH) {
            return false;
        }
        
        // Check for at least one uppercase letter
        if (!Pattern.compile("[A-Z]").matcher(trimmedPassword).find()) {
            return false;
        }
        
        // Check for at least one lowercase letter
        if (!Pattern.compile("[a-z]").matcher(trimmedPassword).find()) {
            return false;
        }
        
        // Check for at least one digit
        if (!Pattern.compile("[0-9]").matcher(trimmedPassword).find()) {
            return false;
        }
        
        // Check for at least one special character
        if (!Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]").matcher(trimmedPassword).find()) {
            return false;
        }
        
        // Check for no whitespace
        if (Pattern.compile("\\s").matcher(trimmedPassword).find()) {
            return false;
        }
        
        // Check for common weak passwords (optional enhancement)
        String[] weakPasswords = {"password", "123456", "qwerty", "admin", "welcome"};
        String lowerPassword = trimmedPassword.toLowerCase();
        for (String weak : weakPasswords) {
            if (lowerPassword.contains(weak)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Validates that two passwords match
     * 
     * @param password the password
     * @param confirmationPassword the confirmation password
     * @return true if passwords match, false otherwise
     */
    public static boolean doPasswordsMatch(String password, String confirmationPassword) {
        if (password == null || confirmationPassword == null) {
            return false;
        }
        return password.equals(confirmationPassword);
    }
    
    /**
     * Sanitizes input by removing leading/trailing whitespace and preventing SQL injection
     * 
     * @param input the input string to sanitize
     * @return sanitized string
     */
    public static String sanitizeInput(String input) {
        if (input == null) {
            return "";
        }
        
        String sanitized = input.trim();
        
        // Remove potential SQL injection patterns (basic prevention)
        String[] sqlKeywords = {"SELECT", "INSERT", "UPDATE", "DELETE", "DROP", "UNION", "OR", "AND"};
        for (String keyword : sqlKeywords) {
            sanitized = sanitized.replaceAll("(?i)" + Pattern.quote(keyword), "");
        }
        
        // Remove special characters that could cause issues
        sanitized = sanitized.replaceAll("[;'\"\\\\]", "");
        
        return sanitized;
    }
    
    /**
     * Checks if a string contains only alphanumeric characters
     * 
     * @param input the string to check
     * @return true if alphanumeric, false otherwise
     */
    public static boolean isAlphanumeric(String input) {
        if (input == null) {
            return false;
        }
        return input.matches("^[a-zA-Z0-9]*$");
    }
    
    /**
     * Checks if a string is within specified length bounds
     * 
     * @param input the string to check
     * @param minLength minimum allowed length
     * @param maxLength maximum allowed length
     * @return true if within bounds, false otherwise
     */
    public static boolean isWithinLength(String input, int minLength, int maxLength) {
        if (input == null) {
            return false;
        }
        int length = input.length();
        return length >= minLength && length <= maxLength;
    }
}