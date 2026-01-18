import java.io.Serializable;
import java.util.Objects;

/**
 * RefreshmentPoint class represents a point of rest with all associated data.
 * This model class is used throughout the application to manage refreshment point information.
 */
public class RefreshmentPoint implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Fields representing the refreshment point attributes
    private String id;
    private String name;
    private String location;
    private String description;
    private String facilities; // e.g., "Restrooms, Water, Seating"
    private String status; // "ACTIVE", "INACTIVE", "UNDER_MAINTENANCE"
    private boolean isFunctional;
    private int capacity;
    private String contactPhone;
    private String contactEmail;
    private String operatingHours;
    
    // Default constructor
    public RefreshmentPoint() {
        this.status = "ACTIVE";
        this.isFunctional = true;
    }
    
    // Constructor with essential parameters
    public RefreshmentPoint(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.status = "ACTIVE";
        this.isFunctional = true;
    }
    
    // Full constructor
    public RefreshmentPoint(String id, String name, String location, String description, 
                           String facilities, String status, boolean isFunctional, 
                           int capacity, String contactPhone, String contactEmail, 
                           String operatingHours) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.facilities = facilities;
        this.status = status != null ? status : "ACTIVE";
        this.isFunctional = isFunctional;
        this.capacity = capacity;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.operatingHours = operatingHours;
    }
    
    // Getters and setters for all fields
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getFacilities() {
        return facilities;
    }
    
    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean isFunctional() {
        return isFunctional;
    }
    
    public void setFunctional(boolean isFunctional) {
        this.isFunctional = isFunctional;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public String getContactPhone() {
        return contactPhone;
    }
    
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    
    public String getContactEmail() {
        return contactEmail;
    }
    
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
    
    public String getOperatingHours() {
        return operatingHours;
    }
    
    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }
    
    /**
     * Validates the refreshment point data for completeness and correctness.
     * This method checks if essential fields are present and valid.
     * 
     * @return true if the data is valid, false otherwise
     */
    public boolean validate() {
        // Check that essential fields are not null or empty
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        
        if (location == null || location.trim().isEmpty()) {
            return false;
        }
        
        // Validate status is one of the allowed values
        if (status != null) {
            String upperStatus = status.toUpperCase();
            if (!upperStatus.equals("ACTIVE") && !upperStatus.equals("INACTIVE") && 
                !upperStatus.equals("UNDER_MAINTENANCE")) {
                return false;
            }
        }
        
        // Validate capacity is non-negative
        if (capacity < 0) {
            return false;
        }
        
        // Validate email format if provided
        if (contactEmail != null && !contactEmail.trim().isEmpty()) {
            if (!isValidEmail(contactEmail)) {
                return false;
            }
        }
        
        // Validate phone format if provided
        if (contactPhone != null && !contactPhone.trim().isEmpty()) {
            if (!isValidPhone(contactPhone)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Simple email validation using regex pattern.
     * 
     * @param email the email to validate
     * @return true if email format is valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email.matches(emailRegex);
    }
    
    /**
     * Simple phone validation - allows digits, spaces, parentheses, and hyphens.
     * 
     * @param phone the phone number to validate
     * @return true if phone format is valid, false otherwise
     */
    private boolean isValidPhone(String phone) {
        String phoneRegex = "^[0-9\\s\\-\\(\\)]+$";
        return phone.matches(phoneRegex);
    }
    
    /**
     * Checks if the refreshment point is active and functional.
     * This is used to determine if it can be selected for modification.
     * 
     * @return true if the point is active and functional, false otherwise
     */
    public boolean isActiveAndFunctional() {
        return "ACTIVE".equalsIgnoreCase(status) && isFunctional;
    }
    
    /**
     * Returns a string representation of the refreshment point.
     * Useful for displaying in lists.
     */
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Location: %s, Status: %s, Functional: %s",
                id, name, location, status, isFunctional ? "Yes" : "No");
    }
    
    /**
     * Returns a detailed string representation of the refreshment point.
     * Useful for displaying full details.
     */
    public String toDetailedString() {
        return String.format(
                "Refreshment Point Details:\n" +
                "ID: %s\n" +
                "Name: %s\n" +
                "Location: %s\n" +
                "Description: %s\n" +
                "Facilities: %s\n" +
                "Status: %s\n" +
                "Functional: %s\n" +
                "Capacity: %d\n" +
                "Contact Phone: %s\n" +
                "Contact Email: %s\n" +
                "Operating Hours: %s",
                id, name, location, 
                description != null ? description : "Not provided",
                facilities != null ? facilities : "Not provided",
                status,
                isFunctional ? "Yes" : "No",
                capacity,
                contactPhone != null ? contactPhone : "Not provided",
                contactEmail != null ? contactEmail : "Not provided",
                operatingHours != null ? operatingHours : "Not provided"
        );
    }
    
    /**
     * Compares refreshment points for equality based on ID.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        RefreshmentPoint that = (RefreshmentPoint) obj;
        return Objects.equals(id, that.id);
    }
    
    /**
     * Generates hash code based on ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    /**
     * Creates a copy of this refreshment point.
     * 
     * @return a new RefreshmentPoint with the same data
     */
    public RefreshmentPoint copy() {
        return new RefreshmentPoint(
                id, name, location, description, facilities, status, 
                isFunctional, capacity, contactPhone, contactEmail, operatingHours
        );
    }
}