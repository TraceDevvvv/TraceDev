/**
 * ValidationResult class
 * A simple data class to hold the result of form validation.
 * It indicates whether the data is valid and, if not, an error message.
 */
public class ValidationResult {
    public boolean isValid;
    public String errorMessage;
    public ValidationResult(boolean isValid, String errorMessage) {
        this.isValid = isValid;
        this.errorMessage = errorMessage;
    }
    public boolean isValid() {
        return isValid;
    }
    public String getErrorMessage() {
        return errorMessage;
    }
}