package com.example.teachermanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Simulated repository for Class data.
 * Provides methods to access and manage Class objects.
 * This class mimics a database interaction layer for Class entities.
 */
public class ClassRepository {
    // A map to store Class objects, simulating a database table.
    // Key: Class ID, Value: Class object.
    private final Map<String, Class> classes = new HashMap<>();

    /**
     * Constructs a new ClassRepository and populates it with initial dummy data.
     * This data serves for demonstration purposes in a simulated environment.
     */
    public ClassRepository() {
        // Populate with dummy data for demonstration
        // Ensure academicYearIds match those in AcademicYearRepository
        classes.put("C101", new Class("C101", "Grade 10A", "AY2023"));
        classes.put("C102", new Class("C102", "Grade 10B", "AY2023"));
        classes.put("C103", new Class("C103", "Grade 11A", "AY2023"));
        classes.put("C201", new Class("C201", "Grade 10A", "AY2024"));
        classes.put("C202", new Class("C202", "Grade 11B", "AY2024"));
        classes.put("C301", new Class("C301", "Grade 12C", "AY2025"));
        classes.put("C302", new Class("C302", "Grade 12D", "AY2025"));
    }

    /**
     * Finds classes associated with a specific academic year ID.
     * @param academicYearId The ID of the academic year to filter classes by.
     * @return A new {@code ArrayList} containing {@code Class} objects that belong to the specified academic year.
     *         Returns an empty list if no classes are found for the given academic year ID.
     */
    public List<Class> findByAcademicYearId(String academicYearId) {
        return classes.values().stream()
                .filter(c -> c.getAcademicYearId().equals(academicYearId))
                .collect(Collectors.toList());
    }

    /**
     * Finds a class by its unique identifier.
     * @param id The unique ID of the class to find.
     * @return An {@code Optional} containing the {@code Class} if found,
     *         or an empty {@code Optional} if no class with the given ID exists.
     */
    public Optional<Class> findById(String id) {
        return Optional.ofNullable(classes.get(id));
    }
}