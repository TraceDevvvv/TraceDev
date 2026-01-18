/**
Data model class representing a user's search preferences.
Contains all preference fields that can be modified by the Tourist.
*/
import java.io.Serializable;
public class SearchPreference implements Serializable {
    private String destinationType; // Beach, Mountain, City, etc.
    private int maxBudget; // Maximum budget for travel
    private int travelDistance; // Maximum travel distance in km
    private String accommodationType; // Hotel, Hostel, Airbnb, etc.
    private boolean familyFriendly; // Family-friendly preferences
    private boolean adventureActivities; // Include adventure activities
    private String foodPreference; // Vegetarian, Non-vegetarian, Vegan
    public SearchPreference() {
        // Default preferences
        this.destinationType = "Beach";
        this.maxBudget = 2000;
        this.travelDistance = 500;
        this.accommodationType = "Hotel";
        this.familyFriendly = true;
        this.adventureActivities = false;
        this.foodPreference = "Non-vegetarian";
    }
    public SearchPreference(String destinationType, int maxBudget, int travelDistance, 
                           String accommodationType, boolean familyFriendly, 
                           boolean adventureActivities, String foodPreference) {
        this.destinationType = destinationType;
        this.maxBudget = maxBudget;
        this.travelDistance = travelDistance;
        this.accommodationType = accommodationType;
        this.familyFriendly = familyFriendly;
        this.adventureActivities = adventureActivities;
        this.foodPreference = foodPreference;
    }
    // Getters and setters
    public String getDestinationType() { return destinationType; }
    public void setDestinationType(String destinationType) { this.destinationType = destinationType; }
    public int getMaxBudget() { return maxBudget; }
    public void setMaxBudget(int maxBudget) { this.maxBudget = maxBudget; }
    public int getTravelDistance() { return travelDistance; }
    public void setTravelDistance(int travelDistance) { this.travelDistance = travelDistance; }
    public String getAccommodationType() { return accommodationType; }
    public void setAccommodationType(String accommodationType) { this.accommodationType = accommodationType; }
    public boolean isFamilyFriendly() { return familyFriendly; }
    public void setFamilyFriendly(boolean familyFriendly) { this.familyFriendly = familyFriendly; }
    public boolean isAdventureActivities() { return adventureActivities; }
    public void setAdventureActivities(boolean adventureActivities) { this.adventureActivities = adventureActivities; }
    public String getFoodPreference() { return foodPreference; }
    public void setFoodPreference(String foodPreference) { this.foodPreference = foodPreference; }
    @Override
    public String toString() {
        return String.format(
            "Destination Type: %s\nMax Budget: $%d\nTravel Distance: %d km\nAccommodation: %s\nFamily Friendly: %s\nAdventure Activities: %s\nFood Preference: %s",
            destinationType, maxBudget, travelDistance, accommodationType,
            familyFriendly ? "Yes" : "No", adventureActivities ? "Yes" : "No", foodPreference
        );
    }
}