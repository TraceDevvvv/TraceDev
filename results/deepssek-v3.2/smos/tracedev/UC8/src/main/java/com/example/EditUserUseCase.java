package com.example;

/**
 * Use case for editing a user.
 */
public class EditUserUseCase {
    private UserRepository userRepository;
    private UserValidator validator;
    private ViewUserDetailsUseCase viewUserDetailsUseCase;

    public EditUserUseCase(UserRepository userRepository, UserValidator validator, ViewUserDetailsUseCase viewUserDetailsUseCase) {
        this.userRepository = userRepository;
        this.validator = validator;
        this.viewUserDetailsUseCase = viewUserDetailsUseCase;
    }

    /**
     * Executes the edit user command.
     * First validates the input, then updates the user if validation passes.
     */
    public EditUserResult execute(EditUserCommand command) {
        // Entry condition: viewdetTailsente use case must have been executed.
        // We assume it has been called earlier.
        EditUserDTO dto = command.getEditUserDTO();
        // First, validate the DTO syntax
        ValidationResult syntaxValidation = dto.validate();
        if (!syntaxValidation.isValid()) {
            return EditUserResult.failure("Syntax validation failed", syntaxValidation.getErrors());
        }

        // Fetch existing user for business validation
        User existingUser = userRepository.findById(command.getUserId());
        if (existingUser == null) {
            return EditUserResult.failure("User not found", java.util.Collections.singletonList("User not found"));
        }

        // Validate business rules
        ValidationResult businessValidation = validator.validateForEdit(dto, existingUser);
        if (!businessValidation.isValid()) {
            return EditUserResult.failure("Business validation failed", businessValidation.getErrors());
        }

        // All validations passed, proceed with update
        User userToUpdate = userRepository.findById(command.getUserId());
        if (userToUpdate == null) {
            return EditUserResult.failure("User not found during update", java.util.Collections.singletonList("User not found"));
        }

        userToUpdate.updateDetails(dto);
        try {
            userRepository.save(userToUpdate);
        } catch (Exception e) {
            return EditUserResult.failure("Save failed: " + e.getMessage(), java.util.Collections.emptyList());
        }

        return EditUserResult.success("User updated successfully");
    }
}