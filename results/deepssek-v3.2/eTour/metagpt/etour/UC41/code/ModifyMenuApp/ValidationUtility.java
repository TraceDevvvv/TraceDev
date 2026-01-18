import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * ValidationUtility.java
 * Utility class for validating menu data and user inputs.
 * Contains methods for menu validation, input validation, and data integrity checks.
 * This class follows the Single Responsibility Principle by focusing only on validation logic.
 */
public class ValidationUtility {
    
    // Constants for validation rules
    private static final int MAX_ITEM_LENGTH = 100;
    private static final int MIN_ITEM_LENGTH = 1;
    private static final int MAX_SPECIAL_NOTES_LENGTH = 500;
    private static final Pattern INVALID_CHAR_PATTERN = Pattern.compile("[@|]{2,}");
    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
        "(?i)(SELECT|INSERT|UPDATE|DELETE|DROP|UNION|OR|AND|WHERE|FROM|JOIN|EXEC|TRUNCATE|ALTER|CREATE)"
    );
    
    /**
     * Validates a complete DailyMenu object.
     * Implements business rules for menu validation.
     * 
     * @param menu The DailyMenu to validate
     * @return ValidationResult containing validation status and error messages
     */
    public static ValidationResult validateMenu(DailyMenu menu) {
        ValidationResult result = new ValidationResult();
        
        if (menu == null) {
            result.addError("Menu cannot be null");
            return result;
        }
        
        // Validate day of week
        if (!isValidDayOfWeek(menu.getDayOfWeek())) {
            result.addError("Invalid day of week: " + menu.getDayOfWeek());
        }
        
        // Validate all menu items
        validateMenuItems(menu.getAppetizers(), "Appetizers", result);
        validateMenuItems(menu.getMainCourses(), "Main Courses", result);
        validateMenuItems(menu.getDesserts(), "Desserts", result);
        validateMenuItems(menu.getBeverages(), "Beverages", result);
        
        // Validate special notes
        if (menu.getSpecialNotes() != null && !menu.getSpecialNotes().isEmpty()) {
            if (menu.getSpecialNotes().length() > MAX_SPECIAL_NOTES_LENGTH) {
                result.addError("Special notes exceed maximum length of " + MAX_SPECIAL_NOTES_LENGTH + " characters");
            }
            if (containsInvalidCharacters(menu.getSpecialNotes())) {
                result.addError("Special notes contain invalid characters");
            }
        }
        
        // Business rule: Menu must contain at least one item
        if (menu.getAllItems().isEmpty()) {
            result.addError("Menu must contain at least one item");
        }
        
        // Business rule: Warning if no main courses (but not an error)
        if (menu.getMainCourses().isEmpty()) {
            result.addWarning("No main courses specified");
        }
        
        return result;
    }
    
    /**
     * Validates a list of menu items.
     * 
     * @param items List of menu items to validate
     * @param categoryName Name of the category for error messages
     * @param result ValidationResult to accumulate errors
     */
    private static void validateMenuItems(List<String> items, String categoryName, ValidationResult result) {
        if (items == null) {
            result.addError(categoryName + " list cannot be null");
            return;
        }
        
        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            if (item == null) {
                result.addError(categoryName + " item at position " + (i + 1) + " cannot be null");
                continue;
            }
            
            String trimmedItem = item.trim();
            
            // Check for empty items
            if (trimmedItem.isEmpty()) {
                result.addError(categoryName + " item at position " + (i + 1) + " is empty");
                continue;
            }
            
            // Check minimum length
            if (trimmedItem.length() < MIN_ITEM_LENGTH) {
                result.addError(categoryName + " item at position " + (i + 1) + " is too short");
            }
            
            // Check maximum length
            if (trimmedItem.length() > MAX_ITEM_LENGTH) {
                result.addError(categoryName + " item '" + getTruncatedString(trimmedItem, 20) + 
                              "' exceeds maximum length of " + MAX_ITEM_LENGTH + " characters");
            }
            
            // Check for invalid characters
            if (containsInvalidCharacters(trimmedItem)) {
                result.addError(categoryName + " item '" + getTruncatedString(trimmedItem, 20) + 
                              "' contains invalid characters");
            }
            
            // Check for potential SQL injection
            if (containsSqlInjection(trimmedItem)) {
                result.addError(categoryName + " item '" + getTruncatedString(trimmedItem, 20) + 
                              "' contains potentially dangerous content");
            }
        }
    }
    
    /**
     * Validates if a string represents a valid day of the week.
     * 
     * @param day Day string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidDayOfWeek(String day) {
        if (day == null || day.trim().isEmpty()) {
            return false;
        }
        
        String[] validDays = {
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday",
            "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"
        };
        
        for (String validDay : validDays) {
            if (validDay.equals(day.trim())) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Validates operator ID format.
     * 
     * @param operatorId Operator ID to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidOperatorId(String operatorId) {
        if (operatorId == null || operatorId.trim().isEmpty()) {
            return false;
        }
        
        // Operator ID should be 3-10 characters, alphanumeric
        String trimmedId = operatorId.trim();
        if (trimmedId.length() < 3 || trimmedId.length() > 10) {
            return false;
        }
        
        // Check for alphanumeric characters only
        for (char c : trimmedId.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Validates password strength.
     * 
     * @param password Password to validate
     * @return true if meets minimum requirements, false otherwise
     */
    public static boolean isValidPassword(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        
        // Check for at least one letter and one number
        boolean hasLetter = false;
        boolean hasDigit = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
            
            if (hasLetter && hasDigit) {
                break;
            }
        }
        
        return hasLetter && hasDigit;
    }
    
    /**
     * Validates if input is a valid integer within specified range.
     * 
     * @param input String input to validate
     * @param min Minimum allowed value
     * @param max Maximum allowed value
     * @return true if valid integer within range, false otherwise
     */
    public static boolean isValidIntegerInRange(String input, int min, int max) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        
        try {
            int value = Integer.parseInt(input.trim());
            return value >= min && value <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Validates if a string contains invalid characters.
     * 
     * @param input String to check
     * @return true if contains invalid characters, false otherwise
     */
    public static boolean containsInvalidCharacters(String input) {
        if (input == null) {
            return false;
        }
        
        Matcher matcher = INVALID_CHAR_PATTERN.matcher(input);
        return matcher.find();
    }
    
    /**
     * Checks for potential SQL injection patterns.
     * 
     * @param input String to check
     * @return true if potential SQL injection detected, false otherwise
     */
    public static boolean containsSqlInjection(String input) {
        if (input == null) {
            return false;
        }
        
        Matcher matcher = SQL_INJECTION_PATTERN.matcher(input);
        return matcher.find();
    }
    
    /**
     * Truncates a string for display in error messages.
     * 
     * @param input String to truncate
     * @param maxLength Maximum length for truncated string
     * @return Truncated string with "..." if longer than maxLength
     */
    private static String getTruncatedString(String input, int maxLength) {
        if (input == null) {
            return "";
        }
        
        if (input.length() <= maxLength) {
            return input;
        }
        
        return input.substring(0, maxLength) + "...";
    }
    
    /**
     * Validates that a list is not null and not empty.
     * 
     * @param list List to validate
     * @param listName Name of the list for error messages
     * @return true if valid, false otherwise
     */
    public static boolean isListValid(List<?> list, String listName) {
        if (list == null) {
            throw new IllegalArgumentException(listName + " cannot be null");
        }
        return !list.isEmpty();
    }
    
    /**
     * Validates that a string is not null or empty.
     * 
     * @param input String to validate
     * @param fieldName Name of the field for error messages
     * @return true if valid, false otherwise
     */
    public static boolean isStringValid(String input, String fieldName) {
        if (input == null) {
            throw new IllegalArgumentException(fieldName + " cannot be null");
        }
        return !input.trim().isEmpty();
    }
    
    /**
     * Inner class to represent validation results.
     * Contains both errors and warnings.
     */
    public static class ValidationResult {
        private StringBuilder errors;
        private StringBuilder warnings;
        private boolean isValid;
        
        public ValidationResult() {
            this.errors = new StringBuilder();
            this.warnings = new StringBuilder();
            this.isValid = true;
        }
        
        /**
         * Adds an error message to the validation result.
         * 
         * @param error Error message
         */
        public void addError(String error) {
            if (errors.length() > 0) {
                errors.append("\n");
            }
            errors.append("✗ ").append(error);
            isValid = false;
        }
        
        /**
         * Adds a warning message to the validation result.
         * 
         * @param warning Warning message
         */
        public void addWarning(String warning) {
            if (warnings.length() > 0) {
                warnings.append("\n");
            }
            warnings.append("⚠ ").append(warning);
        }
        
        /**
         * Checks if validation passed without errors.
         * 
         * @return true if no errors, false otherwise
         */
        public boolean isValid() {
            return isValid;
        }
        
        /**
         * Gets all error messages.
         * 
         * @return Formatted error messages
         */
        public String getErrors() {
            return errors.toString();
        }
        
        /**
         * Gets all warning messages.
         * 
         * @return Formatted warning messages
         */
        public String getWarnings() {
            return warnings.toString();
        }
        
        /**
         * Gets a combined report of errors and warnings.
         * 
         * @return Complete validation report
         */
        public String getReport() {
            StringBuilder report = new StringBuilder();
            
            if (errors.length() > 0) {
                report.append("=== VALIDATION ERRORS ===\n");
                report.append(errors).append("\n");
            }
            
            if (warnings.length() > 0) {
                if (report.length() > 0) {
                    report.append("\n");
                }
                report.append("=== VALIDATION WARNINGS ===\n");
                report.append(warnings).append("\n");
            }
            
            if (isValid) {
                if (report.length() > 0) {
                    report.append("\n");
                }
                report.append("✓ Validation passed successfully.");
            }
            
            return report.toString();
        }
        
        /**
         * Clears all validation results.
         */
        public void clear() {
            errors = new StringBuilder();
            warnings = new StringBuilder();
            isValid = true;
        }
    }
}