package com.example.repository;

import com.example.model.ClassEntity;
import java.util.List;

/**
 * Interface for the Class Repository.
 * Defines the contract for data access operations related to ClassEntity.
 */
public interface IClassRepository {

    /**
     * Retrieves all ClassEntity objects from the data source.
     * @return A list of all ClassEntity objects.
     */
    List<ClassEntity> findAll();
}