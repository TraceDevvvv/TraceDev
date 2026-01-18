package com.example;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache for academic years, keyed by professor ID.
 * Implements a simple in-memory cache.
 */
public class AcademicYearCache {
    // Using ConcurrentHashMap for thread safety if accessed concurrently, though not strictly needed by current sequence diagram.
    private Map<String, List<AcademicYear>> cache = new ConcurrentHashMap<>();

    public AcademicYearCache() {
        System.out.println("AcademicYearCache initialized.");
    }

    /**
     * Retrieves academic years from the cache for a given professor.
     *
     * @param professorId The ID of the professor.
     * @return A list of AcademicYear objects if found in cache, otherwise null.
     */
    public List<AcademicYear> getAcademicYears(String professorId) {
        System.out.println("AcademicYearCache: Attempting to retrieve academic years for professorId: " + professorId);
        List<AcademicYear> years = cache.get(professorId);
        if (years != null) {
            System.out.println("AcademicYearCache: Cache hit for professorId: " + professorId);
        } else {
            System.out.println("AcademicYearCache: Cache miss for professorId: " + professorId);
        }
        return years;
    }

    /**
     * Stores academic years in the cache for a given professor.
     *
     * @param professorId The ID of the professor.
     * @param years The list of AcademicYear objects to cache.
     */
    public void putAcademicYears(String professorId, List<AcademicYear> years) {
        System.out.println("AcademicYearCache: Storing " + years.size() + " academic years for professorId: " + professorId);
        cache.put(professorId, years);
    }
}