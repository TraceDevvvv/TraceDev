package com.etour.banner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for the response after a banner upload operation.
 * This class encapsulates the result of an insert banner request,
 * providing a message, the ID of the newly created banner, and its image URL.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadResponseDTO {
    /**
     * A message indicating the status or outcome of the upload operation (e.g., "success", "error").
     */
    private String message;

    /**
     * The unique identifier of the banner that was successfully uploaded.
     * This will be null if the upload failed.
     */
    private String bannerId;

    /**
     * The URL where the uploaded banner image can be accessed.
     * This will be null if the upload failed.
     */
    private String imageUrl;
}