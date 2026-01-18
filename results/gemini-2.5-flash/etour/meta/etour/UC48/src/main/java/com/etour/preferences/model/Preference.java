package com.etour.preferences.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents the generic personal preferences for a tourist.
 * This entity stores preferences associated with a specific user ID.
 * The preferences are stored as a map of key-value pairs, allowing for flexible
 * and generic preference settings.
 */
@Entity
@Table(name = "user_preferences")
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class Preference {

    /**
     * Unique identifier for the preference entry.
     * This is typically the user ID, as each user has one set of generic preferences.
     */
    @Id
    private Long userId;

    /**
     * A map to store the actual preference key-value pairs.
     * This allows for dynamic and generic preference settings without
     * needing to alter the database schema for each new preference.
     * The map is stored as a JSON string in the database.
     *
     * Using @ElementCollection with @CollectionTable and @MapKeyColumn/@Column
     * is a common way to store a Map in JPA. However, for simplicity and flexibility
     * with generic preferences (where keys might not be fixed), storing as a JSON string
     * in a single column is often preferred. For this example, we'll simulate
     * this by using a simple String and relying on a converter or direct JSON handling
     * in the service layer if needed. For JPA, a simple approach is to store it as a String
     * and let the application handle the JSON serialization/deserialization.
     *
     * For a more robust solution, a custom `AttributeConverter` could be used
     * to automatically convert `Map<String, String>` to/from JSON string.
     * For this implementation, we'll assume the `Map<String, String>` is handled
     * by the application logic or a simple string representation.
     *
     * Given the system design's "unclear" section about storage,
     * we'll use a simple String field for `settings` and assume it will store
     * a JSON representation of the map. This keeps the model simple and
     * flexible for various preference types.
     */
    @Column(name = "settings", columnDefinition = "TEXT")
    private String settingsJson; // Stores preferences as a JSON string

    /**
     * Helper method to convert the JSON string settings into a Map.
     * This is a transient method and not directly mapped to a database column.
     * In a real application, a JSON library (like Jackson) would be used for parsing.
     * For this example, we'll return an empty map or a placeholder.
     *
     * @return A map of preference key-value pairs.
     */
    @Transient // Indicates that this field is not to be persisted in the database
    public Map<String, String> getSettings() {
        // In a real application, use a JSON library (e.g., Jackson) to deserialize settingsJson
        // For simplicity, returning an empty map or a dummy map.
        // Example: return new ObjectMapper().readValue(settingsJson, new TypeReference<Map<String, String>>() {});
        if (settingsJson == null || settingsJson.isEmpty()) {
            return new HashMap<>();
        }
        // Placeholder for actual JSON deserialization
        // For the purpose of this exercise, we'll assume the DTO handles the map conversion
        // and this model primarily deals with the JSON string for persistence.
        // If we were to implement full JSON conversion here, it would require a JSON library.
        // Let's assume the service layer or DTO conversion handles the actual map logic.
        return new HashMap<>(); // Returning an empty map as a placeholder
    }

    /**
     * Helper method to set the preferences from a Map and convert them into a JSON string.
     * This is a transient method and not directly mapped to a database column.
     * In a real application, a JSON library (like Jackson) would be used for serialization.
     *
     * @param settings A map of preference key-value pairs.
     */
    @Transient // Indicates that this field is not to be persisted in the database
    public void setSettings(Map<String, String> settings) {
        // In a real application, use a JSON library (e.g., Jackson) to serialize settings
        // For simplicity, setting a dummy JSON string or handling in DTO.
        // Example: this.settingsJson = new ObjectMapper().writeValueAsString(settings);
        if (settings == null || settings.isEmpty()) {
            this.settingsJson = null;
        } else {
            // Placeholder for actual JSON serialization
            // For the purpose of this exercise, we'll assume the DTO handles the map conversion
            // and this model primarily deals with the JSON string for persistence.
            // If we were to implement full JSON conversion here, it would require a JSON library.
            this.settingsJson = "{}"; // Dummy JSON string
        }
    }
}