package com.example.modifybanner.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Data Transfer Object (DTO) for updating a banner.
 * This class encapsulates the data required to change a banner's image.
 */
@Data // Lombok annotation to generate getters, setters, toString, equals, and hashCode
public class BannerUpdateRequest {
    private MultipartFile imageFile; // The new image file for the banner
}