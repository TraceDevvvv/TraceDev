package com.example.commentediting.usecase;

import com.example.commentediting.request.EditCommentRequest;

/**
 * Interface for the Edit Comment Use Case.
 * Defines the contract for initiating the comment editing process.
 */
public interface IEditCommentUseCase {
    /**
     * Executes the comment editing logic based on the provided request.
     *
     * @param request The request object containing comment details and confirmation status.
     */
    void execute(EditCommentRequest request);
}