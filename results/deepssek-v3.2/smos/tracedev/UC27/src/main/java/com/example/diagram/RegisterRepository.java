package com.example.diagram;

import java.util.List;

/**
 * Repository interface for Register entities.
 */
public interface RegisterRepository {
    /**
     * Finds registers by academic year.
     * @param year the academic year.
     * @return list of Register objects.
     */
    List<Register> findByAcademicYear(String year);
}