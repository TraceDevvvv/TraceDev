package com.example.application_layer;

/**
 * Application Layer: Use Case interface for editing a rest point.
 * Follows the Interface Segregation Principle.
 */
public interface EditRestPointUseCase {
    EditRestPointResult execute(EditRestPointCommand command);
}