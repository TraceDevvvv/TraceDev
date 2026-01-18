/**
 * Utility class for input validation and data verification
 */
import java.util.regex.Pattern;
public class ValidationUtils {
    private ValidationUtils() {
        // Utility class - prevent instantiation
    }
    /**
     * Validates if a string contains only alphanumeric characters and basic punctuation
     * @param input the string to validate
     * @param fieldName name of the field for error messages
     * @return true if valid
     * @throws IllegalArgumentException if validation fails
     */
    public static boolean validateText(String input, String fieldName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty");
        }
        // Basic sanitization - allow letters, numbers, spaces, and common punctuation
        Pattern pattern = Pattern.compile("^[\\p{L}\\p{N}\\s.,;:'\"()-]+$");
        if (!pattern.matcher(input).matches()) {
            throw new IllegalArgumentException(fieldName + " contains invalid characters");
        }
        // Check for maximum length (prevent extremely long inputs)
        if (input.length() > 500) {
            throw new IllegalArgumentException(fieldName + " is too long (maximum 500 characters)");
        }
        return true;
    }
    /**
     * Validates if a string is a valid ID format (alphanumeric with optional dashes)
     * @param id the ID to validate
     * @return true if valid
     * @throws IllegalArgumentException if validation fails
     */
    public static boolean validateId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty");
        }
        Pattern pattern = Pattern.compile("^[A-Za-z0-9-]{1,50}$");
        if (!pattern.matcher(id).matches()) {
            throw new IllegalArgumentException("ID can only contain letters, numbers, and dashes (1-50 characters)");
        }
        return true;
    }
    /**
     * Validates if a year is reasonable for cultural heritage
     * @param year the year to validate
     * @return true if valid
     * @throws IllegalArgumentException if validation fails
     */
    public static boolean validateYear(int year) {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        if (year < 0) {
            throw new IllegalArgumentException("Year cannot be negative");
        }
        if (year > currentYear) {
            throw new IllegalArgumentException("Year cannot be in the future");
        }
        // Most cultural heritage is not older than 10000 years
        if (year < currentYear - 10000) {
            throw new IllegalArgumentException("Year is too far in the past");
        }
        return true;
    }
    /**
     * Validates a complete CulturalHeritage object
     * @param heritage the object to validate
     * @return true if all validations pass
     * @throws IllegalArgumentException if any validation fails
     */
    public static boolean validateCulturalHeritage(CulturalHeritage heritage) {
        if (heritage == null) {
            throw new IllegalArgumentException("Cultural heritage cannot be null");
        }
        validateId(heritage.getId());
        validateText(heritage.getName(), "Name");
        validateText(heritage.getType(), "Type");
        validateText(heritage.getLocation(), "Location");
        validateYear(heritage.getYear());
        // Description is optional, but if provided, validate it
        if (!heritage.getDescription().isEmpty()) {
            validateText(heritage.getDescription(), "Description");
        }
        return true;
    }
    /**
     * Validates if a string can be parsed as an integer year
     * @param yearStr the string to validate
     * @return the parsed integer year if valid
     * @throws IllegalArgumentException if validation fails
     */
    public static int validateAndParseYear(String yearStr) {
        if (yearStr == null || yearStr.trim().isEmpty()) {
            throw new IllegalArgumentException("Year cannot be empty");
        }
        try {
            int year = Integer.parseInt(yearStr.trim());
            validateYear(year);
            return year;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Year must be a valid integer");
        }
    }
}