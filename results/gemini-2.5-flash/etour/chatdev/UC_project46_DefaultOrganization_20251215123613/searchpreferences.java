/*
Represents the personal search preferences of a Tourist.
This class holds various search criteria such as destination,
minimum price, maximum price, and a set of categories.
*/
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
public class SearchPreferences implements Serializable {
    // Unique serialization ID for compatibility purposes
    private static final long serialVersionUID = 1L;
    private String destination;
    private double minPrice;
    private double maxPrice;
    private Set<String> categories;
    /**
     * Default constructor for SearchPreferences.
     * Initializes with default values: empty destination, 0.0 min/max price,
     * and an empty set of categories.
     */
    public SearchPreferences() {
        this.destination = "";
        this.minPrice = 0.0;
        this.maxPrice = 0.0;
        this.categories = new HashSet<>();
    }
    /**
     * Constructs a SearchPreferences object with specified values.
     *
     * @param destination The preferred destination for searches.
     * @param minPrice The minimum price for search results.
     * @param maxPrice The maximum price for search results.
     * @param categories A set of preferred categories.
     */
    public SearchPreferences(String destination, double minPrice, double maxPrice, Set<String> categories) {
        this.destination = destination;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.categories = new HashSet<>(categories); // Copy to ensure independence
    }
    // --- Getter Methods ---
    public String getDestination() {
        return destination;
    }
    public double getMinPrice() {
        return minPrice;
    }
    public double getMaxPrice() {
        return maxPrice;
    }
    public Set<String> getCategories() {
        return new HashSet<>(categories); // Return a copy to prevent external modification
    }
    // --- Setter Methods ---
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }
    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }
    public void setCategories(Set<String> categories) {
        this.categories = new HashSet<>(categories); // Copy to ensure independence
    }
    /**
     * Adds a single category to the preferences.
     *
     * @param category The category to add.
     */
    public void addCategory(String category) {
        if (this.categories == null) {
            this.categories = new HashSet<>();
        }
        this.categories.add(category);
    }
    /**
     * Removes a single category from the preferences.
     *
     * @param category The category to remove.
     */
    public void removeCategory(String category) {
        if (this.categories != null) {
            this.categories.remove(category);
        }
    }
    /**
     * Compares this SearchPreferences object with another object for equality.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchPreferences that = (SearchPreferences) o;
        return Double.compare(that.minPrice, minPrice) == 0 &&
               Double.compare(that.maxPrice, maxPrice) == 0 &&
               Objects.equals(destination, that.destination) &&
               Objects.equals(categories, that.categories);
    }
    /**
     * Returns a hash code value for the object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(destination, minPrice, maxPrice, categories);
    }
    /**
     * Returns a string representation of the SearchPreferences object.
     *
     * @return A string containing all preference details.
     */
    @Override
    public String toString() {
        return "SearchPreferences{" +
               "destination='" + destination + '\'' +
               ", minPrice=" + minPrice +
               ", maxPrice=" + maxPrice +
               ", categories=" + categories +
               '}';
    }
}