import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a justice/justification in the system.
 * Contains all relevant fields and validation logic.
 */
public class Justification {
    private int id;
    private LocalDate date;
    private String description;
    private String status;
    private String caseNumber;
    private String lastModifiedBy;
    private LocalDate lastModifiedDate;
    
    // Constants for validation
    private static final int MAX_DESCRIPTION_LENGTH = 1000;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    /**
     * Constructor for creating a new justification.
     * 
     * @param id Unique identifier for the justification
     * @param date Date of the justification
     * @param description Text description of the justification
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Justification(int id, LocalDate date, String description) {
        setId(id);
        setDate(date);
        setDescription(description);
        this.status = "ACTIVE";
        this.caseNumber = generateCaseNumber(id);
        this.lastModifiedBy = "SYSTEM";
        this.lastModifiedDate = LocalDate.now();
    }
    
    /**
     * Full constructor with all fields.
     */
    public Justification(int id, LocalDate date, String description, String status, 
                        String caseNumber, String lastModifiedBy, LocalDate lastModifiedDate) {
        this.id = id;
        this.date = date;
        this.description = description;
        this.status = status;
        this.caseNumber = caseNumber;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedDate = lastModifiedDate;
    }
    
    /**
     * Validates and sets the justification ID.
     * 
     * @param id The justification ID (must be positive)
     * @throws IllegalArgumentException if ID is not positive
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Justification ID must be positive");
        }
        this.id = id;
    }
    
    /**
     * Validates and sets the justification date.
     * Date cannot be in the future.
     * 
     * @param date The justification date
     * @throws IllegalArgumentException if date is null or in the future
     */
    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Justification date cannot be in the future");
        }
        this.date = date;
    }
    
    /**
     * Validates and sets the justification description.
     * 
     * @param description The justification text
     * @throws IllegalArgumentException if description is null, empty, or too long
     */
    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new IllegalArgumentException("Description exceeds maximum length of " + 
                                             MAX_DESCRIPTION_LENGTH + " characters");
        }
        this.description = description.trim();
    }
    
    /**
     * Updates the justification with new values.
     * Also updates last modified information.
     * 
     * @param newDate New date for the justification
     * @param newDescription New description for the justification
     * @param modifiedBy User who made the modification
     * @return true if update was successful
     */
    public boolean updateJustification(LocalDate newDate, String newDescription, String modifiedBy) {
        try {
            // Validate inputs
            if (newDate != null) {
                setDate(newDate);
            }
            if (newDescription != null && !newDescription.trim().isEmpty()) {
                setDescription(newDescription);
            }
            
            // Update modification metadata
            this.lastModifiedBy = modifiedBy;
            this.lastModifiedDate = LocalDate.now();
            
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to update justification: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Displays the justification details in a formatted way.
     */
    public void displayDetails() {
        System.out.println("=== Justification Details ===");
        System.out.println("ID: " + id);
        System.out.println("Date: " + date.format(DATE_FORMATTER));
        System.out.println("Case Number: " + caseNumber);
        System.out.println("Status: " + status);
        System.out.println("Description: " + description);
        System.out.println("Last Modified By: " + lastModifiedBy);
        System.out.println("Last Modified Date: " + 
                          (lastModifiedDate != null ? lastModifiedDate.format(DATE_FORMATTER) : "N/A"));
        System.out.println("============================");
    }
    
    /**
     * Returns a string representation of the justification for logging/debugging.
     */
    @Override
    public String toString() {
        return String.format("Justification{id=%d, date=%s, caseNumber=%s, status=%s}", 
                           id, date.format(DATE_FORMATTER), caseNumber, status);
    }
    
    /**
     * Validates if the justification is in a valid state.
     * 
     * @return true if all required fields are valid
     */
    public boolean isValid() {
        try {
            if (id <= 0) return false;
            if (date == null || date.isAfter(LocalDate.now())) return false;
            if (description == null || description.trim().isEmpty()) return false;
            if (description.length() > MAX_DESCRIPTION_LENGTH) return false;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Parses a date string in yyyy-MM-dd format.
     * 
     * @param dateStr The date string to parse
     * @return LocalDate object if valid, null otherwise
     */
    public static LocalDate parseDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return null;
        }
        try {
            return LocalDate.parse(dateStr.trim(), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format. Expected yyyy-MM-dd");
            return null;
        }
    }
    
    // Getters
    public int getId() { return id; }
    public LocalDate getDate() { return date; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
    public String getCaseNumber() { return caseNumber; }
    public String getLastModifiedBy() { return lastModifiedBy; }
    public LocalDate getLastModifiedDate() { return lastModifiedDate; }
    
    // Setters for other fields
    public void setStatus(String status) { 
        if (status != null) this.status = status; 
    }
    
    public void setCaseNumber(String caseNumber) { 
        if (caseNumber != null) this.caseNumber = caseNumber; 
    }
    
    public void setLastModifiedBy(String lastModifiedBy) { 
        if (lastModifiedBy != null) this.lastModifiedBy = lastModifiedBy; 
    }
    
    public void setLastModifiedDate(LocalDate lastModifiedDate) { 
        if (lastModifiedDate != null) this.lastModifiedDate = lastModifiedDate; 
    }
    
    /**
     * Generates a case number based on the justification ID.
     * 
     * @param id The justification ID
     * @return Generated case number string
     */
    private String generateCaseNumber(int id) {
        return String.format("CASE-%06d", id);
    }
}