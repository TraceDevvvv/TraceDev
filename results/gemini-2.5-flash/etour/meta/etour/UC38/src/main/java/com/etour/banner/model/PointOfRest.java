package com.etour.banner.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Conceptual model representing a Point of Rest.
 * In a full system, this would likely be a JPA entity or DTO fetched from another service.
 * For the scope of this 'InsertBanner' service, it primarily serves to define
 * the structure for properties like 'maxBanners' that might be associated with a PoR.
 * It is not directly persisted by this service but is used for business logic.
 */
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
public class PointOfRest {

    private String id; // Unique identifier for the Point of Rest
    private String name; // Name of the Point of Rest
    private int maxBanners; // Maximum number of banners allowed for this Point of Rest

    // In a real application, this class might have more fields
    // and potentially be an @Entity if managed by this service,
    // or a DTO if fetched from another microservice.
}