package application;

import presentation.ErrorDetailsDTO;

/**
 * Use case to process error details (e.g., logging).
 */
public class ErroredUseCase {
    public void execute(ErrorDetailsDTO errorDetails) {
        // In a real implementation, this would log the error or trigger alerts.
        System.out.println("ErroredUseCase: Handling error for tag '" + 
                           errorDetails.getTagName() + 
                           "' with type " + errorDetails.getErrorType());
    }
}