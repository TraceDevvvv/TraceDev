import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents the edit justification form in the system.
 * Handles form fields, validation, and saving changes to a justification.
 * Follows the use case sequence: change fields -> click save -> change justification.
 */
public class EditJustificationForm {
    private Justification justification;
    private LocalDate newDate;
    private String newJustificationText;
    private boolean isModified;
    private DateTimeFormatter dateFormatter;
    
    /**
     * Constructor for creating an edit form with an existing justification.
     * 
     * @param justification The justification to be edited
     * @throws IllegalArgumentException if justification is null
     */
    public EditJustificationForm(Justification justification) {
        if (justification == null) {
            throw new IllegalArgumentException("Justification cannot be null");
        }
        
        this.justification = justification;
        this.newDate = justification.getDate();
        this.newJustificationText = justification.getDescription();
        this.isModified = false;
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }
    
    /**
     * Displays the current form with all editable fields.
     * Shows current values and indicates which fields can be modified.
     */
    public void displayForm() {
        System.out.println("\n===== Edit Justification Form =====");
        System.out.println("Justification ID: " + justification.getId());
        System.out.println("Case Number: " + justification.getCaseNumber());
        System.out.println("------------------------------------");
        System.out.println("Fields marked with (*) are required\n");
        
        System.out.println("1. Date (*):");
        System.out.println("   Current: " + justification.getDate().format(dateFormatter));
        System.out.println("   New: " + (isDateChanged() ? newDate.format(dateFormatter) : "[unchanged]"));
        
        System.out.println("\n2. Justification Text (*):");
        System.out.println("   Current: " + truncateText(justification.getDescription(), 50));
        System.out.println("   New: " + (isTextChanged() ? truncateText(newJustificationText, 50) : "[unchanged]"));
        
        System.out.println("\n------------------------------------");
        System.out.println("Instructions:");
        System.out.println("- Change fields using setDate() and setJustificationText() methods");
        System.out.println("- Click 'Save' using saveChanges() method");
        System.out.println("====================================");
    }
    
    /**
     * Sets a new date for the justification.
     * Validates the date before accepting the change.
     * 
     * @param newDate The new date for the justification
     * @return true if date was successfully set, false otherwise
     */
    public boolean setDate(LocalDate newDate) {
        if (newDate == null) {
            System.err.println("Error: Date cannot be null");
            return false;
        }
        
        // Validate date is not in the future
        if (newDate.isAfter(LocalDate.now())) {
            System.err.println("Error: Justification date cannot be in the future");
            return false;
        }
        
        // Check if date is actually different
        if (!newDate.equals(justification.getDate())) {
            this.newDate = newDate;
            this.isModified = true;
            return true;
        }
        
        return false;
    }
    
    /**
     * Sets a new date from a string input.
     * Parses the string and validates the date.
     * 
     * @param dateString The date string in yyyy-MM-dd format
     * @return true if date was successfully set, false otherwise
     */
    public boolean setDate(String dateString) {
        if (dateString == null || dateString.trim().isEmpty()) {
            System.err.println("Error: Date string cannot be null or empty");
            return false;
        }
        
        try {
            LocalDate parsedDate = LocalDate.parse(dateString.trim(), dateFormatter);
            return setDate(parsedDate);
        } catch (DateTimeParseException e) {
            System.err.println("Error: Invalid date format. Please use yyyy-MM-dd format");
            return false;
        }
    }
    
    /**
     * Sets new justification text.
     * Validates the text before accepting the change.
     * 
     * @param newText The new justification text
     * @return true if text was successfully set, false otherwise
     */
    public boolean setJustificationText(String newText) {
        if (newText == null || newText.trim().isEmpty()) {
            System.err.println("Error: Justification text cannot be null or empty");
            return false;
        }
        
        // Check text length (using maximum from Justification class)
        if (newText.length() > 1000) { // Assuming MAX_DESCRIPTION_LENGTH from Justification
            System.err.println("Error: Justification text exceeds maximum length of 1000 characters");
            return false;
        }
        
        String trimmedText = newText.trim();
        
        // Check if text is actually different
        if (!trimmedText.equals(justification.getDescription())) {
            this.newJustificationText = trimmedText;
            this.isModified = true;
            return true;
        }
        
        return false;
    }
    
