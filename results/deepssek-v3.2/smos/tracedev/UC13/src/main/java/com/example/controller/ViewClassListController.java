package com.example.controller;

import com.example.model.Class;
import com.example.repository.ClassRepository;
import com.example.exception.ConnectionException;
import java.util.List;

/**
 * Controller for the "View List of Classes" use case.
 */
public class ViewClassListController {
    private ClassRepository classRepository;

    public ViewClassListController(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    /**
     * Executes the use case to retrieve classes for a given academic year.
     * @param academicYear the selected academic year
     * @return list of classes for that year
     * @throws ConnectionException if a connection failure occurs
     */
    public List<Class> execute(String academicYear) throws ConnectionException {
        // Delegate to repository to fetch classes
        return classRepository.findByAcademicYear(academicYear);
    }

    /**
     * Handles empty list or exception from repository.
     * @param academicYear the academic year
     * @return list of classes, or throws ConnectionException
     * @throws ConnectionException if connection fails
     */
    public List<Class> emptyListOrException(String academicYear) throws ConnectionException {
        try {
            List<Class> classes = classRepository.findByAcademicYear(academicYear);
            if (classes.isEmpty()) {
                // Return empty list
                return classes;
            }
            return classes;
        } catch (ConnectionException e) {
            throw e;
        }
    }

    public ClassRepository getClassRepository() {
        return classRepository;
    }

    public void setClassRepository(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }
}