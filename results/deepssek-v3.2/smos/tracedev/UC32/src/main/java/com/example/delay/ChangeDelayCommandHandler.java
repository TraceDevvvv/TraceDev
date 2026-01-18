package com.example.delay;

/**
 * Command handler that implements the use case logic.
 */
public class ChangeDelayCommandHandler implements DelayChangeUseCase {
    private DelayRepository delayRepository;
    private ChangeDelayValidator validator;
    private volatile boolean cancelled = false; // Flag for interruption handling

    /**
     * Constructor with dependencies.
     * @param delayRepository Repository for delay data.
     * @param validator Validator for command integrity.
     */
    public ChangeDelayCommandHandler(DelayRepository delayRepository, ChangeDelayValidator validator) {
        this.delayRepository = delayRepository;
        this.validator = validator;
    }

    @Override
    public Result execute(ChangeDelayCommand command) {
        if (cancelled) {
            return new Result(false, "Operation cancelled");
        }

        // Validate the command
        ValidationResult validationResult = validator.validate(command);
        if (!validationResult.isValid()) {
            return new Result(false, "Invalid data: " + String.join(", ", validationResult.getErrors()));
        }

        if (cancelled) {
            return new Result(false, "Operation cancelled");
        }

        // Fetch existing delay or create new
        Delay delay = delayRepository.findByDate(command.getDate());
        if (delay == null) {
            delay = new Delay(command.getDate(), command.getDelay());
            delayRepository.save(delay);
        } else {
            delay.updateDelay(command.getDelay());
            delayRepository.updateDelay(delay);
        }

        return new Result(true, "Delay updated successfully");
    }

    /**
     * Cancels the ongoing operation (handles interruption).
     */
    public void cancel() {
        cancelled = true;
        System.out.println("Command handler cancelled.");
    }
}