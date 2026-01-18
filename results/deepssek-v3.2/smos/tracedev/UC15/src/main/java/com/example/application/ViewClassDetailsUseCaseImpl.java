package com.example.application;

import com.example.domain.Class;
import com.example.infrastructure.ClassRepository;
import com.example.infrastructure.exceptions.DataSourceException;
import com.example.ui.ClassDetailsDTO;
import com.example.ui.ErrorDTO;

/**
 * Implementation of the ViewClassDetailsUseCase.
 * This class orchestrates the retrieval of class details from the repository
 * and maps the result to a DTO or an error DTO.
 */
public class ViewClassDetailsUseCaseImpl implements ViewClassDetailsUseCase {
    private ClassRepository classRepository;

    /**
     * Constructor for the use case implementation.
     * @param classRepository The repository to fetch class data from.
     */
    public ViewClassDetailsUseCaseImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    /**
     * Executes the use case to retrieve class details.
     * @param classId The ID of the class to retrieve.
     * @return A ClassDetailsDTO if successful, or an ErrorDTO if an error occurs.
     */
    @Override
    public Object execute(String classId) {
        try {
            Class classEntity = classRepository.findById(classId);
            // Create ClassDetailsDTO from the Class entity
            ClassDetailsDTO dto = createClassDetailsDTO(classEntity);
            return dto;
        } catch (DataSourceException e) {
            // If a DataSourceException is thrown, create an ErrorDTO
            ErrorDTO errorDTO = new ErrorDTO("Connection failed", 500);
            return errorDTO;
        }
    }

    /**
     * Creates a ClassDetailsDTO.
     * @param classEntity The Class entity.
     * @return The created ClassDetailsDTO.
     */
    public ClassDetailsDTO createClassDetailsDTO(Class classEntity) {
        return new ClassDetailsDTO(
            classEntity.getId(),
            classEntity.getName(),
            classEntity.getAddress(),
            classEntity.getSchoolYear()
        );
    }
}