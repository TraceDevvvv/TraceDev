"""
Service class for data validation
Implements validation logic for refreshment point data
"""
import java.util.regex.Pattern;
public class ValidationService {
    /**
     * Validates refreshment point data according to business rules
     * Returns empty string if valid, error message if invalid
     */
    public String validateRefreshmentPointData(String name, String location, 
                                                String capacityStr, String[] refreshments) {
        StringBuilder errors = new StringBuilder();
        // Validate name
        if (name == null || name.trim().isEmpty()) {
            errors.append("Point name is required.\n");
        } else if (name.length() > 100) {
            errors.append("Point name cannot exceed 100 characters.\n");
        }
        // Validate location
        if (location == null || location.trim().isEmpty()) {
            errors.append("Location is required.\n");
        } else if (location.length() > 200) {
            errors.append("Location cannot exceed 200 characters.\n");
        }
        // Validate capacity
        if (capacityStr == null || capacityStr.trim().isEmpty()) {
            errors.append("Capacity is required.\n");
        } else {
            try {
                int capacity = Integer.parseInt(capacityStr);
                if (capacity <= 0) {
                    errors.append("Capacity must be a positive number.\n");
                } else if (capacity > 1000) {
                    errors.append("Capacity cannot exceed 1000.\n");
                }
            } catch (NumberFormatException e) {
                errors.append("Capacity must be a valid integer number.\n");
            }
        }
        // Validate refreshments
        if (refreshments == null || refreshments.length == 0) {
            errors.append("At least one refreshment is required.\n");
        } else {
            for (int i = 0; i < refreshments.length; i++) {
                String item = refreshments[i].trim();
                if (item.isEmpty()) {
                    errors.append("Refreshment item ").append(i + 1).append(" cannot be empty.\n");
                } else if (item.length() > 50) {
                    errors.append("Refreshment '").append(item)
                          .append("' exceeds 50 characters.\n");
                }
            }
        }
        return errors.toString();
    }
    /**
     * Validates if a string contains only alphanumeric characters and spaces
     */
    private boolean isValidAlphanumeric(String str) {
        if (str == null) return false;
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9 ]+$");
        return pattern.matcher(str).matches();
    }
}