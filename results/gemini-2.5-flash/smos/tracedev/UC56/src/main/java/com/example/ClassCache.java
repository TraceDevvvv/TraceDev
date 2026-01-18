package com.example;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Cache for classes, keyed by a combination of professor ID and academic year ID.
 * Implements a simple in-memory cache.
 */
public class ClassCache {
    // Using ConcurrentHashMap for thread safety, with a concatenated key.
    private Map<String, List<Class>> cache = new ConcurrentHashMap<>();

    public ClassCache() {
        System.out.println("ClassCache initialized.");
    }

    private String generateKey(String professorId, String academicYearId) {
        return professorId + "_" + academicYearId;
    }

    /**
     * Retrieves classes from the cache for a given professor and academic year.
     *
     * @param professorId The ID of the professor.
     * @param academicYearId The ID of the academic year.
     * @return A list of Class objects if found in cache, otherwise null.
     */
    public List<Class> getClasses(String professorId, String academicYearId) {
        String key = generateKey(professorId, academicYearId);
        System.out.println("ClassCache: Attempting to retrieve classes for key: " + key);
        List<Class> classes = cache.get(key);
        if (classes != null) {
            System.out.println("ClassCache: Cache hit for key: " + key);
        } else {
            System.out.println("ClassCache: Cache miss for key: " + key);
        }
        return classes;
    }

    /**
     * Stores classes in the cache for a given professor and academic year.
     *
     * @param professorId The ID of the professor.
     * @param academicYearId The ID of the academic year.
     * @param classes The list of Class objects to cache.
     */
    public void putClasses(String professorId, String academicYearId, List<Class> classes) {
        String key = generateKey(professorId, academicYearId);
        System.out.println("ClassCache: Storing " + classes.size() + " classes for key: " + key);
        cache.put(key, classes);
    }
}