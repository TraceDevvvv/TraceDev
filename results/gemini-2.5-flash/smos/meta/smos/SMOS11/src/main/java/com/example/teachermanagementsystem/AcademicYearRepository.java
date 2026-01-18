package com.example.teachermanagementsystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Simulated repository for AcademicYear data.
 * Provides methods to access and manage AcademicYear objects.
 * This class mimics a database interaction layer for AcademicYear entities.
 */
public class AcademicYearRepository {
    // A map to store AcademicYear objects, simulating a database table.
    // Key: AcademicYear ID, Value: AcademicYear object.
    private final Map<String, AcademicYear> academicYears = new HashMap<>();

    /**
     * Constructs a new AcademicYearRepository and populates it with initial dummy data.
     * This data serves for demonstration purposes in a simulated environment.
     */
    public AcademicYearRepository() {
        // Populate with dummy data for demonstration
        academicYears.put("AY2023", new AcademicYear("AY2023", "2023-2024"));
        academicYears.put("AY2024", new AcademicYear("AY2024", "2024-2025"));
        academicYears.put("AY2025", new AcademicYear("AY2025", "2025-2026"));
    }

    /**
     * Retrieves all academic years currently stored in the repository.
     * @return A new {@code ArrayList} containing all {@code AcademicYear} objects.
     *         Returns an empty list if no academic years are present.
     */
    public List<AcademicYear> findAll() {
        return new ArrayList<>(academicYears.values());
    }

    /**
     * Finds an academic year by its unique identifier.
     * @param id The unique ID of the academic year to find.
     * @return An {@code Optional} containing the {@code AcademicYear} if found,
     *         or an empty {@code Optional} if no academic year with the given ID exists.
     */
    public Optional<AcademicYear> findById(String id) {
        return Optional.ofNullable(academicYears.get(id));
    }
}