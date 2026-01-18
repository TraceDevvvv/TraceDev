/**
 * Utility class for validating input data, specific to teaching details.
 */
public class Validator {
    /**
     * Checks if a string value is not null and not empty.
     * Appends an error message to the errors StringBuilder if validation fails.
     * @param value The string value to validate.
     * @param fieldName The name of the field for error reporting.
     * @param errors A StringBuilder to accumulate error messages.
     * @return True if the string is valid, false otherwise.
     */
    public static boolean isValidString(String value, String fieldName, StringBuilder errors) {
        if (value == null || value.trim().isEmpty()) {
            errors.append(fieldName).append(" cannot be empty.\n");
            return false;
        }
        return true;
    }
    /**
     * Checks if a string value can be parsed as a positive integer.
     * Appends an error message to the errors StringBuilder if validation fails.
     * @param value The string value to validate.
     * @param fieldName The name of the field for error reporting.
     * @param errors A StringBuilder to accumulate error messages.
     * @return True if the string is a valid positive integer, false otherwise.
     */
    public static boolean isValidInteger(String value, String fieldName, StringBuilder errors) {
        if (!isValidString(value, fieldName, errors)) {
            return false; // Already reported as empty
        }
        try {
            int intValue = Integer.parseInt(value);
            if (intValue <= 0) {
                errors.append(fieldName).append(" must be a positive number.\n");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            errors.append(fieldName).append(" must be a valid number.\n");
            return false;
        }
    }
    /**
     * Performs comprehensive validation for teaching details.
     * @param name The name of the teaching.
     * @param description The description of the teaching.
     * @param instructor The instructor's name.
     * @param creditsStr The credits as a string.
     * @return A string containing all error messages, or null if all data is valid.
     */
    public static String validateTeaching(String name, String description, String instructor, String creditsStr) {
        StringBuilder errors = new StringBuilder();
        // Validate name
        isValidString(name, "Name", errors);
        // Validate description
        isValidString(description, "Description", errors);
        // Validate instructor
        isValidString(instructor, "Instructor", errors);
        // Validate credits
        isValidInteger(creditsStr, "Credits", errors);
        return errors.isEmpty() ? null : errors.toString();
    }
}