    /**
     * Saves the changes made to the justification.
     * This corresponds to the "Click on Save" event in the use case.
     * 
     * @return true if changes were successfully saved, false otherwise
     */
    public boolean saveChanges() {
        // Check if any changes were made
        if (!isModified) {
            System.out.println("No changes to save.");
            return true; // Treat as successful (nothing to save)
        }
        
        System.out.println("\n--- Saving Justification Changes ---");
        
        // Validate all fields before saving
        if (!validateForm()) {
            System.out.println("✗ Cannot save: Form validation failed.");
            return false;
        }
        
        // Check if there are actual changes to apply
        boolean hasDateChange = isDateChanged();
        boolean hasTextChange = isTextChanged();
        
        if (!hasDateChange && !hasTextChange) {
            System.out.println("No actual changes detected.");
            return true;
        }
        
        try {
            // Apply changes to the justification
            boolean updateSuccess = justification.updateJustification(
                hasDateChange ? newDate : null,
                hasTextChange ? newJustificationText : null,
                "Form Editor"
            );
            
            if (updateSuccess) {
                System.out.println("✓ Justification updated successfully.");
                System.out.println("✓ Changes: " + 
                    (hasDateChange ? "Date " : "") + 
                    (hasTextChange ? "Text" : ""));
                
                // Reset form state after successful save
                resetFormState();
                return true;
            } else {
                System.out.println("✗ Failed to update justification in the system.");
                return false;
            }
            
        } catch (Exception e) {
            System.err.println("✗ Error during save operation: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Validates all form fields before saving.
     * 
     * @return true if all fields are valid, false otherwise
     */
    private boolean validateForm() {
        System.out.println("Validating form data...");
        
        // Validate date
        if (newDate == null) {
            System.err.println("  ✗ Date is required");
            return false;
        }
        
        if (newDate.isAfter(LocalDate.now())) {
            System.err.println("  ✗ Date cannot be in the future");
            return false;
        }
        
        System.out.println("  ✓ Date is valid: " + newDate.format(dateFormatter));
        
        // Validate justification text
        if (newJustificationText == null || newJustificationText.trim().isEmpty()) {
            System.err.println("  ✗ Justification text is required");
            return false;
        }
        
        if (newJustificationText.length() > 1000) {
            System.err.println("  ✗ Justification text exceeds maximum length");
            return false;
        }
        
        System.out.println("  ✓ Justification text is valid (" + 
                          newJustificationText.length() + " characters)");
        
        return true;
    }
    
    /**
     * Checks if the date has been changed from the original.
     * 
     * @return true if date is different from original
     */
    public boolean isDateChanged() {
        return !Objects.equals(newDate, justification.getDate());
    }
    
    /**
     * Checks if the justification text has been changed from the original.
     * 
     * @return true if text is different from original
     */
    public boolean isTextChanged() {
        return !Objects.equals(newJustificationText, justification.getDescription());
    }
    
    /**
     * Checks if any field in the form has been modified.
     * 
     * @return true if any field has been changed
     */
    public boolean isModified() {
        return isModified;
    }
    
    /**
     * Gets the current justification being edited.
     * 
     * @return the justification object
     */
    public Justification getJustification() {
        return justification;
    }
    
    /**
     * Gets the new date value (may be same as original if not changed).
     * 
     * @return the new date
     */
    public LocalDate getNewDate() {
        return newDate;
    }
    
    /**
     * Gets the new justification text (may be same as original if not changed).
     * 
     * @return the new justification text
     */
    public String getNewJustificationText() {
        return newJustificationText;
    }
    
    /**
     * Resets the form state after saving.
     * Updates original values to match current values and clears modified flag.
     */
    private void resetFormState() {
        // Update the form's understanding of "original" values
        this.newDate = justification.getDate();
        this.newJustificationText = justification.getDescription();
        this.isModified = false;
    }
    
    /**
     * Cancels the edit operation and discards any changes.
     * 
     * @return true if cancellation was successful
     */
    public boolean cancelEdit() {
        if (isModified) {
            System.out.println("Discarding " + 
                (isDateChanged() ? "date " : "") + 
                (isTextChanged() ? "text " : "") + 
                "changes...");
            
            // Reset to original values
            this.newDate = justification.getDate();
            this.newJustificationText = justification.getDescription();
            this.isModified = false;
            
            System.out.println("✓ Edit cancelled. All changes discarded.");
        } else {
            System.out.println("No changes to discard.");
        }
        
        return true;
    }
    
    /**
     * Displays a summary of changes that will be applied.
     */
    public void displayChangeSummary() {
        System.out.println("\n--- Change Summary ---");
        
        if (!isModified) {
            System.out.println("No changes have been made.");
            return;
        }
        
        if (isDateChanged()) {
            System.out.println("Date:");
            System.out.println("  From: " + justification.getDate().format(dateFormatter));
            System.out.println("  To:   " + newDate.format(dateFormatter));
        }
        
        if (isTextChanged()) {
            System.out.println("Justification Text:");
            System.out.println("  From: " + truncateText(justification.getDescription(), 30));
            System.out.println("  To:   " + truncateText(newJustificationText, 30));
        }
        
        System.out.println("----------------------");
    }
    
    /**
     * Truncates text for display purposes.
     * 
     * @param text The text to truncate
     * @param maxLength Maximum length before truncation
     * @return truncated text with ellipsis if needed
     */
    private String truncateText(String text, int maxLength) {
        if (text == null) {
            return "[null]";
        }
        
        if (text.length() <= maxLength) {
            return text;
        }
        
        return text.substring(0, maxLength - 3) + "...";
    }
}