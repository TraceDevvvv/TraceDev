package com.system.controller;

import com.system.domain.CulturalGood;
import com.system.repository.ICulturalGoodRepository;
import com.system.validation.IValidator;
import com.system.validation.ValidationResult;
import com.system.service.IAgencyService;
import com.system.ui.EditForm;
import com.system.errors.ErrorHandler;
import java.util.Map;

/**
 * Controller coordinating the edit cultural good use case.
 */
public class EditCulturalGoodController {
    private ICulturalGoodRepository culturalGoodRepository;
    private IValidator validator;
    private IAgencyService agencyService;
    private ErrorHandler errorHandler;

    public EditCulturalGoodController(ICulturalGoodRepository culturalGoodRepository, IValidator validator,
                                      IAgencyService agencyService, ErrorHandler errorHandler) {
        this.culturalGoodRepository = culturalGoodRepository;
        this.validator = validator;
        this.agencyService = agencyService;
        this.errorHandler = errorHandler;
    }

    /**
     * Loads a cultural good by its ID.
     */
    public CulturalGood loadCulturalGood(String id) {
        return culturalGoodRepository.findById(id);
    }

    /**
     * Creates and returns an EditForm for the given cultural good.
     */
    public EditForm displayEditForm(CulturalGood culturalGood) {
        EditForm form = new EditForm();
        form.display(culturalGood);
        return form;
    }

    /**
     * Validates the form data using the injected validator.
     */
    public ValidationResult validateFormData(Map<String, Object> formData) {
        return validator.validate(formData);
    }

    /**
     * Creates a transaction confirmation message.
     */
    public String confirmTransaction() {
        return "Are you sure you want to save the changes?";
    }

    /**
     * Saves the updated cultural good to the repository.
     */
    public boolean saveCulturalGood(CulturalGood culturalGood) {
        try {
            CulturalGood saved = culturalGoodRepository.save(culturalGood);
            return saved != null;
        } catch (Exception e) {
            handleError(e);
            return false;
        }
    }

    /**
     * Handles errors by delegating to the error handler.
     */
    public void handleError(Exception error) {
        errorHandler.handleSystemError(error);
    }

    /**
     * Verifies the agency is active before allowing edits.
     */
    public boolean verifyAgencyActive(String agencyId) {
        return agencyService.isAgencyActive(agencyId);
    }

    /**
     * Returns transaction confirmation object (for sequence diagram compatibility).
     */
    public String getTransactionConfirmation() {
        return confirmTransaction();
    }

    /**
     * Returns validation result with explicit valid status (for sequence diagram).
     */
    public ValidationResult validationResult(boolean valid) {
        // This method is for sequence diagram traceability
        // In real implementation, it would return a specific validation result
        return new ValidationResult(valid, null);
    }
}