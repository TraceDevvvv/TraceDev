package com.example.application;

import com.example.application.command.Command;

/**
 * Interface for role management use cases.
 */
public interface RoleManagementUseCase {
    /**
     * Executes the given command.
     * @param command the command to execute
     */
    void execute(Command command);

    /**
     * Rolls back the last operation (e.g., in case of interruption).
     */
    void rollback();
}