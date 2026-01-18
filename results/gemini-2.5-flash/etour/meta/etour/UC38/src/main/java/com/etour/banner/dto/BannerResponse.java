package com.etour.banner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for banner insertion responses.
 * This object is used to send back information to the client after a banner
 * insertion attempt, indicating success or failure and providing relevant details.
 */
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class BannerResponse {

    private String bannerId; // The ID of the newly inserted banner, if successful
    private String message; // A descriptive message about the operation's outcome
    private String imageUrl; // The URL or path of the newly uploaded banner image, if successful
}