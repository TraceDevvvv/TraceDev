/**
 * Represents search criteria for tourist search functionality
 * Used in step 3: Fill out the form
 */
public class SearchCriteria {
    private String name;
    private String email;
    private String touristId;
    public SearchCriteria() {
        // Empty constructor for flexibility
    }
    // Getters and setters
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTouristId() {
        return touristId;
    }
    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }
    /**
     * Checks if all search criteria are empty
     * @return true if all fields are null or empty
     */
    public boolean isEmpty() {
        return (name == null || name.trim().isEmpty()) &&
               (email == null || email.trim().isEmpty()) &&
               (touristId == null || touristId.trim().isEmpty());
    }
}