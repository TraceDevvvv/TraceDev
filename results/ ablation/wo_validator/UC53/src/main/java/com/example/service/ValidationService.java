
package com.example.service;

import com.example.dto.FeedbackForm;

/**
 * Service interface for validation.
 */
public interface ValidationService {
    /**
     * Validates the feedback form data.
     * @param feedbackForm The form to validate.
     * @return ValidationResult with validity status and error messages.
     */
    Object validate(FeedbackForm feedbackForm);
}
