package com.school;

/**
 * Controller for the Insert New Class use case.
 */
public class InsertClassController {
    private ClassValidator classValidator;
    private ClassRepository classRepository;

    public InsertClassController(ClassValidator validator, ClassRepository repo) {
        this.classValidator = validator;
        this.classRepository = repo;
    }

    /**
     * Main method to insert a new class.
     * @param formData the form data.
     * @return insertion result.
     */
    public InsertionResult insertNewClass(ClassFormDTO formData) {
        ValidationResult validationResult = classValidator.validate(formData);
        if (validationResult != ValidationResult.VALID) {
            handleError(validationResult);
            return InsertionResult.VALIDATION_ERROR;
        }

        Class classEntity = createClassEntity(formData);
        try {
            Class savedClass = classRepository.save(classEntity);
            System.out.println("InsertClassController: Class saved with ID " + savedClass.getId());
            return InsertionResult.SUCCESS;
        } catch (RuntimeException e) {
            if ("NETWORK_ERROR".equals(e.getMessage())) {
                return InsertionResult.NETWORK_ERROR;
            } else if ("PERSISTENCE_ERROR".equals(e.getMessage())) {
                return InsertionResult.PERSISTENCE_ERROR;
            } else {
                // Unknown error
                return InsertionResult.PERSISTENCE_ERROR;
            }
        }
    }

    /**
     * Internal method to create a Class entity from DTO.
     * Requirement REQ-004 (internal method)
     */
    private Class createClassEntity(ClassFormDTO formDTO) {
        return new Class(formDTO.getName(), formDTO.getAddress(), formDTO.getAcademicYear());
    }

    /**
     * Handles validation errors by obtaining an error message and logging.
     * Visibility changed to public to match sequence diagram.
     * @param validationResult the validation result.
     */
    public void handleError(ValidationResult validationResult) {
        String errorMessage = classValidator.getErrorMessage(validationResult);
        System.out.println("InsertClassController: Validation error - " + errorMessage);
        // In the sequence diagram, the error message is returned to UI.
        // We'll just log it here; UI retrieves it via getErrorMessage.
    }

    /**
     * Cancels the insertion operation.
     * @return USER_CANCELLED result.
     */
    public InsertionResult cancelInsertion() {
        return InsertionResult.USER_CANCELLED;
    }
}