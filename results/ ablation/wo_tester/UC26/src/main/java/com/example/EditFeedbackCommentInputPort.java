package com.example;

/**
 * Input port interface for the edit feedback comment use case (Clean Architecture).
 */
public interface EditFeedbackCommentInputPort {
    /**
     * Executes the edit feedback comment operation.
     * @param request DTO containing all necessary data
     */
    void execute(EditFeedbackCommentRequestDTO request);
}