package com.example.delay;

/**
 * Use case interface for changing delay.
 */
public interface DelayChangeUseCase {
    /**
     * Executes the change delay command.
     * @param command The command containing date and new delay.
     * @return Result indicating success or failure.
     */
    Result execute(ChangeDelayCommand command);
}