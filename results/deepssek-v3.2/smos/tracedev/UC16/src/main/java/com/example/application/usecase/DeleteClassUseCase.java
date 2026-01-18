package com.example.application.usecase;

import com.example.domain.model.Class;
import com.example.domain.repository.ClassRepository;
import com.example.application.dto.ClassDTO;
import com.example.infrastructure.logging.Logger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Use case for deleting a class and returning the updated list of classes.
 */
public class DeleteClassUseCase {
    private ClassRepository classRepository;
    private Logger logger;

    public DeleteClassUseCase(ClassRepository classRepository, Logger logger) {
        this.classRepository = classRepository;
        this.logger = logger;
    }

    /**
     * Executes the delete class command.
     * Returns a list of ClassDTOs representing the updated class list.
     */
    public List<ClassDTO> execute(DeleteClassCommand command) {
        Class classEntity = classRepository.findById(command.getClassId());

        if (classEntity != null) {
            // Mark for deletion and delete from repository
            classEntity.markForDeletion();
            classRepository.delete(classEntity);

            // Retrieve updated list
            List<Class> classList = classRepository.findAll();
            List<ClassDTO> dtos = convertToDTOs(classList);

            // Log the operation
            logger.logInfo("Class deletion completed");

            return dtos;
        } else {
            // Return empty list if class not found
            return List.of();
        }
    }

    /**
     * Converts a list of Class entities to ClassDTOs.
     */
    public List<ClassDTO> convertToDTOs(List<Class> classList) {
        return classList.stream()
                .map(c -> new ClassDTO(c.getId(), c.getName(), c.isArchived()))
                .collect(Collectors.toList());
    }
}