package presentation.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Presentation Layer DTO: Data Transfer Object for registration operation results.
 * Carries the outcome of a registration attempt back to the UI.
 * Added to satisfy recommendation 5.
 */
public class RegistrationResultDTO {
    public Boolean success;
    public String message;
    public Map<String, String> errors; // Field-specific error messages

    /**
     * Constructor for a successful registration result.
     *
     * @param success Boolean indicating success (should be true).
     * @param message A message describing the successful outcome.
     */
    public RegistrationResultDTO(Boolean success, String message) {
        this(success, message, Collections.emptyMap());
    }

    /**
     * Constructor for a registration result, potentially with errors.
     *
     * @param success Boolean indicating success or failure.
     * @param message A general message about the outcome.
     * @param errors A map of field names to their specific error messages.
     */
    public RegistrationResultDTO(Boolean success, String message, Map<String, String> errors) {
        this.success = success;
        this.message = message;
        this.errors = errors != null ? new HashMap<>(errors) : new HashMap<>();
    }

    // Getters for all fields
    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getErrors() {
        return Collections.unmodifiableMap(errors); // Return unmodifiable map
    }
}