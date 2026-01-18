package com.ata.service;

import com.ata.entity.Class;
import com.ata.repository.ClassRepository;
import java.util.List;

/**
 * Implementation of ClassQueryService.
 * Uses repository to fetch classes.
 * Quality requirement: response time < 2 seconds.
 */
public class ClassQueryServiceImpl implements ClassQueryService {
    private ClassRepository classRepository;

    public ClassQueryServiceImpl(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    /**
     * Retrieves all classes. Delegates to repository.
     * Ensures prompt display as per quality requirement.
     * @return List of all classes.
     */
    @Override
    public List<Class> getAllClasses() {
        // Call repository to get classes; note timing requirement.
        // In a real scenario, performance monitoring would ensure <2 seconds.
        return classRepository.findAll();
    }
}