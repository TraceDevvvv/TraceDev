package com.example.command;

import com.example.model.Feedback;
import com.example.repository.FeedbackRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Handler for EditCommentCommand. Performs validation and execution.
 */
public class EditCommentCommandHandler {
    private FeedbackRepository feedbackRepo;

    public EditCommentCommandHandler(FeedbackRepository feedbackRepo) {
        this.feedbackRepo = feedbackRepo;
    }

    /**
     * Executes the command: validates, updates comment, saves.
     * @param command The command to handle.
     * @return CommandResult indicating success or failure.
     */
    public CommandResult handle(EditCommentCommand command) {
        System.out.println("EditCommentCommandHandler: handling command for feedback " + command.getFeedbackId());
        ValidationResult validationResult = validate(command);
        if (!validationResult.isValid()) {
            return new CommandResult(false, "Validation failed", validationResult.getErrors());
        }

        Feedback feedback = feedbackRepo.findById(command.getFeedbackId());
        if (feedback == null) {
            return new CommandResult(false, "Feedback not found", List.of("Feedback not found"));
        }

        feedback.updateComment(command.getNewComment());
        feedbackRepo.save(feedback);

        return new CommandResult(true, "Feedback comment updated successfully", new ArrayList<>());
    }

    /**
     * Validates the command.
     * @param command The command to validate.
     * @return ValidationResult with status and error messages.
     */
    public ValidationResult validate(EditCommentCommand command) {
        List<String> errors = new ArrayList<>();
        if (command.getFeedbackId() <= 0) {
            errors.add("Invalid feedback ID");
        }
        if (command.getNewComment() == null || command.getNewComment().trim().isEmpty()) {
            errors.add("Comment cannot be empty");
        }
        if (command.getOperatorId() == null || command.getOperatorId().trim().isEmpty()) {
            errors.add("Operator ID is required");
        }
        return new ValidationResult(errors.isEmpty(), errors);
    }
}