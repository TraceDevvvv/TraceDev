/**
 * Implementation of the SearchTourist use case.
 * This class provides search functionality for tourist accounts as a separate,
 * reusable component that can be activated by other use cases.
 * According to the requirements: "Tourists from the list obtained by activating 
 * the use case SearchTourist will select and activate a feature for disposal."
 */
import java.util.ArrayList;
import java.util.List;
public class SearchTourist {
    /**
     * Searches for tourists based on the given query string.
     * This method implements the SearchTourist use case functionality.
     * 
     * @param allTourists The complete list of tourists to search through
     * @param query The search string to match against tourist attributes
     * @return A filtered list of tourists matching the search criteria
     */
    public List<Tourist> search(List<Tourist> allTourists, String query) {
        List<Tourist> results = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) {
            // If query is empty, return the complete list
            results.addAll(allTourists);
            return results;
        }
        String searchQuery = query.toLowerCase().trim();
        // Search through all tourists for matches
        for (Tourist tourist : allTourists) {
            // Check if the query matches any of the tourist's attributes
            if (tourist.getName().toLowerCase().contains(searchQuery) ||
                tourist.getId().toLowerCase().contains(searchQuery) ||
                tourist.getEmail().toLowerCase().contains(searchQuery)) {
                results.add(tourist);
            }
        }
        return results;
    }
    /**
     * Inner class representing a Tourist.
     * This class is public to allow access from other packages.
     */
    public static class Tourist {
        private String id;
        private String name;
        private String email;
        public Tourist(String id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }
        public String getId() {
            return id;
        }
        public String getName() {
            return name;
        }
        public String getEmail() {
            return email;
        }
    }
}