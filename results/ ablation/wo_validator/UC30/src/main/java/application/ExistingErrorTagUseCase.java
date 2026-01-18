package application;

import presentation.ErrorDetailsDTO;
import java.util.Date;

/**
 * Use case to handle errors for existing tags.
 */
public class ExistingErrorTagUseCase {
    public ErrorDetailsDTO execute(String tagName) {
        // Create error details for duplicate tag
        ErrorDetailsDTO errorDetails = new ErrorDetailsDTO();
        errorDetails.setTagName(tagName);
        errorDetails.setErrorType("DUPLICATE_TAG");
        errorDetails.setTimestamp(new Date());
        errorDetails.setSeverity("MEDIUM");
        return errorDetails;
    }
}