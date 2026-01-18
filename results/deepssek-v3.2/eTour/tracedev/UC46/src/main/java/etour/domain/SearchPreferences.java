package etour.domain;

import java.util.List;
import java.util.Map;

/**
 * Domain entity representing search preferences of a tourist.
 */
public class SearchPreferences {
    private String touristId;
    private String language;
    private List<String> categories;
    private int maxDistance;

    public SearchPreferences() {}

    public SearchPreferences(String touristId, String language, List<String> categories, int maxDistance) {
        this.touristId = touristId;
        this.language = language;
        this.categories = categories;
        this.maxDistance = maxDistance;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    /**
     * Updates preferences based on provided map.
     * @param updates map of field names to new values
     */
    public void updatePreferences(Map<String, Object> updates) {
        if (updates == null) return;
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            switch (key) {
                case "language":
                    if (value instanceof String) setLanguage((String) value);
                    break;
                case "categories":
                    if (value instanceof List<?>) {
                        // Type safety warning suppressed; assume List<String>
                        @SuppressWarnings("unchecked")
                        List<String> catList = (List<String>) value;
                        setCategories(catList);
                    }
                    break;
                case "maxDistance":
                    if (value instanceof Integer) setMaxDistance((Integer) value);
                    break;
                default: // ignore unknown keys
            }
        }
    }

    /**
     * Validates the current preference values.
     * @return true if valid, false otherwise
     */
    public boolean validate() {
        // Simple validation: touristId must be present, maxDistance non‑negative.
        return touristId != null && !touristId.trim().isEmpty() && maxDistance >= 0;
    }

    /**
     * Entry condition requirement traceability
     * R-Entry: Tourist HAS search preferences
     */
    public void checkTouristHasSearchPreferences() {
        System.out.println("R-Entry: Tourist HAS search preferences");
    }

    @Override
    public String toString() {
        return "SearchPreferences{" +
                "touristId='" + touristId + '\'' +
                ", language='" + language + '\'' +
                ", categories=" + categories +
                ", maxDistance=" + maxDistance +
                '}';
    }
}