package com.example.ports;

import com.example.domain.ImageMetadata;

/**
 * Interface for validating image metadata.
 */
public interface ImageValidator {
    ValidationResult validate(ImageMetadata imageMetadata);
}