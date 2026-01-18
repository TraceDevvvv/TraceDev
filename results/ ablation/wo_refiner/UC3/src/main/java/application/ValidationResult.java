package application;

import java.util.List;

/**
 * Result of validation, containing success flag and error messages.
 */
public class ValidationResult {
    public final boolean isValid;
    public final List<String> errorMessages;

    public ValidationResult(boolean isValid, List<String> errorMessages) {
        this.isValid = isValid;
        this.errorMessages = errorMessages;
    }
}