package com.example.modifybanner.dto;

import com.example.modifybanner.model.Banner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing a banner in responses.
 * This class encapsulates the data returned to the client after banner operations.
 */
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@AllArgsConstructor // Lombok annotation to generate an all-argument constructor
public class BannerResponse {
    private Long id; // Unique identifier for the banner
    private Long restaurantId; // Identifier for the associated restaurant
    private String name; // Name of the banner
    private String imagePath; // Internal path where the image is stored
    private String imageUrl; // Publicly accessible URL of the banner image
    private LocalDateTime createdAt; // Timestamp when the banner was created
    private LocalDateTime updatedAt; // Timestamp when the banner was last updated

    /**
     * Static factory method to create a BannerResponse from a Banner entity.
     *
     * @param banner The Banner entity to convert.
     * @return A new BannerResponse object.
     */
    public static BannerResponse fromEntity(Banner banner) {
        return new BannerResponse(
                banner.getId(),
                banner.getRestaurantId(),
                banner.getName(),
                banner.getImagePath(),
                banner.getImageUrl(),
                banner.getCreatedAt(),
                banner.getUpdatedAt()
        );
    }
}