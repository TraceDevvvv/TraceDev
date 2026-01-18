package com.etour.preferences.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.HashMap;

/**
 * Data Transfer Object (DTO) for preference data.
 * This class is used to transfer preference information between the client and the server.
 * It includes validation annotations to ensure data integrity upon submission.
 */
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class PreferenceDTO {

    /**
     * The unique identifier of the user whose preferences are being modified.
     * This field is mandatory.
     */
    @NotNull(message = "User ID cannot be null")
    private Long userId;

    /**
     * A map containing the generic personal preferences.
     * The keys represent preference names (e.g., "language", "theme")
     * and the values represent the chosen settings (e.g., "en", "dark").
     * This map can be empty if no preferences are set, but not null.
     */
    private Map<String, String> settings = new HashMap<>();

    // Custom constructor for convenience if needed, though Lombok's AllArgsConstructor covers it.
    // public PreferenceDTO(Long userId, Map<String, String> settings) {
    //     this.userId = userId;
    //     this.settings = settings != null ? settings : new HashMap<>();
    // }
}