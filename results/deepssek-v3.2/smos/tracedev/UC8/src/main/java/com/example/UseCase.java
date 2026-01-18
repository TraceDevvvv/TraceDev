package com.example;

import java.util.List;

/**
 * UseCase matching sequence diagram participant "UseCase".
 */
public class UseCase {
    private EditUserUseCase editUserUseCase;

    public UseCase(EditUserUseCase editUserUseCase) {
        this.editUserUseCase = editUserUseCase;
    }

    /**
     * Create failed EditUserResult (message m20).
     */
    public EditUserResult createFailedEditUserResult(String message, List<String> errors) {
        return EditUserResult.failure(message, errors);
    }

    /**
     * Create failed EditUserResult (message m40).
     */
    public EditUserResult createFailedEditUserResultFromException(String message) {
        return EditUserResult.failure(message, java.util.Collections.emptyList());
    }

    /**
     * Create successful EditUserResult (message m47).
     */
    public EditUserResult createSuccessfulEditUserResult(String message) {
        return EditUserResult.success(message);
    }

    /**
     * Execute edit user command.
     */
    public EditUserResult executeEditUserCommand(EditUserCommand command) {
        return editUserUseCase.execute(command);
    }
}