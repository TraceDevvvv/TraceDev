package com.example.service;

import com.example.dto.ClassDTO;
import com.example.model.ClassEntity;
import com.example.repository.IClassRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for business logic related to classes.
 * It orchestrates data retrieval and transformation.
 */
public class ClassService {
    private IClassRepository classRepository;

    /**
     * Constructor for ClassService, typically used for dependency injection.
     * @param classRepository The repository for accessing ClassEntity data.
     */
    public ClassService(IClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    /**
     * Retrieves all classes and transforms them into ClassDTOs.
     * This method corresponds to the "Class Service -> Class Repository : findAll()"
     * and subsequent mapping in the sequence diagram.
     *
     * @return A list of ClassDTOs representing all available classes.
     */
    public List<ClassDTO> getAllClasses() {
        System.out.println("ClassService: Requesting all classes from repository.");
        // Call the repository to get entities
        List<ClassEntity> classEntities = classRepository.findAll();
        List<ClassDTO> classDTOs = new ArrayList<>();

        System.out.println("ClassService: Mapping ClassEntity to ClassDTO (m8).");
        // Map each entity to a DTO
        for (ClassEntity entity : classEntities) {
            classDTOs.add(mapToDTO(entity));
        }

        System.out.println("ClassService: Prepared " + classDTOs.size() + " ClassDTOs.");
        return classDTOs;
    }

    /**
     * Private helper method to map a ClassEntity to a ClassDTO.
     * This corresponds to the "ClassService -> ClassService : mapToDTO(entity : ClassEntity)"
     * self-call in the sequence diagram.
     *
     * @param entity The ClassEntity to be mapped.
     * @return A new ClassDTO populated with data from the entity.
     */
    private ClassDTO mapToDTO(ClassEntity entity) {
        // Assume all fields are directly transferable for simplicity
        return new ClassDTO(entity.getId(), entity.getName(), entity.getDescription(), entity.getAccessKey());
    }
}