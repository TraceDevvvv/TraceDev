/**
 * RefreshmentPoint.java
 * Represents a point of rest (refreshment point) in the system.
 * Contains attributes describing the refreshment point and methods to access them.
 */
public class RefreshmentPoint {
    private int id;
    private String name;
    private String description;
    private String address;
    private String city;
    private String category; // e.g., restaurant, cafe, bar, etc.
    private double rating; // 0.0 to 5.0
    private int priceRange; // 1-4 (1: $, 2: $$, 3: $$$, 4: $$$$)
    private String phoneNumber;
    private String openingHours;
    private boolean hasWiFi;
    private boolean hasParking;
    
    /**
     * Default constructor.
     */
    public RefreshmentPoint() {
        this.id = 0;
        this.name = "Unknown";
        this.description = "";
        this.address = "";
        this.city = "";
        this.category = "Unknown";
        this.rating = 0.0;
        this.priceRange = 1;
        this.phoneNumber = "";
        this.openingHours = "N/A";
        this.hasWiFi = false;
        this.hasParking = false;
    }
    
    /**
     * Parameterized constructor for creating a refreshment point with all details.
     * 
     * @param id unique identifier
     * @param name name of the refreshment point
     * @param description description
     * @param address street address
     * @param city city location
     * @param category category type
     * @param rating average rating (0.0-5.0)
     * @param priceRange price range indicator (1-4)
     * @param phoneNumber contact phone number
     * @param openingHours business hours
     * @param hasWiFi WiFi availability
     * @param hasParking parking availability
     */
    public RefreshmentPoint(int id, String name, String description, String address, String city,
                           String category, double rating, int priceRange, String phoneNumber,
                           String openingHours, boolean hasWiFi, boolean hasParking) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.city = city;
        this.category = category;
        setRating(rating); // Use setter to validate
        setPriceRange(priceRange); // Use setter to validate
        this.phoneNumber = phoneNumber;
        this.openingHours = openingHours;
        this.hasWiFi = hasWiFi;
        this.hasParking = hasParking;
    }
    
    /**
     * Gets the ID of the refreshment point.
     * 
     * @return the ID
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets the ID of the refreshment point.
     * 
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * Gets the name of the refreshment point.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Sets the name of the refreshment point.
     * 
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * Gets the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Sets the description.
     * 
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * Gets the address.
     * 
     * @return the address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * Sets the address.
     * 
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * Gets the city.
     * 
     * @return the city
     */
    public String getCity() {
        return city;
    }
    
    /**
     * Sets the city.
     * 
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * Gets the category.
     * 
     * @return the category
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * Sets the category.
     * 
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
    /**
     * Gets the rating.
     * Validates that the rating is between 0.0 and 5.0.
     * 
     * @return the rating
     */
    public double getRating() {
        return rating;
    }
    
    /**
     * Sets the rating.
     * 
     * @param rating the rating to set (0.0-5.0)
     * @throws IllegalArgumentException if rating is outside valid range
     */
    public void setRating(double rating) {
        if (rating < 0.0 || rating > 5.0) {
            throw new IllegalArgumentException("Rating must be between 0.0 and 5.0");
        }
        this.rating = rating;
    }
    
    /**
     * Gets the price range.
     * 
     * @return the priceRange (1-4)
     */
    public int getPriceRange() {
        return priceRange;
    }
    
    /**
     * Sets the price range.
     * 
     * @param priceRange the priceRange to set (1-4)
     * @throws IllegalArgumentException if price range is outside 1-4
     */
    public void setPriceRange(int priceRange) {
        if (priceRange < 1 || priceRange > 4) {
            throw new IllegalArgumentException("Price range must be between 1 and 4");
        }
        this.priceRange = priceRange;
    }
    
    /**
     * Gets the price range as a string representation.
     * 
     * @return string representation of price range (e.g., "$$")
     */
    public String getPriceRangeString() {
        switch (priceRange) {
            case 1: return "$";
            case 2: return "$$";
            case 3: return "$$$";
            case 4: return "$$$$";
            default: return "$";
        }
    }
    
    /**
     * Gets the phone number.
     * 
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    /**
     * Sets the phone number.
     * 
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    /**
     * Gets the opening hours.
     * 
     * @return the openingHours
     */
    public String getOpeningHours() {
        return openingHours;
    }
    
    /**
     * Sets the opening hours.
     * 
     * @param openingHours the openingHours to set
     */
    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }
    
    /**
     * Checks if WiFi is available.
     * 
     * @return true if WiFi is available, false otherwise
     */
    public boolean hasWiFi() {
        return hasWiFi;
    }
    
    /**
     * Sets WiFi availability.
     * 
     * @param hasWiFi the WiFi availability to set
     */
    public void setHasWiFi(boolean hasWiFi) {
        this.hasWiFi = hasWiFi;
    }
    
    /**
     * Checks if parking is available.
     * 
     * @return true if parking is available, false otherwise
     */
    public boolean hasParking() {
        return hasParking;
    }
    
    /**
     * Sets parking availability.
     * 
     * @param hasParking the parking availability to set
     */
    public void setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
    }
    
    /**
     * Returns a string representation of the object.
     * Shows basic details for display in a list.
     * 
     * @return string representation
     */
    @Override
    public String toString() {
        return String.format("[ID: %d] %s - %s (%s) ★%.1f %s", 
            id, name, category, city, rating, getPriceRangeString());
    }
    
    /**
     * Returns detailed string representation for the card view.
     * Shows all details in a formatted way.
     * 
     * @return detailed string representation
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append(String.format("Refreshment Point: %s\n", name));
        sb.append("========================================\n");
        sb.append(String.format("ID: %d\n", id));
        sb.append(String.format("Category: %s\n", category));
        sb.append(String.format("Description: %s\n", description.isEmpty() ? "N/A" : description));
        sb.append(String.format("Address: %s, %s\n", address, city));
        sb.append(String.format("Phone: %s\n", phoneNumber.isEmpty() ? "N/A" : phoneNumber));
        sb.append(String.format("Opening Hours: %s\n", openingHours));
        sb.append(String.format("Rating: ★%.1f/5.0\n", rating));
        sb.append(String.format("Price Range: %s\n", getPriceRangeString()));
        sb.append(String.format("Amenities: %s %s\n",
            hasWiFi ? "Wi-Fi" : "",
            hasParking ? "Parking" : ""));
        sb.append("========================================");
        return sb.toString();
    }
}