/**
 * SearchPreferences class encapsulating a tourist's search preferences.
 * This class models the search preferences that a tourist can modify in the eTour system.
 * Includes validation methods and a copy constructor for safe editing.
 */
public class SearchPreferences {
    private String destination;
    private double minBudget;
    private double maxBudget;
    private String startDate;
    private String endDate;
    private String accommodationType;
    private int numberOfTravelers;
    private boolean includeFlights;
    private boolean includeMeals;
    private String travelStyle;
    
    /**
     * Default constructor - creates empty search preferences
     */
    public SearchPreferences() {
        this.destination = "";
        this.minBudget = 0.0;
        this.maxBudget = 0.0;
        this.startDate = "";
        this.endDate = "";
        this.accommodationType = "Hotel";
        this.numberOfTravelers = 1;
        this.includeFlights = false;
        this.includeMeals = false;
        this.travelStyle = "Standard";
    }
    
    /**
     * Parameterized constructor for creating search preferences with specific values
     * @param destination desired travel destination
     * @param minBudget minimum budget for the trip
     * @param maxBudget maximum budget for the trip
     * @param startDate trip start date (format: YYYY-MM-DD)
     * @param endDate trip end date (format: YYYY-MM-DD)
     * @param accommodationType type of accommodation (Hotel, Hostel, Apartment, etc.)
     * @param numberOfTravelers number of people traveling
     * @param includeFlights whether flights should be included
     * @param includeMeals whether meals should be included
     * @param travelStyle travel style (Budget, Standard, Luxury, Adventure, etc.)
     */
    public SearchPreferences(String destination, double minBudget, double maxBudget, 
                           String startDate, String endDate, String accommodationType,
                           int numberOfTravelers, boolean includeFlights, 
                           boolean includeMeals, String travelStyle) {
        this.destination = destination;
        this.minBudget = minBudget;
        this.maxBudget = maxBudget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accommodationType = accommodationType;
        this.numberOfTravelers = numberOfTravelers;
        this.includeFlights = includeFlights;
        this.includeMeals = includeMeals;
        this.travelStyle = travelStyle;
    }
    
    /**
     * Copy constructor - creates a deep copy of another SearchPreferences object
     * Useful for editing preferences without modifying the original until confirmed
     * @param other the SearchPreferences object to copy
     */
    public SearchPreferences(SearchPreferences other) {
        if (other != null) {
            this.destination = other.destination;
            this.minBudget = other.minBudget;
            this.maxBudget = other.maxBudget;
            this.startDate = other.startDate;
            this.endDate = other.endDate;
            this.accommodationType = other.accommodationType;
            this.numberOfTravelers = other.numberOfTravelers;
            this.includeFlights = other.includeFlights;
            this.includeMeals = other.includeMeals;
            this.travelStyle = other.travelStyle;
        }
    }
    
    /**
     * Validates the search preferences
     * @return true if preferences are valid, false otherwise
     */
    public boolean validate() {
        // Check required fields
        if (destination == null || destination.trim().isEmpty()) {
            return false;
        }
        
        // Budget validation
        if (minBudget < 0 || maxBudget < 0) {
            return false;
        }
        
        if (minBudget > maxBudget) {
            return false;
        }
        
        // Date validation (basic format check)
        if (startDate != null && !startDate.isEmpty() && 
            endDate != null && !endDate.isEmpty()) {
            // In a real system, we would parse and compare dates
            // For now, just check that end date is not before start date lexicographically
            // (assuming YYYY-MM-DD format)
            if (startDate.compareTo(endDate) > 0) {
                return false;
            }
        }
        
        // Number of travelers validation
        if (numberOfTravelers < 1 || numberOfTravelers > 20) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Gets a summary of the search preferences
     * @return a formatted string summary
     */
    public String getSummary() {
        return String.format(
            "Destination: %s | Budget: $%.2f - $%.2f | Dates: %s to %s | " +
            "Accommodation: %s | Travelers: %d | Flights: %s | Meals: %s | Style: %s",
            destination, minBudget, maxBudget, startDate, endDate, 
            accommodationType, numberOfTravelers, 
            includeFlights ? "Included" : "Not Included",
            includeMeals ? "Included" : "Not Included",
            travelStyle
        );
    }
    
    // Getters and setters for all fields
    
    public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
        this.destination = destination;
    }
    
    public double getMinBudget() {
        return minBudget;
    }
    
    public void setMinBudget(double minBudget) {
        this.minBudget = minBudget;
    }
    
    public double getMaxBudget() {
        return maxBudget;
    }
    
    public void setMaxBudget(double maxBudget) {
        this.maxBudget = maxBudget;
    }
    
    public String getStartDate() {
        return startDate;
    }
    
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    
    public String getEndDate() {
        return endDate;
    }
    
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
    public String getAccommodationType() {
        return accommodationType;
    }
    
    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }
    
    public int getNumberOfTravelers() {
        return numberOfTravelers;
    }
    
    public void setNumberOfTravelers(int numberOfTravelers) {
        this.numberOfTravelers = numberOfTravelers;
    }
    
    public boolean isIncludeFlights() {
        return includeFlights;
    }
    
    public void setIncludeFlights(boolean includeFlights) {
        this.includeFlights = includeFlights;
    }
    
    public boolean isIncludeMeals() {
        return includeMeals;
    }
    
    public void setIncludeMeals(boolean includeMeals) {
        this.includeMeals = includeMeals;
    }
    
    public String getTravelStyle() {
        return travelStyle;
    }
    
    public void setTravelStyle(String travelStyle) {
        this.travelStyle = travelStyle;
    }
    
    /**
     * Returns a string representation of the search preferences
     * @return string representation
     */
    @Override
    public String toString() {
        return "SearchPreferences [destination=" + destination + 
               ", minBudget=" + minBudget + ", maxBudget=" + maxBudget + 
               ", startDate=" + startDate + ", endDate=" + endDate + 
               ", accommodationType=" + accommodationType + 
               ", numberOfTravelers=" + numberOfTravelers + 
               ", includeFlights=" + includeFlights + 
               ", includeMeals=" + includeMeals + 
               ", travelStyle=" + travelStyle + "]";
    }
    
    /**
     * Checks if two SearchPreferences objects are equal
     * @param obj the object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        SearchPreferences that = (SearchPreferences) obj;
        
        if (Double.compare(that.minBudget, minBudget) != 0) return false;
        if (Double.compare(that.maxBudget, maxBudget) != 0) return false;
        if (numberOfTravelers != that.numberOfTravelers) return false;
        if (includeFlights != that.includeFlights) return false;
        if (includeMeals != that.includeMeals) return false;
        if (!destination.equals(that.destination)) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!endDate.equals(that.endDate)) return false;
        if (!accommodationType.equals(that.accommodationType)) return false;
        return travelStyle.equals(that.travelStyle);
    }
    
    /**
     * Generates hash code for the SearchPreferences object
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = destination.hashCode();
        temp = Double.doubleToLongBits(minBudget);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(maxBudget);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + accommodationType.hashCode();
        result = 31 * result + numberOfTravelers;
        result = 31 * result + (includeFlights ? 1 : 0);
        result = 31 * result + (includeMeals ? 1 : 0);
        result = 31 * result + travelStyle.hashCode();
        return result;
    }
}