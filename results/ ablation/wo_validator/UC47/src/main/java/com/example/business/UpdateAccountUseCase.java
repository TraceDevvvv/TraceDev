package com.example.business;

import com.example.business.command.UpdateAccountCommand;
import com.example.business.result.UpdateAccountResult;

/**
 * Application Business Rules Layer: Use case interface for updating tourist account.
 */
public interface UpdateAccountUseCase {
    UpdateAccountResult execute(UpdateAccountCommand command);
}