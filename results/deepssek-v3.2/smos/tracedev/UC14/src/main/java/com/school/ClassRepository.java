package com.school;

/**
 * Repository interface for Class persistence.
 */
public interface ClassRepository {
    /**
     * Saves a class entity.
     * @param classObj the class to save.
     * @return the saved class (with generated ID).
     */
    Class save(Class classObj);
}