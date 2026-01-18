

package com.example.usecase;

import com.example.model.CulturalHeritage;
import com.example.repository.CulturalHeritageRepositoryInterface;
import java.util.Map;

/**
 * Use Case for modifying Cultural Heritage.
 * Maps to the ModifyCulturalHeritageUseCase class in the UML diagram.
 */
public class ModifyCulturalHeritageUseCase {
    private CulturalHeritageRepositoryInterface repository;
    private boolean isProcessing;
    private long lastRequestTime;

    public ModifyCulturalHeritageUseCase(CulturalHeritageRepositoryInterface repository) {
        this.repository = repository;
        this.isProcessing = false;
        this.lastRequestTime = 0;
    }

    /**
     * Loads a CulturalHeritage by id as per sequence diagram.
     */
    public CulturalHeritage loadCulturalHeritage(String id) {
        return repository.findById(id);
    }

    /**
     * Validates the updated data.
     * Returns a ValidationResult as per sequence diagram.
     */
    public ValidationResult validateData(Map<String, Object> data) {
        // Simplified validation: check that required fields are present and non-empty
        boolean isValid = true;
        java.util.List<String> errors = new java.util.ArrayList<>();

        if (data.containsKey("title")) {
            Object title = data.get("title");
            if (title == null || !(title instanceof String) || ((String) title).trim().isEmpty()) {
                isValid = false;
                errors.add("Title must be a non-empty string");
            }
        }
        // Add more validation as needed

        return new ValidationResult(isValid, errors);
    }

    /**
     * Requests confirmation for the update.
     * In a real system, this might interact with the UI.
     * For this implementation, we simulate by returning true (confirmation granted).
     */
    public boolean requestConfirmation() {
        // Simulate confirmation request - in reality would involve UI interaction
        return true;
    }

    /**
     * Updates the Cultural Heritage as per sequence diagram.
     * Implements the flow including validation, confirmation, and update.
     */
    public Result updateCulturalHeritage(String id, Map<String, Object> updatedData) {
        // Step 1: block multiple submissions (as per sequence diagram alt block)
        if (blockMultipleSubmissions()) {
            return new Result(false, "Multiple submission blocked", null);
        }

        // Step 2: validate data
        ValidationResult validationResult = validateData(updatedData);
        if (!validationResult.isValid()) {
            return new Result(false, "Validation failed", null);
        }

        // Step 3: request confirmation (simulated)
        if (!requestConfirmation()) {
            return new Result(false, "Operation cancelled by user", null);
        }

        // Step 4: load current cultural heritage
        CulturalHeritage culturalHeritage = repository.findById(id);
        if (culturalHeritage == null) {
            return new Result(false, "Cultural heritage not found", null);
        }

        // Step 5: update the cultural heritage object
        culturalHeritage.update(updatedData);

        // Step 6: persist update via repository
        try {
            repository.update(culturalHeritage);
        } catch (Exception e) {
            // Simulate connection interruption check (as per sequence diagram alt connection interrupted)
            return new Result(false, "Connection interrupted", null);
        }

        // Step 7: return success result
        return new Result(true, "Update successful", culturalHeritage);
    }

    /**
     * Blocks multiple submissions within a short time window.
     * Returns true if submission should be blocked.
     */
    boolean blockMultipleSubmissions() {
        long currentTime = System.currentTimeMillis();
        // Block if still processing or request within last 2 seconds
        if (isProcessing || (currentTime - lastRequestTime < 2000)) {
            return true;
        }
        isProcessing = true;
        lastRequestTime = currentTime;
        // Reset processing after a delay (simulated)
        new java.util.Timer().schedule(
            new java.util.TimerTask() {
                @Override
                public void run() {
                    isProcessing = false;
                }
            },
            3000
        );
        return false;
    }

    /**
     * Inner class for validation result as per sequence diagram.
     */
    public static class ValidationResult {
        private boolean valid;
        private java.util.List<String> errors;

        public ValidationResult(boolean valid, java.util.List<String> errors) {
            this.valid = valid;
            this.errors = errors;
        }

        public boolean isValid() {
            return valid;
        }

        public java.util.List<String> getErrors() {
            return errors;
        }
    }

    /**
     * Inner class for operation result.
     */
    public static class Result {
        private boolean success;
        private String message;
        private CulturalHeritage data;

        public Result(boolean success, String message, CulturalHeritage data) {
            this.success = success;
            this.message = message;
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }

        public CulturalHeritage getData() {
            return data;
        }
    }
}
