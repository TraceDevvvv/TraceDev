package com.etour.registration.application;

import com.etour.registration.domain.CreateAccountCommand;
import com.etour.registration.domain.RegistrationResult;

/**
 * Port/Interface for the Create Account use case.
 */
public interface CreateAccountUseCase {
    RegistrationResult execute(CreateAccountCommand command);
}