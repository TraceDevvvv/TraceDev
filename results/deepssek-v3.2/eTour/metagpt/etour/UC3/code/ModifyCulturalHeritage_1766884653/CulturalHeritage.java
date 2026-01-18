/**
 * CulturalHeritage class represents a cultural heritage object in the system.
 * It contains all the data fields for a cultural heritage item and includes
 * validation methods to ensure data integrity.
 */
package workspace.ModifyCulturalHeritage_1766884653;

public class CulturalHeritage {
    // Private fields for cultural heritage data
    private String id;
    private String name;
    private String description;
    private String location;
    private int year; // Year of creation/origin
    private String category;
    private String status; // Status: ACTIVE, INACTIVE, PENDING_VERIFICATION
    
    // Constants for validation
    public static final int MIN_YEAR = -3000; // Reasonable minimum year for cultural heritage
    public static final int MAX_YEAR = 2024; // Current year as maximum
    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_INACTIVE = "INACTIVE";
    public static final String STATUS_PENDING = "PENDING_VERIFICATION";
    
    /**
     * Default constructor
     */
    public CulturalHeritage() {
        this.id = "";
        this.name = "";
        this.description = "";
        this.location = "";
        this.year = 0;
        this.category = "";
        this.status = STATUS_ACTIVE;
    }
    
    /**
     * Parameterized constructor
     * 
     * @param id The unique identifier for the cultural heritage
     * @param name The name of the cultural heritage
     * @param description Description of the cultural heritage
     * @param location Location where the cultural heritage is located
     * @param year Year of creation/origin
     * @param category Category of cultural heritage (e.g., Monument, Artifact, etc.)
     * @param status Current status of the cultural heritage
     */
    public CulturalHeritage(String id, String name, String description, 
                           String location, int year, String category, String status) {
        this.id = id != null ? id : "";
        this.name = name != null ? name : "";
        this.description = description != null ? description : "";
        this.location = location != null ? location : "";
        this.year = year;
        this.category = category != null ? category : "";
        this.status = status != null ? status : STATUS_ACTIVE;
    }
    
    // Getters and Setters
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id != null ? id : "";
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name != null ? name : "";
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description != null ? description : "";
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location != null ? location : "";
    }
    
    public int getYear() {
        return year;
    }
    
    public void setYear(int year) {
        this.year = year;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category != null ? category : "";
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        if (isValidStatus(status)) {
            this.status = status;
        } else {
            this.status = STATUS_ACTIVE;
        }
    }
    
    /**
     * Validates if the cultural heritage object has all required fields
     * 
     * @return true if valid, false if missing required data
     */
    public boolean isValid() {
        return id != null && !id.trim().isEmpty() &&
               name != null && !name.trim().isEmpty() &&
               location != null && !location.trim().isEmpty() &&
               year >= MIN_YEAR && year <= MAX_YEAR &&
               category != null && !category.trim().isEmpty() &&
               isValidStatus(status);
    }
    
    /**
     * Validates if a given status is valid
     * 
     * @param status Status to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidStatus(String status) {
        return status != null && (
            status.equals(STATUS_ACTIVE) ||
            status.equals(STATUS_INACTIVE) ||
            status.equals(STATUS_PENDING)
        );
    }
    
    /**
     * Validates if the year is within reasonable bounds
     * 
     * @param year Year to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValidYear(int year) {
        return year >= MIN_YEAR && year <= MAX_YEAR;
    }
    
    /**
     * Gets validation error messages for invalid fields
     * 
     * @return String containing validation error messages or empty string if valid
     */
    public String getValidationErrors() {
        StringBuilder errors = new StringBuilder();
        
        if (id == null || id.trim().isEmpty()) {
            errors.append("ID is required. ");
        }
        
        if (name == null || name.trim().isEmpty()) {
            errors.append("Name is required. ");
        }
        
        if (location == null || location.trim().isEmpty()) {
            errors.append("Location is required. ");
        }
        
        if (!isValidYear(year)) {
            errors.append(String.format("Year must be between %d and %d. ", MIN_YEAR, MAX_YEAR));
        }
        
        if (category == null || category.trim().isEmpty()) {
            errors.append("Category is required. ");
        }
        
        if (!isValidStatus(status)) {
            errors.append("Status must be one of: ACTIVE, INACTIVE, PENDING_VERIFICATION. ");
        }
        
        return errors.toString().trim();
    }
    
    /**
     * Creates a copy of this CulturalHeritage object
     * 
     * @return A new CulturalHeritage object with the same data
     */
    public CulturalHeritage copy() {
        return new CulturalHeritage(
            this.id,
            this.name,
            this.description,
            this.location,
            this.year,
            this.category,
            this.status
        );
    }
    
    /**
     * Compares this cultural heritage object with another
     * 
     * @param other The other CulturalHeritage object to compare with
     * @return true if all fields are equal, false otherwise
     */
    public boolean equals(CulturalHeritage other) {
        if (other == null) return false;
        
        return (this.id == null ? other.id == null : this.id.equals(other.id)) &&
               (this.name == null ? other.name == null : this.name.equals(other.name)) &&
               (this.description == null ? other.description == null : this.description.equals(other.description)) &&
               (this.location == null ? other.location == null : this.location.equals(other.location)) &&
               this.year == other.year &&
               (this.category == null ? other.category == null : this.category.equals(other.category)) &&
               (this.status == null ? other.status == null : this.status.equals(other.status));
    }
    
    @Override
    public String toString() {
        return String.format(
            "CulturalHeritage [ID: %s, Name: %s, Location: %s, Year: %d, Category: %s, Status: %s]",
            id, name, location, year, category, status
        );
    }
    
    /**
     * Returns a formatted string with all details for display
     * 
     * @return Formatted string with all cultural heritage details
     */
    public String toDetailedString() {
        return String.format(
            "Cultural Heritage Details:\n" +
            "  ID: %s\n" +
            "  Name: %s\n" +
            "  Description: %s\n" +
            "  Location: %s\n" +
            "  Year: %d\n" +
            "  Category: %s\n" +
            "  Status: %s",
            id, name, description, location, year, category, status
        );
    }
}