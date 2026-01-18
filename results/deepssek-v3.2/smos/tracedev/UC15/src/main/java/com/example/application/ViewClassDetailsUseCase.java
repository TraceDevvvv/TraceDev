package com.example.application;

import com.example.domain.Class;
import com.example.infrastructure.ClassRepository;
import com.example.ui.ClassDetailsDTO;
import com.example.ui.ErrorDTO;

/**
 * Interface defining the use case for viewing class details.
 * The execute method takes a class ID and returns a ClassDetailsDTO if successful,
 * or an ErrorDTO in case of failure.
 */
public interface ViewClassDetailsUseCase {
    /**
     * Executes the use case to retrieve class details.
     * @param classId The ID of the class to retrieve.
     * @return A ClassDetailsDTO containing the class details, or an ErrorDTO if an error occurs.
     */
    Object execute(String classId); // Returns either ClassDetailsDTO or ErrorDTO
}